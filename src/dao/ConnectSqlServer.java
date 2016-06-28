
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

public class ConnectSqlServer {
	public static void main(String[] args) throws Exception {
		System.out.println("Start");
		boolean isSqlServer = true;
		boolean isInitilLoad = false;
		Connection connection = null;

		try {
			if (isSqlServer) {
				connection = getConnection(false);
			} else {
				connection = getOdbcConnection(false);
			}
			if (isInitilLoad) {
				dropTable(connection);
				createTable(connection);
				insertRecords(500000, connection);
			} else {
				// updateTable(10, connection);
				// deleteTable(connection);
				insertRecords(1000000, connection);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.commit();
			connection.close();
		}
		System.out.println("END ");
	}

	public static Connection getConnection(boolean doPrint) throws ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String userName = "subash";
		String password = "Subash07";
		String host = "HP\\SQLEXPRESS";
		String port = ":1433";
		System.out.println("Start  :" + host);
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlserver://" + host + port + ";DatabaseName=BDL;", userName,
					password);
			System.out.println("Connected jdbc:sqlserver " + host);

			if (doPrint) {
				Statement sta = connection.createStatement();
				String Sql = "select * from EMPLOYEE_INFO";
				ResultSet rs = sta.executeQuery(Sql);
				while (rs.next()) {
					System.out.print(rs.getString(1) + "\t");
					System.out.print(rs.getString(2) + "\t");
					System.out.print(rs.getString(3) + "\t");
					System.out.print(rs.getString(4) + "\t");
					System.out.print(rs.getString(5) + "\t");
					System.out.print(rs.getString(6) + "\t");
					System.out.print(rs.getString(7) + "\t");
					System.out.println(rs.getString(8));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("END " + host);
		return connection;
	}

	public static Connection getOdbcConnection(boolean doPrint) throws Exception {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:odbc:BDL");
			System.out.println("Connected jdbc:odbc:BDL");
			if (doPrint) {
				Statement sta = connection.createStatement();
				String Sql = "select * from EMPLOYEE_INFO";
				ResultSet rs = sta.executeQuery(Sql);
				while (rs.next()) {
					System.out.print(rs.getString(1) + "\t");
					System.out.print(rs.getString(2) + "\t");
					System.out.print(rs.getString(3) + "\t");
					System.out.print(rs.getString(4) + "\t");
					System.out.print(rs.getString(5) + "\t");
					System.out.print(rs.getString(6) + "\t");
					System.out.print(rs.getString(7) + "\t");
					System.out.println(rs.getString(8));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;

	}

	public static void dropTable(Connection connection) throws SQLException {
		String sql = "DROP TABLE [dbo].[EMPLOYEE_INFO]";
		Statement st = connection.createStatement();
		st.executeUpdate(sql);
		System.out.println("Table Dropped");
	}

	public static void updateTable(int count, Connection connection) throws SQLException {
		String sql = "UPDATE [BDL].[dbo].[EMPLOYEE_INFO] SET " + "[TP_RATING] = (TP_RATING - 10), "
				+ "[LAST_UPDATED_DATE] = CURRENT_TIMESTAMP WHERE EMPLOYEE_ID IN (SELECT top " + count
				+ " EMPLOYEE_ID FROM [BDL].[dbo].[EMPLOYEE_INFO] WHERE EMPLOYEE_NAME LIKE '%as%')";

		Statement st = connection.createStatement();
		st.executeUpdate(sql);
		System.out.println("Records Updated " + st.getUpdateCount());
	}

	public static void deleteTable(Connection connection) throws SQLException {
		String sql = "UPDATE [BDL].[dbo].[EMPLOYEE_INFO] SET [LAST_UPDATED_DATE] = CURRENT_TIMESTAMP, "
				+ "[DELETED_DATE] = CURRENT_TIMESTAMP "
				+ " WHERE EMPLOYEE_ID IN (SELECT EMPLOYEE_ID FROM [BDL].[dbo].[EMPLOYEE_INFO] WHERE EMPLOYEE_NAME LIKE '%ef%'  )";
		// System.out.println(sql);
		Statement st = connection.createStatement();
		st.executeUpdate(sql);

		System.out.println("Records Deleted " + st.getUpdateCount());
	}

	public static void createTable(Connection connection) throws SQLException {
		String sql = "CREATE TABLE" + "[BDL].[dbo].[EMPLOYEE_INFO]" + "( " + "[EMPLOYEE_ID] [int] NOT NULL, "
				+ "[EMPLOYEE_NAME] [varchar](50) NULL, " + "[BIRTH_DT] [datetime] NOT NULL, "
				+ "[TP_RATING] [smallint] NULL, " + "[GI_ID] [bigint] NOT NULL, " + "[EMAIL_TXT] [varchar](320) NULL, "
				+ "[ROOSTER_IND] [bit] NOT NULL, " + "[EMP_SVRC_LVL_NO] [int] NOT NULL, "
				+ "[CLIENT_RETENTION_NO] [decimal](4, 2) NULL," + "[DELETED_DATE] [datetime] NULL, "
				+ "[LAST_UPDATED_DATE] [datetime] NULL " + " )";

		Statement st = connection.createStatement();
		st.executeUpdate(sql);
		System.out.println("Table Created");

	}

	static Random random = new Random();

	public static int getMax(Connection connection) throws SQLException {
		int max = 0;
		Statement sta = connection.createStatement();
		String Sql = "select max( [EMPLOYEE_ID] ) from [BDL].[dbo].[EMPLOYEE_INFO]";
		ResultSet rs = sta.executeQuery(Sql);
		while (rs.next()) {
			max = rs.getInt(1);
		}
		return max;
	}

	public static void insertRecords(int count, Connection connection) throws SQLException {
		Statement st = connection.createStatement();
		boolean isExecute = true;
		String insertBegin = "INSERT INTO " + "[dbo].[EMPLOYEE_INFO] "
				+ "([EMPLOYEE_ID] ,[BIRTH_DT] ,[TP_RATING] ,[GI_ID] ,[EMAIL_TXT] ,"
				+ "[ROOSTER_IND] ,[EMP_SVRC_LVL_NO] ,[CLIENT_RETENTION_NO] ,[DELETED_DATE] ,[LAST_UPDATED_DATE] ,[EMPLOYEE_NAME]) "
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
