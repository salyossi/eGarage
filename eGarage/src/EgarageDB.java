import java.awt.Color;
import java.util.ArrayList;
import java.util.Vector;

public class EgarageDB {
	
	// Status of parking slot on all levels as int
	public static int getSlotStatus(int SlotID, int Level) {
		String query;
		try {
			query = "SELECT CASE `SlotUsed` WHEN 0 THEN type ELSE 4 END AS SlotType FROM `parkinglist` WHERE `Slot` = " + SlotID + " AND `Level` = " + Level;
			
			return ConnClass.SlotStatus(query, "SlotType");
		} catch (ClassNotFoundException e) {
			return 0;
		}
	}

	public static boolean UpdateCarEnteredParkingSlot(String Level, String Slot) {

		String query;

		query = "UPDATE `parkinglist` SET `SlotUsed`= 1 WHERE `Level` = " + Level + " and `Slot` = " + Slot;

		try {
			return ConnClass.UpdateCarEnteredOrExitParkingSlot(query);	
		} catch (ClassNotFoundException e) {
			return false;
		}
	}


	public static boolean UpdateCarEnteredParkingSlotUsageList(int Level, int Slot, int CarId) {

		String query;

		query = "UPDATE `egarageusage` SET `ParkingSlotEnterance`= ?, `Level` = ?, `Slot` = ? WHERE `CarID` = ?";

		try {
			return ConnClass.InsertForIntOneDateParam(query, Level, Slot, CarId);	
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	public static boolean UpdateCarExitFromParkingSlot(String Level, String Slot) {

		String query;

		query = "UPDATE `parkinglist` SET `SlotUsed`= 0 WHERE `Level` = " + Level + " and `Slot` = " + Slot;

		try {
			return ConnClass.UpdateCarEnteredOrExitParkingSlot(query);	
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	public static boolean isExistsCarID(int CarId) {

		String query;

		query = "SELECT count(*) count FROM `userlist` WHERE `CarID` = " + CarId;

		try {
			int i = ConnClass.IntegerSelect(query, "count");
			if (i > 0) {
				return true;
			} else {
				return false;
			}
		} catch (ClassNotFoundException e) {

			return false;
		}
	}
	
	public static boolean AddRegularCarIdToUserList(int CarId) {

		String query;

		query = "INSERT INTO `userlist` (`CarID`, `Type`) VALUES (?, 1)";

		try {
			return ConnClass.InsertOneIntParam(query, CarId);	
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	public static boolean AddNewCarIDToUsageList(int CarId) {

		String query;

		query = "INSERT INTO `egarageusage` (`CarID`, `GarageEnterance`) VALUES (?, ?)";

		try {
			return ConnClass.InsertOneIntOneDateParam(query, CarId);	
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	public static Vector<String> getCarsEnteredGarage() {
		String query;
		try {
			query = "SELECT `CarID` FROM `egarageusage` WHERE `ParkingSlotEnterance` IS NULL";
			
			return ConnClass.CarsEnteredGarageList(query, "CarID");
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	//UPDATE `egarageusage` SET `ParkingSlotEnterance`= now() WHERE `CarID` = 12345678
	
	
}
