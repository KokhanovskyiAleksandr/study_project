import org.xml.sax.SAXException;
import serialize.XMLSerializer;
import tree.ChildrenList;
import tree.MyNode;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

import java.util.function.Predicate;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException, ClassNotFoundException {

        XMLSerializer xmlSerializer = new XMLSerializer();

        MyNode myNode = xmlSerializer.deserialize(new File("tmp.xml"));
        System.out.println(myNode);
        xmlSerializer.serialize(myNode, new File("tmp1.xml"));

       /* myNode.nameWideSearch("title");
        myNode.attrWideSearch("price", "20.00");

        ChildrenList childrenList = (ChildrenList) myNode.getChildren();
        myNode.nameDepthSearch("title",childrenList);
        myNode.attrDepthSearch("id","001",childrenList);*/


        String key = "k";
        String value = "lol";

        Predicate<MyNode> pred = (n) -> value.equals(n.getName());
        Predicate<MyNode>pred2 = (n) -> n.getAttr().get(key).equals(value);



    }
}
