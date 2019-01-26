import java.awt.Color;
import java.util.ArrayList;

public class EgarageDB {
	
	// Status of parking slot on all levels as int
	public static int getSlotStatus(int SlotID, int Level) {
		String query;
		try {
			query = "SELECT CASE `SlotUsed` WHEN 0 THEN type ELSE 4 END AS SlotType FROM `parkinglist` WHERE `Slot` = " + SlotID + " AND `Level` = " + Level;
			
			return connClass.SlotStatus(query, "SlotType");
		} catch (ClassNotFoundException e) {
			return 0;
		}
	}

	public static boolean UpdateCarEnteredParkingSlot(String Level, String Slot) {

		String query;

		query = "UPDATE `parkinglist` SET `SlotUsed`= 1 WHERE `Level` = " + Level + " and `Slot` = " + Slot;

		try {
			return connClass.UpdateCarEnteredOrExitParkingSlot(query);	
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	public static boolean UpdateCarExitFromParkingSlot(String Level, String Slot) {

		String query;

		query = "UPDATE `parkinglist` SET `SlotUsed`= 0 WHERE `Level` = " + Level + " and `Slot` = " + Slot;

		try {
			return connClass.UpdateCarEnteredOrExitParkingSlot(query);	
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

}
