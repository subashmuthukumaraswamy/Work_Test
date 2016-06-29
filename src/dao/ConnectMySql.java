package dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Random;

import utility.RandomStringTester;

public class ConnectMySql {

	static String SCHEMA_NAME = "subash";
	static String TABLE_NAME = SCHEMA_NAME + "." + "EMPLOYEE_INFO1";

	public static void main(String args[]) throws Exception {
		System.out.println("Start");
		boolean isInitilLoad = false;
		Connection connection = null;

		try {
			connection = getMySqlConnection(false);
			if (isInitilLoad) {
				//dropTable(connection);
				createTable(connection);
				insertRecords(10, connection);
			} else {
				//updateTable(10, connection);
				//deleteTable(connection);
				insertRecords(20, connection);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// connection.commit();
			connection.close();
		}
		System.out.println("END ");
	}

	public static Connection getMySqlConnection(boolean doPrint) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager
				.getConnection("jdbc:mysql://138.201.50.21:3306/subash?user=root&password=Bigboys@123");

		if (doPrint) {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select * from " + TABLE_NAME);
			while (rs.next()) {
				System.out.print(rs.getString(1) + "\t");
				System.out.print(rs.getString(2) + "\t");
				System.out.println(rs.getString(3));
			}
			rs.close();
		}
		System.out.println("Connected?");
		return connection;
	}

	public static void dropTable(Connection connection) throws SQLException {
		String sql = "DROP TABLE " + TABLE_NAME;
		Statement st = connection.createStatement();
		st.executeUpdate(sql);
		System.out.println("Table Dropped");
	}

	public static void updateTable(int count, Connection connection) throws SQLException {
		String sql = "UPDATE " + TABLE_NAME + " SET " + "TP_RATING = (TP_RATING - 10), "
				+ "LAST_UPDATED_DATE = CURRENT_TIMESTAMP WHERE EMPLOYEE_ID IN (SELECT top " + count
				+ " EMPLOYEE_ID FROM " + TABLE_NAME + " WHERE EMPLOYEE_NAME LIKE '%as%')";

		Statement st = connection.createStatement();
		st.executeUpdate(sql);
		System.out.println("Records Updated " + st.getUpdateCount());
	}

	public static void deleteTable(Connection connection) throws SQLException {
		String sql = "UPDATE " + TABLE_NAME + " SET LAST_UPDATED_DATE = CURRENT_TIMESTAMP, "
				+ "DELETED_DATE = CURRENT_TIMESTAMP " + " WHERE EMPLOYEE_ID IN (SELECT EMPLOYEE_ID FROM " + TABLE_NAME
				+ " WHERE EMPLOYEE_NAME LIKE '%ef%'  )";
		// System.out.println(sql);
		Statement st = connection.createStatement();
		st.executeUpdate(sql);

		System.out.println("Records Deleted " + st.getUpdateCount());
	}

	public static void createTable(Connection connection) throws SQLException {
		String sql = "CREATE TABLE " + TABLE_NAME + "" + "( " + "EMPLOYEE_ID int NOT NULL, "
				+ "EMPLOYEE_NAME varchar(50) NULL, " + "BIRTH_DT datetime NOT NULL, " + "TP_RATING smallint NULL, "
				+ "GI_ID bigint NOT NULL, " + "EMAIL_TXT varchar(320) NULL, " + "ROOSTER_IND bit NOT NULL, "
				+ "EMP_SVRC_LVL_NO int NOT NULL, " + "CLIENT_RETENTION_NO decimal(4, 2) NULL,"
				+ "DELETED_DATE datetime NULL, " + "LAST_UPDATED_DATE datetime NULL " + " )";

		Statement st = connection.createStatement();
		st.executeUpdate(sql);
		System.out.println("Table Created");

	}

	static Random random = new Random();

	public static int getMax(Connection connection) throws SQLException {
		int max = 0;
		Statement sta = connection.createStatement();
		String Sql = "select max(EMPLOYEE_ID) from " + TABLE_NAME + "";
		ResultSet rs = sta.executeQuery(Sql);
		while (rs.next()) {
			max = rs.getInt(1);
		}
		return max;
	}

	public static void insertRecords(int count, Connection connection) throws SQLException {
		Statement st = connection.createStatement();
		boolean isExecute = true;
		String insertBegin = "INSERT INTO " + " " + TABLE_NAME + " "
				+ "(EMPLOYEE_ID ,BIRTH_DT ,TP_RATING ,GI_ID ,EMAIL_TXT ,"
				+ "ROOSTER_IND ,EMP_SVRC_LVL_NO ,CLIENT_RETENTION_NO ,DELETED_DATE ,LAST_UPDATED_DATE ,EMPLOYEE_NAME) "
				+ "VALUES ";
		int max_EMPLOYEE_ID = getMax(connection);
		for (int EMPLOYEE_ID = 1; EMPLOYEE_ID <= count; EMPLOYEE_ID++) {
			short smallInt = (short) random.nextInt(Short.MAX_VALUE + 1);
			BigInteger bigint = new BigInteger(20, random);
			String employeeName = RandomStringTester.randomString(5);
			String emailId = employeeName + "@yotabitesllc.com";
			double decimal = roundTwoDecimals(random.nextDouble() * 100);
			int bit = random.nextBoolean() ? 1 : 0;

			String newRow = insertBegin + " (" + (EMPLOYEE_ID + max_EMPLOYEE_ID) + ", CURRENT_TIMESTAMP, " + smallInt
					+ ", " + bigint + ", '" + emailId + "', " + bit + ", " + random.nextInt(50000) + ", " + decimal
					+ ", NULL, " + "CURRENT_TIMESTAMP, '" + employeeName + "')";
			System.out.println(newRow);
			if (isExecute) {
				st.executeUpdate(newRow);
			}
		}
		System.out.println("Inserted " + count + " Records");
	}

	public static double roundTwoDecimals(double d) {
		DecimalFormat twoDForm = new DecimalFormat("##.##");
		double dbl = Double.valueOf(twoDForm.format(d));
		if (dbl >= 100.0) {
			dbl = roundTwoDecimals(random.nextDouble() * 100);
		}
		return dbl;
	}

}
