package test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.json.JSONObject;

public class hello {
	static Configuration conf = new Configuration();

	public static void main3(String arsgs[]) throws Exception {

		JSONObject jsonObj = new JSONObject(
				"{\"_id\": \"05f4ee66-4958-48e7-9dc3-031a194d6b43_u\",\"version\": \"2d307029d34bd4cd4f9535247d1ca55f3634dcc4\",\"events\": [{\"taxYear\": 2015,\"actor\": {\"t\": \"unknown\",\"name\": \"upgrade\",\"officeId\" : \"87971\",\"ptin\" : \"P123456789\"},\"practice\": false,\"workspaceType\": \"CONSUMER\",\"channel\": \"HRBO\",\"client\": {\"clientId\": \"1dbc19b3-f535-486c-aa18-9fde011cea2f\",\"firstName\": \"Test\",\"lastName\": \"DBWODS\",\"taxIdentificationNumber\": \"238749187\",\"dateOfBirth\": {\"$date\": \"1990-01-01T06:00:00.000Z\"}},\"returnId\": \"81463728-c1a5-46a5-9acc-4a73b5e1b7f3\",\"taxProductType\": \"1040\",\"eid\": \"01080a4c-4673-4cd1-8b78-5d605a73824b\",\"ets\": {\"$date\": \"2016-04-11T16:36:18.785Z\"},\"aid\": \"05f4ee66-4958-48e7-9dc3-031a194d6b43_u\",\"ev\": \"efd54f9e71a86f1627c45c75e32c77b44e6f18a3\",\"ep\": {},\"et\": \"com.hrb.esg.ods.domain.tax.event.v2.TaxWorkspaceCreatedEvent\",\"taxpayer\": {\"taxIdentificationNumber\": \"238749187\",\"firstName\": \"Test\",\"lastName\": \"DBWODS\",\"dateOfBirth\": {\"$date\": \"1990-01-01T06:00:00.000Z\"}},\"cardinality\": \"PRIMARY\",\"filingStatus\": \"SINGLE\",\"original\": {},\"modified\": {\"return_dot_prepBeginDate\": \"2016-04-11T11:36:18.786\",\"return_dot_id\": \"81463728-c1a5-46a5-9acc-4a73b5e1b7f3\",\"return_dot_aol_dot_status\": \"\",\"return_dot_taxSystemType\": \"1040\",\"return_dot_lastEditDate\": \"2016-04-11T11:36:18.786\",\"return_dot_workspace_dot_type\": \"CONSUMER\",\"taxpayer_dot_name_dot_first\": \"Test\",\"taxpayer_dot_dob\": \"1990-01-01\",\"taxpayer_dot_name_dot_last\": \"DBWODS\",\"return_dot_taxYear\": \"2015\",\"return_dot_workspace_dot_id\": \"05f4ee66-4958-48e7-9dc3-031a194d6b43\",\"taxpayer_dot_ssn\": \"238749187\",\"return_dot_amended\": \"false\",\"return_dot_segment2\": \"false\",\"return_dot_extension\": \"false\"},\"taxDataResource\": {\"name\": \"81463728-c1a5-46a5-9acc-4a73b5e1b7f3.clz\",\"uriPath\": \"/05f4ee66-4958-48e7-9dc3-031a194d6b43/r/81463728-c1a5-46a5-9acc-4a73b5e1b7f3/d/81463728-c1a5-46a5-9acc-4a73b5e1b7f3.clz?rev=01f5d734a7f10ccc61faf2bda59bc5b7a3271a7a\",\"contentType\": \"x-hrb-application/clz\",\"metadata\": {\"x-hrb-audit-id\": \"3986e888-838b-4dee-9aef-db053418b7b9\"}},\"filingAgency\": \"US\",\"entityGuid\": \"81463728-c1a5-46a5-9acc-4a73b5e1b7f3\",\"payment\": {\"id\": \"b580b3ae-cd3c-4efc-a282-44c1da677304\",\"resourceLink\": {\"name\": \"b580b3ae-cd3c-4efc-a282-44c1da677304.xml\",\"uriPath\": \"/05f4ee66-4958-48e7-9dc3-031a194d6b43/r/81463728-c1a5-46a5-9acc-4a73b5e1b7f3/p/b580b3ae-cd3c-4efc-a282-44c1da677304.xml?rev=6f6f25f3a9bb1e84d782673e17f7739f64e982bb\",\"contentType\": \"x-hrb-application/pos\",\"metadata\": {\"x-hrb-office-id\": \"87971\",\"x-hrb-audit-id\": \"063eb0eb-d89e-4b6a-a962-cb0b8a939fcb\",\"x-hrb-payment-status\": \"PAID\",\"x-hrb-guid\": \"81463728-c1a5-46a5-9acc-4a73b5e1b7f3\",\"x-hrb-content-version\": \"3\",\"x-hrb-payment-balancedue\": \"200\"}},\"lineItems\": [{\"returnId\": \"81463728-c1a5-46a5-9acc-4a73b5e1b7f3\",\"paymentStatus\": \"PAID\",\"balanceDue\": 200.0}],\"createdDateTime\": {\"$date\": \"2016-04-11T16:36:20.070Z\"}},\"subscriber\": {\"subscriberId\": \"87971\",\"subscriberType\": \"OWNER\"},\"filingId\": \"40c509f4-3dea-4521-b0a8-83d48dcd352e\",\"env\": \"TS15_DIGQA\",\"returnFiling\": {\"agency\": \"US\",\"methodType\": \"ELECTRONIC\",\"amendment\": false,\"extension\": false},\"activity\": {\"t\": \"com.hrb.esg.ods.domain.tax.activity.FilingActivity\",\"id\": \"9bfcf676-2543-4d5b-9a61-4a830a2cfd7b\",\"created\": {\"$date\": \"2016-04-11T16:36:20.472Z\"},\"officeId\": \"87971\",\"taxYear\": 2015,\"workspaceType\" : \"CONSUMER\",\"channel\" : \"HRBO\",\"returnType\": \"1040\",\"taxReturnFile\": {\"name\": \"81463728-c1a5-46a5-9acc-4a73b5e1b7f3.clz\",\"uriPath\": \"/05f4ee66-4958-48e7-9dc3-031a194d6b43/r/81463728-c1a5-46a5-9acc-4a73b5e1b7f3/d/81463728-c1a5-46a5-9acc-4a73b5e1b7f3.clz?rev=01f5d734a7f10ccc61faf2bda59bc5b7a3271a7a\",\"contentType\": \"x-hrb-application/clz\",\"metadata\": {\"x-hrb-audit-id\": \"3986e888-838b-4dee-9aef-db053418b7b9\"}},\"payment\": {\"id\": \"b580b3ae-cd3c-4efc-a282-44c1da677304\",\"resourceLink\": {\"name\": \"b580b3ae-cd3c-4efc-a282-44c1da677304.xml\",\"uriPath\": \"/05f4ee66-4958-48e7-9dc3-031a194d6b43/r/81463728-c1a5-46a5-9acc-4a73b5e1b7f3/p/b580b3ae-cd3c-4efc-a282-44c1da677304.xml?rev=6f6f25f3a9bb1e84d782673e17f7739f64e982bb\",\"contentType\": \"x-hrb-application/pos\",\"metadata\": {\"x-hrb-office-id\": \"87971\",\"x-hrb-audit-id\": \"063eb0eb-d89e-4b6a-a962-cb0b8a939fcb\",\"x-hrb-payment-status\": \"PAID\",\"x-hrb-guid\": \"81463728-c1a5-46a5-9acc-4a73b5e1b7f3\",\"x-hrb-content-version\": \"3\",\"x-hrb-payment-balancedue\": \"200\"}},\"lineItems\": [{\"returnId\": \"81463728-c1a5-46a5-9acc-4a73b5e1b7f3\",\"paymentStatus\": \"PAID\",\"balanceDue\": 200.0}],\"createdDateTime\": {\"$date\": \"2016-04-11T16:36:20.070Z\"}},\"filing\": {\"resources\": [],\"filingId\": \"40c509f4-3dea-4521-b0a8-83d48dcd352e\",\"entities\": {\"81463728-c1a5-46a5-9acc-4a73b5e1b7f3\": {\"resources\": [],\"agency\": \"US\",\"guid\": \"81463728-c1a5-46a5-9acc-4a73b5e1b7f3\",\"creationDate\": {\"$date\": \"2016-04-11T16:36:20.472Z\"},\"method\": \"ELECTRONIC\",\"filingStatus\": \"PENDING_TRANSMISSION\",\"amendment\": false,\"extension\": false,\"t\": \"com.hrb.esg.ods.domain.tax.FilingEntity\"}},\"filingTime\": {\"$date\": \"2016-04-11T16:36:20.472Z\"},\"status\": \"CREATED\",\"env\": \"TS15_DIGQA\",\"t\": \"com.hrb.esg.ods.domain.tax.Filing\"},\"attachmentRequests\": []}}]}");
		System.out.println(jsonObj.get("events"));

	}

	public static void main1(String[] args) {

		conf.set("hbase.zookeeper.quorum", "master.bigdata.labs,slave3.bigdata.labs,slave1.bigdata.labs");
		conf.set("hbase.zookeeper.property.clientPort", "2181");
		ResultScanner rscan = null;
		Connection connect;
		try {
			connect = ConnectionFactory.createConnection(conf);
			Table table = connect.getTable(TableName.valueOf("jsondoc"));
			Scan scan = new Scan();
			rscan = table.getScanner(scan);
		} catch (Exception e) {

		}

		for (Result res : rscan) {
			System.out.println(res);
			System.out.println(Bytes.toString(res.getValue("doc".getBytes(), "doc".getBytes())));

		}

	}
}
