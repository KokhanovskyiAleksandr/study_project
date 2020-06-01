import org.xml.sax.SAXException;
import serialize.XMLSerializer;
import tree.MyNode;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException, ClassNotFoundException {

        XMLSerializer xmlSerializer = new XMLSerializer();

        MyNode myNode = xmlSerializer.deserialize(new File("tmp.xml"));
        xmlSerializer.serialize(myNode, new File("tmp1.xml"));
    }
}
