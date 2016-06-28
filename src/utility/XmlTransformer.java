package utility;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

public class XmlTransformer {
	public static void main(String[] args) {
		try {
			File inputFile = new File("D:\\workspace\\test\\dataFiles\\xslt.xslt");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList childNodes = doc.getChildNodes();
			printChildNodes(null, childNodes);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void printChildNodes(String parentKey, NodeList childNodes) {

		for (int temp = 0; temp < childNodes.getLength(); temp++) {
			Node nNode = childNodes.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				String childKey = eElement.getTagName();
				String newKey = ((null == parentKey) ? childKey : parentKey + "_" + childKey);
				if (null != eElement.getFirstChild() && null != eElement.getFirstChild().getNodeValue()
						&& !eElement.getFirstChild().getNodeValue().trim().isEmpty()) {
					System.out.println(newKey + " = " + eElement.getFirstChild().getNodeValue());

				}
				NamedNodeMap attribute = eElement.getAttributes();
				for (int i = 0; i < attribute.getLength(); i++) {
					System.out.println(newKey + "_Attr_" + attribute.item(i).getNodeName() + " = "
							+ attribute.item(i).getNodeValue());
				}
				NodeList elementChildNodes = eElement.getChildNodes();
				printChildNodes(newKey, elementChildNodes);

			}
		}
	}
}
