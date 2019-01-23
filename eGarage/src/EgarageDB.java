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


}
