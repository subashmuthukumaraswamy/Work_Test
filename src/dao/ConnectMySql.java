package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectMySql {

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager
				.getConnection("jdbc:mysql://138.201.50.21:3306/subash?user=root&password=Bigboys@123");
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from customers");
		rs.close();
		System.out.println("Connected?");
	}
}
