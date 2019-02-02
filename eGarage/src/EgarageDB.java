import java.awt.Color;
import java.util.ArrayList;
import java.util.Vector;

public class EgarageDB {
	
	// Status of parking slot on all levels as integer
	public static int getSlotStatus(int Slot, int Level) {
		String query;
		try {
			query = "SELECT CASE `SlotUsed` WHEN 0 THEN type ELSE 4 END AS SlotType FROM `parkinglist` WHERE `Slot` = " + Slot + " AND `Level` = " + Level;
			
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

	public static boolean UpdateCarExitFromParkingSlotUsageList(int Level, int Slot) {

		String query;

		query = "UPDATE `egarageusage` SET `ParkingSlotExit`= now() WHERE `Level` = ? and `Slot` = ?";

		try {
			return ConnClass.InsertTwoIntsParam(query, Level, Slot);	
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	public static boolean UpdateCarIsPayingUsageList(int CarId) {

		String query;

		query = "UPDATE egarageusage SET TiketPaid= now() WHERE `CarID` = ?";

		try {
			return ConnClass.InsertOneIntParam(query, CarId);	
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
			query = "SELECT `CarID` FROM `egarageusage` "
					+ "WHERE `ParkingSlotEnterance` IS NULL "
					+ "OR (`ParkingSlotExit` IS NOT NULL "
					+ "AND `ParkingSlotEnterance` < `ParkingSlotExit`)";
			
			return ConnClass.CarsInGarageList(query, "CarID");
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	public static Vector<String> getCarsInPaymentMode() {
		String query;
		try {
			query = "SELECT CarID FROM egarageusage WHERE ParkingSlotEnterance IS NOT NULL AND Authorized = 0";
			
			return ConnClass.CarsInGarageList(query, "CarID");
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
	
	public static int getCarIdInLevelAndSlot(int Level, int Slot) {
		String query;
		try {
			query = "SELECT CarID FROM egarageusage WHERE Slot = " + Slot + " AND Level = " + Level;
			
			return ConnClass.IntegerSelect(query, "CarID");
		} catch (ClassNotFoundException e) {
			return 0;
		}
	}

	public static int getCarTypeInParkingList(int Level, int Slot) {
		String query;
		try {
			query = "SELECT Type FROM parkinglist WHERE Level = " + Level + " and Slot = " + Slot;
			
			return ConnClass.IntegerSelect(query, "Type");
		} catch (ClassNotFoundException e) {
			return 0;
		}
	}

	public static int getCarTypeInUserList(int CarId) {
		String query;
		try {
			query = "SELECT Type FROM userlist WHERE CarID = " + CarId;
			
			return ConnClass.IntegerSelect(query, "Type");
		} catch (ClassNotFoundException e) {
			return 0;
		}
	}

	public static boolean DeleteCarExitingGarage(String CarID) {

		String query;

		query = "DELETE FROM egarageusage WHERE CarID = " + CarID;

		try {
			return ConnClass.DeleteCarExiting(query);	
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	public static boolean isAuthorized(String CarId) {

		String query;

		query = "SELECT `Authorized` FROM `egarageusage` WHERE `CarID` = " + CarId;

		try {
			int i = ConnClass.IntegerSelect(query, "Authorized");
			if (i > 0) {
				return true;
			} else {
				return false;
			}
		} catch (ClassNotFoundException e) {

			return false;
		}
	}

	public static boolean SetAuthorized(int CarId) {

		String query;

		query = "UPDATE egarageusage SET Authorized = 1 WHERE CarID = ?";

		try {
			return ConnClass.InsertOneIntParam(query, CarId);	
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	public static java.sql.Timestamp ParkingStartTime(String CarId) {

		String query;

		query = "SELECT ParkingSlotEnterance FROM egarageusage where CarID = " + CarId;

		try {
			return ConnClass.TimeStampSelect(query, "ParkingSlotEnterance");
		} catch (ClassNotFoundException e) {

			return null;
		}
	}
	
	public static java.sql.Timestamp ParkingEndTime(String CarId) {

		String query;

		query = "SELECT TiketPaid FROM egarageusage where CarID = " + CarId;

		try {
			return ConnClass.TimeStampSelect(query, "TiketPaid");
		} catch (ClassNotFoundException e) {

			return null;
		}
	}
		
	public static int ParkingTimeInHours(String CarId) {

		String query;

		query = "SELECT CEILING((UNIX_TIMESTAMP(`TiketPaid`)-UNIX_TIMESTAMP(`ParkingSlotEnterance`))/3600) time FROM `egarageusage` where `CarID` = " + CarId;

		try {
			return ConnClass.IntegerSelect(query, "time");
		} catch (ClassNotFoundException e) {

			return 0;
		}
	}

	public static int ParkingTimeInDays(String CarId) {

		String query;

		query = "SELECT CEILING((UNIX_TIMESTAMP(`TiketPaid`)-UNIX_TIMESTAMP(`ParkingSlotEnterance`))/86400) time FROM `egarageusage` where `CarID` = " + CarId;

		try {
			return ConnClass.IntegerSelect(query, "time");
		} catch (ClassNotFoundException e) {

			return 0;
		}
	}
	
}
