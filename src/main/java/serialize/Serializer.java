package serialize;

import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import tree.MyNode;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface Serializer {
    void serialize(MyNode myNode, File file) throws TransformerException, IOException, ParserConfigurationException;
    MyNode deserialize(File file) throws ParserConfigurationException, IOException, SAXException;
}
