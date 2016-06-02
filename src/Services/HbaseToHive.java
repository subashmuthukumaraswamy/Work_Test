package Services;

import DAO.ConnectDB;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseToHive {

	public static void main(String args[]) throws Exception {
		ConnectDB connectDB = new ConnectDB();
		Connection connection = connectDB.connectHbase();
	}
}
