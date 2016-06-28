package test;

import java.util.ArrayList;
import java.util.List;

public class Test128Char {

	public static void main(String[] args) {
		List<String> a = new ArrayList<String>();

		a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_Attr_version");
		a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_Attr_xmlns:xllxllxllxllxllxllxllxllxllxllxll");
		a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_xllxllxllxllxllxllxllxllxllxllxll:output_Attr_encoding");
		a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_xllxllxllxllxllxllxllxllxllxllxll:output_Attr_indent");
		a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_xllxllxllxllxllxllxllxllxllxllxll:output_Attr_method");
		a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_xllxllxllxllxllxllxllxllxllxllxll:output_Attr_version");
		a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_config_Request_Attr_id");
		a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_config_Request_Attr_name");
		a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_config_Request_requestqueue");
		a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_config_Request_responsequeue");
		a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_config_Request_Attr_name");
		a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_config_Request_requestqueue");
		a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_config_Request_responsequeue");
		a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_xllxllxllxllxllxllxllxllxllxllxll:template_Attr_match");
		a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_xllxllxllxllxllxllxllxllxllxllxll:template_Attr_match");
		a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_xllxllxllxllxllxllxllxllxllxllxll:template_xllxllxllxllxllxllxllxllxllxllxll:element_Attr_name");
		a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_xllxllxllxllxllxllxllxllxllxllxll:template_xllxllxllxllxllxllxllxllxllxllxll:element_xllxllxllxllxllxllxllxllxllxllxll:for-each_Attr_select");
		a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_xllxllxllxllxllxllxllxllxllxllxll:template_xllxllxllxllxllxllxllxllxllxllxll:element_xllxllxllxllxllxllxllxllxllxllxll:for-each_xllxllxllxllxllxllxllxllxllxllxll:attribute_Attr_name");
		a.add("xllxllxllxllxllxllxllxllxllxllxll:stylesheet_xllxllxllxllxllxllxllxllxllxllxll:template_xllxllxllxllxllxllxllxllxllxllxll:element_xllxllxllxllxllxllxllxllxllxllxll:for-each_xllxllxllxllxllxllxllxllxllxllxll:attribute_xllxllxllxllxllxllxllxllxllxllxll:value-of_Attr_select");
		String dummyStr = "";
		for (String a1 : a) {
			dummyStr = getkey(a1, 1);
			if (a1.length() != dummyStr.length()) {
				System.out.println(a1 + " ----> " + a1.length());
				System.out.println(dummyStr + " ----> " + dummyStr.length());
			} 
		}
	}

	public static String getkey(String str, int lastN) {

		if (str.length() >= 128) {
			// System.out.println(str + "--" + lastN + "---> " + str.length());
			String[] split = str.split(":");
			String dummyStr = "";
			int j = (split.length <= lastN) ? 0 : lastN;
			boolean isFirst = true;
			for (int i = j; i < split.length; i++) {
				if (isFirst) {
					dummyStr = split[i];
					isFirst = false;
				} else {
					dummyStr = dummyStr + ":" + split[i];
				}
			}
			if (dummyStr.length() >= 128) {
				dummyStr = getkey(dummyStr, lastN);
			} 
			// System.out.println(dummyStr + " ----> " + dummyStr.length());
			return dummyStr;
		} else {
			// System.out.println(str + " -A---> " + str.length());
			return str;
		}
	}

}
