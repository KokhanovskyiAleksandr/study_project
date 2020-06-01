import org.xml.sax.SAXException;
import serialize.XMLSerializer;
import tree.MyNode;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException, ClassNotFoundException {

        File deserialize = new File(Main.class.getResource("deserialize.xml").getFile());
        File serialize = new File(Main.class.getResource("serialize.xml").getFile());

        XMLSerializer xmlSerializer = new XMLSerializer();
        MyNode myNode = xmlSerializer.deserialize(deserialize);
        xmlSerializer.serialize(myNode, serialize);
    }
}
