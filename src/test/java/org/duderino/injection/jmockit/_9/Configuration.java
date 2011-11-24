package org.duderino.injection.jmockit._9;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class Configuration {
    private Map<String, String> map = new HashMap<String, String>();
    private final String path;

    public Configuration(String path) throws Exception {
        this.path = path;

        reload();
    }

    public String get(String key) {
        return map.get(key);
    }

    public void reload() throws Exception {
        map.clear();

        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        Document document = docBuilder.parse(new FileInputStream(path));

        NodeList settings = document.getElementsByTagName("setting");

        for (int i = 0; i < settings.getLength(); ++i) {
            Node setting = settings.item(i);

            NodeList children = setting.getChildNodes();

            String key = null;
            String value = null;

            for (int j = 0; j < children.getLength(); ++j) {
                Node child = children.item(j);

                if ("key".equals(child.getNodeName())) {
                    key = child.getNodeValue();
                    continue;
                }

                if ("value".equals(child.getNodeName())) {
                    value = child.getNodeValue();
                    continue;
                }
            }

            if (null != key) {
                map.put(key, value);
            }
        }
    }
}
