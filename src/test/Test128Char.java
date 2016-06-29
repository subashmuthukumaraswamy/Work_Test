package test;

import java.util.ArrayList;
import java.util.List;

public class Test128Char {

	public static void main(String[] args) {
		List<String> a = new ArrayList<String>();

		// a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_Attr_version");
		// a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_Attr_xmlns:xllxllxllxllxllxllxllxllxllxllxll");
		// a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_xllxllxllxllxllxllxllxllxllxllxll:output_Attr_encoding");
		// a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_xllxllxllxllxllxllxllxllxllxllxll:output_Attr_indent");
		// a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_xllxllxllxllxllxllxllxllxllxllxll:output_Attr_method");
		// a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_xllxllxllxllxllxllxllxllxllxllxll:output_Attr_version");
		// a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_config_Request_Attr_id");
		// a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_config_Request_Attr_name");
		// a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_config_Request_requestqueue");
		// a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_config_Request_responsequeue");
		// a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_config_Request_Attr_name");
		// a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_config_Request_requestqueue");
		// a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_config_Request_responsequeue");
		// a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_xllxllxllxllxllxllxllxllxllxllxll:template_Attr_match");
		// a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_xllxllxllxllxllxllxllxllxllxllxll:template_Attr_match");
		// a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_xllxllxllxllxllxllxllxllxllxllxll:template_xllxllxllxllxllxllxllxllxllxllxll:element_Attr_name");
		// a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_xllxllxllxllxllxllxllxllxllxllxll:template_xllxllxllxllxllxllxllxllxllxllxll:element_xllxllxllxllxllxllxllxllxllxllxll:for-each_Attr_select");
		// a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_xllxllxllxllxllxllxllxllxllxllxll:template_xllxllxllxllxllxllxllxllxllxllxll:element_xllxllxllxllxllxllxllxllxllxllxll:for-each_xllxllxllxllxllxllxllxllxllxllxll:attribute_Attr_name");
		// a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_xllxllxllxllxllxllxllxllxllxllxll:template_xllxllxllxllxllxllxllxllxllxllxll:element_xllxllxllxllxllxllxllxllxllxllxll:for-each_xllxllxllxllxllxllxllxllxllxllxll:attribute_xllxllxllxllxllxllxllxllxllxllxll:value-of_Attr_select");
		a.add("user_admin_subashWork_workflow_sql_import_table_data_part_m_00006_avro_user_admin_subashWork_workflow_sql_import_table_data_part_m_00006");
		String dummyStr = "";
		for (String a1 : a) {
			dummyStr = getkey(a1, 1, 128, "_");
			if (a1.length() != dummyStr.length()) {
				System.out.println(a1 + " ----> " + a1.length());
				System.out.println(dummyStr + " ----> " + dummyStr.length());
			}
		}
	}

	public static String getkey(String str, int lastN, int maxSize, String delimitedText) {
		if (str.length() >= maxSize) {
			// System.out.println(str + "--" + lastN + "---> " + str.length());
			String[] split = str.split(delimitedText+"");
			String dummyStr = "";
			int j = (split.length <= lastN) ? 0 : lastN;
			boolean isFirst = true;
			for (int i = j; i < split.length; i++) {
				if (isFirst) {
					dummyStr = split[i];
					isFirst = false;
				} else {
					dummyStr = dummyStr + delimitedText + split[i];
				}
			}
			
			
			if (dummyStr.length() >= 128 && dummyStr.contains("_")) {
				dummyStr = getkey(dummyStr, lastN, maxSize, delimitedText);
			} else if (dummyStr.length() >= maxSize && !dummyStr.contains("_")) {
				dummyStr = dummyStr.substring(0, maxSize - 1);
			}
			// System.out.println(dummyStr + " ----> " + dummyStr.length());
			return dummyStr;
		} else {
			// System.out.println(str + " -A---> " + str.length());
			return str;
		}
	}

}
