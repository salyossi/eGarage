import java.awt.Color;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class ConnClass {
	private static Connection conn;

	public static Connection getConn() throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://172.50.1.173:3306/egarage?useUnicode=true&characterEncoding=UTF-8&useSSL=false",
					"root", "");
			return conn;
		} catch (SQLException e) {
			return null;
		}
	}

	public static String StringSelect(String query, String field) throws ClassNotFoundException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = ConnClass.getConn();
			if (conn == null)
				return null;

			Statement stmt;
			ResultSet rs;
			String res = null;

			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				res = rs.getString(field);
			}

			conn.close();
			return res;
		} catch (Exception e) {
			return null;
		}
	}

	public static int IntegerSelect(String query, String field) throws ClassNotFoundException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = ConnClass.getConn();
			if (conn == null)
				return 0;

			Statement stmt;
			ResultSet rs;
			int res = 0;

			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				res = rs.getInt(field);
			}

			conn.close();
			return res;
		} catch (Exception e) {
			return 0;
		}
	}

	public static boolean InsertOneIntParam(String query, int param) throws ClassNotFoundException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = ConnClass.getConn();
			if (conn == null)
				return false;

			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, param);

			boolean res = preparedStmt.execute();

			conn.close();
			return res;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean InsertOneIntOneDateParam(String query, int param) throws ClassNotFoundException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = ConnClass.getConn();
			if (conn == null)
				return false;

			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, param);
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			preparedStmt.setTimestamp(2, date);

			boolean res = preparedStmt.execute();

			conn.close();
			return res;
		} catch (Exception e) {
			return false;
		}
	}


	public static boolean InsertForIntOneDateParam(String Query, int Level, int Slot, int CatID) throws ClassNotFoundException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = ConnClass.getConn();
			if (conn == null)
				return false;

			PreparedStatement preparedStmt = conn.prepareStatement(Query);
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			preparedStmt.setTimestamp(1, date);
			preparedStmt.setInt(2, Level);
			preparedStmt.setInt(3, Slot);
			preparedStmt.setInt(4, CatID);
			

			boolean res = preparedStmt.execute();

			conn.close();
			return res;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean InsertTwoIntsParam(String query, int param1, int param2) throws ClassNotFoundException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = ConnClass.getConn();
			if (conn == null)
				return false;

			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, param1);
			preparedStmt.setInt(2, param2);

			boolean res = preparedStmt.execute();

			conn.close();
			return res;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean InsertOneIntTreeStringsParam(String query, int param1, String param2, String param3,
			String param4) throws ClassNotFoundException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = ConnClass.getConn();
			if (conn == null)
				return false;

			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, param1);
			preparedStmt.setString(2, param2);
			preparedStmt.setString(3, param3);
			preparedStmt.setString(4, param4);

			boolean res = preparedStmt.execute();

			conn.close();
			return res;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean deleteOneInt(String query, int param) throws ClassNotFoundException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = ConnClass.getConn();
			if (conn == null)
				return false;

			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, param);

			boolean res = preparedStmt.execute();
			conn.close();
			return res;
		} catch (Exception e) {
			return false;
		}
	}

	public static int SlotStatus(String query, String key) throws ClassNotFoundException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			int res = 0;

			conn = ConnClass.getConn();
			if (conn == null)
				return 0;

			Statement stmt;
			ResultSet rs;

			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				res = rs.getInt(key);	
			}

			conn.close();
			return res;
		} catch (Exception e) {
			return 0;
		}
	}

	public static boolean UpdateCarEnteredOrExitParkingSlot(String query) throws ClassNotFoundException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = ConnClass.getConn();
			if (conn == null)
				return false;
			
			Statement stmt;
			stmt = conn.createStatement();

			stmt.executeUpdate(query);
			
			conn.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static Vector<String> CarsEnteredGarageList(String query, String key) throws ClassNotFoundException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Vector<String> rsList = new Vector<String>();
			
			conn = ConnClass.getConn();
			if (conn == null)
				return null;

			Statement stmt;
			ResultSet rs;

			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				rsList.add(Integer.toString(rs.getInt(key)));
			}

			conn.close();
			return rsList;
		} catch (Exception e) {
			return null;
		}
	}

	
}