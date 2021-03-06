package serialize;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import tree.ChildrenList;
import tree.MyNode;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class XMLSerializer implements Serializer {
    private DocumentBuilderFactory factory;
    private Document document;

    @Override
    public void serialize(MyNode myNode, File file) throws IOException, ParserConfigurationException, TransformerException {

        factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.newDocument();
        appendNode(document, myNode);

        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(document), new StreamResult(new FileOutputStream(file)));

    }

    private Element appendNode(Document document, MyNode myNode) {
        Element currentElement = document.createElement(myNode.getName());

        Map<String, String> attrs = myNode.getAttr();
        for (Map.Entry<String, String> entry : attrs.entrySet()) {
            String name = entry.getKey();
            String value = entry.getValue();
            Attr attr = document.createAttribute(name);
            attr.setValue(value);
            currentElement.setAttributeNode(attr);
        }
        if (myNode.getParent() == null) {
            document.appendChild(currentElement);
            appendChildren(document, myNode, currentElement);
        } else {
            appendChildren(document, myNode, currentElement);
        }
        return currentElement;
    }

    private void appendChildren(Document document, MyNode myNode, Element element) {
        ChildrenList children = (ChildrenList) myNode.getChildren();
        for (MyNode child : children) {
            Element el = appendNode(document, child);
            element.appendChild(el);
        }
    }


    @Override
    public MyNode deserialize(File file) throws ParserConfigurationException, IOException, SAXException {

        factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse(file);

        MyNode root = new MyNode(document.getDocumentElement().getNodeName());
        NodeList nodeList = document.getChildNodes().item(0).getChildNodes();
        recGetNodeList(nodeList, root);

        return root;
    }

    private void recGetNodeList(NodeList nodeList, MyNode myNode) {

        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i) instanceof Element) {
                MyNode temp = new MyNode(nodeList.item(i).getNodeName());
                myNode.addChild(temp);

                if (nodeList.item(i).hasAttributes()) {
                    NamedNodeMap attributes = nodeList.item(i).getAttributes();
                    addAttribute(attributes, temp);
                }
                if (nodeList.item(i).hasChildNodes()) {
                    recGetNodeList(nodeList.item(i).getChildNodes(), temp);
                }
            }
        }
    }

    private void addAttribute(NamedNodeMap attributes, MyNode myNode) {

        for (int j = 0; j < attributes.getLength(); j++) {
            Node atribute = attributes.item(j);
            String name = atribute.getNodeName();
            String value = atribute.getNodeValue();

            Map<String, String> attr = new HashMap<String, String>();
            attr.put(name, value);

            myNode.setAttr(attr);
        }
    }
}

