package dao;

import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class ConnectMongo {

	public static void main(String[] args) throws JSONException {

		try {
			long startTime = System.currentTimeMillis();
			MongoClient mongoClient = new MongoClient("138.201.50.21", 27017);
			/*
			 * 138.201.50.21 138.201.50.12 or slave3.bigdata.labs
			 */
			// printAll(mongoClient);
			System.out.println("Time Taken Mongsfsd Connection: "
					+ utility.JsonTransformer.getDurationBreakdown(System.currentTimeMillis() - startTime));
			// printTable(mongoClient, "subash", "jsonDoc", "events");
			System.out.println("Time Taken Total: "
					+ utility.JsonTransformer.getDurationBreakdown(System.currentTimeMillis() - startTime));

			aggregateDemo(mongoClient);

			mongoClient.close();
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void printTable(MongoClient mongoClient, String dbName, String tableName, String columnName) {

		long startTime = System.currentTimeMillis();
		DB db = mongoClient.getDB(dbName);
		DBCollection collection = db.getCollection(tableName);
		DBCursor curs = collection.find();
		Iterator<DBObject> fields = curs.iterator();
		System.out.println("Time Taken Mong Query: "
				+ utility.JsonTransformer.getDurationBreakdown(System.currentTimeMillis() - startTime));
		startTime = System.currentTimeMillis();
		String columnNameTemp = null;
		int columnCount = 1;
		columnNameTemp = columnCount++ + "_" + columnName;
		System.out.println("columnNameTemp : " + columnNameTemp);

		while (fields.hasNext()) {

			Object value = fields.next().get(columnName);
			if (null != value) {
				if (value instanceof BasicDBList) {
					System.out.println("BasicDBList : " + value);
					utility.JsonTransformer.iterateList(columnNameTemp, (List<Object>) value);
				} else if (value instanceof BasicDBObject) {
					System.out.println("BasicDBObject : " + value);
					utility.JsonTransformer.iterateMap(columnNameTemp, (Map<String, Object>) value);
				} else {
					System.out.println("columnValue : " + value);
				}
			}
		}
		System.out.println("Time Taken Iterater: "
				+ utility.JsonTransformer.getDurationBreakdown(System.currentTimeMillis() - startTime));
	}

	public static void printAll(MongoClient mongoClient) {
		List<String> databases = mongoClient.getDatabaseNames();

		for (String dbName : databases) {
			System.out.println("- Database: " + dbName);
			DB db = mongoClient.getDB(dbName);
			Set<String> collections = db.getCollectionNames();
			for (String colName : collections) {
				System.out.println("\t + Collection: " + colName);
			}
		}
	}

	public static void aggregateDemo(MongoClient mongoClient) {
		DB db = mongoClient.getDB("subash");

		DBCollection dbCollection = db.getCollection("twt");

		// db.twt.aggregate( {
		// $match : {
		// "by_user" : "Neo4j"
		// }
		// }, {
		// $group : {
		// _id : "$by_user",
		// total : {
		// $sum : "$likes"
		// }
		// }
		// }, {
		// $sort : {
		// "total" : -1
		// }
		// });

		DBObject match = new BasicDBObject("$match", new BasicDBObject("by_user", "Neo4j"));

		DBObject group = new BasicDBObject("$group",
				new BasicDBObject("_id", "$by_user").append("urlDistinct", new BasicDBObject("$sum", "$likes")));

		DBObject sort = new BasicDBObject("$sort", new BasicDBObject("urlDistinct", 1));

		AggregationOutput output = dbCollection.aggregate(match, group, sort);

		for (DBObject result : output.results()) {
			System.out.println(result);
		}
	}
}