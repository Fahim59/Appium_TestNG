package utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class ConfigLoader {

    private Properties properties;

    public Properties initializeProperty() {
        properties = new Properties();
        try {
            FileInputStream ip = new FileInputStream("src/test/resources/config.properties");
            properties.load(ip);

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    public HashMap<String, String> parseStringXML() throws Exception{
        HashMap<String, String> stringMap = new HashMap<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("src/test/resources/Strings.xml");
            document.getDocumentElement().normalize();
            NodeList nList = document.getElementsByTagName("string");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node node = nList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    stringMap.put(eElement.getAttribute("name"), eElement.getTextContent());
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return stringMap;
    }
}