package DAO;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

public class ConnectHbase {
	static Configuration conf = new Configuration();
	private static String hbase_tableName = "jsondoc";
	private static String hbase_columnFamily = "doc";
	private static String hbase_columnName = "doc";

	public static void main(String[] args) throws Exception {
		ConnectHbase connectDB = new ConnectHbase();
		boolean connectHbase = true;
		Connection connect = null;

		if (connectHbase) {
			connect = connectDB.connectHbase();
			ResultScanner rscan = null;

			try {
				Table table = connect.getTable(TableName.valueOf(hbase_tableName));
				Scan scan = new Scan();
				rscan = table.getScanner(scan);
			} catch (Exception e) {
			}

			for (Result res : rscan) {
				System.out.println(res);
				System.out.println(
						Bytes.toString(res.getValue(hbase_columnFamily.getBytes(), hbase_columnName.getBytes())));
			}
		}

	}

	public Connection connectHbase() throws Exception {
		conf.set("hbase.zookeeper.quorum", "master.bigdata.labs,slave3.bigdata.labs,slave1.bigdata.labs");
		conf.set("hbase.zookeeper.property.clientPort", "2181");
		return ConnectionFactory.createConnection(conf);
	}
}
