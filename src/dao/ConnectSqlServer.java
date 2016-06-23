
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectSqlServer {
	public static void main4(String[] args) throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String userName = "subash";
		String password = "Subash07";
		String host = "HP\\SQLEXPRESS";
		String port = ":1433";
		System.out.println("Start  :" + host);

		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlserver://" + host + port + ";DatabaseName=BDL;",
					userName, password);
			System.out.println("connected :" + host);
			Statement sta = conn.createStatement();
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("END " + host); 
	}

	public static void main(String[] args) throws Exception {
		try {
			Connection conn = DriverManager.getConnection("jdbc:odbc:BDL");
			System.out.println("connected");
			Statement sta = conn.createStatement();
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
		} catch (Exception e) {
			e.printStackTrace();
			;
		}

	}

}
