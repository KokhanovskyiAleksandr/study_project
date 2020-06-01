
package serialize;

import org.xml.sax.SAXException;
import tree.MyNode;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

public class SerializableManager {

    private Serializer serializer;

    public void setStrategy(Serializer serializer) {
        this.serializer = serializer;
    }

    public void serialize(MyNode node, File file) throws IOException, TransformerException, ParserConfigurationException {
        serializer.serialize(node, file);
    }

    public MyNode deserialize(File file) throws IOException, SAXException, ParserConfigurationException {
        return serializer.deserialize(file);
    }

}