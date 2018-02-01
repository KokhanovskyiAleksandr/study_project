package tree;


import java.util.*;
import java.util.function.Predicate;

import org.apache.log4j.Logger;


public class MyNode {

    final static Logger logger = Logger.getLogger(MyNode.class);

    private MyNode root;
    private MyNode parent;
    private String name;
    private Map<String, String> attr;
    private List<MyNode> children;


    public MyNode(String name) {


        this.parent = null;

        this.children = new ChildrenList(this);

        this.attr = new HashMap<String, String>();

        this.name = name;
    }


    //todo predicate

    public MyNode wideSearch(String name) {
        Predicate<MyNode> predicate = (n) -> name.equals(n.getName());
        return WS(predicate);
    }

    public MyNode wideSearch(String key, String value) {
        Predicate<MyNode> predicate = (n) -> value.equals(n.getAttr().get(key));
        return WS(predicate);
    }

    private MyNode WS(Predicate<MyNode> predicate) {

        Queue<MyNode> queue = new LinkedList<MyNode>();
        queue.add(this);

        while (!queue.isEmpty()) {
            if (predicate.test(queue.element())) {
                return queue.element();
            } else {
                queue.addAll(queue.element().getChildren());
            }
            queue.remove();
        }
        return null;
    }

    private MyNode DS(Predicate<MyNode> predicate){
        return null;
    }

    public MyNode dept(String name){
        rec(name,this);
        return null;
    }

    private MyNode rec(String name, MyNode myNode) {

            if (myNode.getName().equals(name)) {
                System.out.println(myNode);
                return myNode;
            }
            if (!myNode.getChildren().isEmpty()) {
                ChildrenList childrenList = (ChildrenList) myNode.getChildren();
                for (MyNode myNode1 : childrenList) {
                rec(name,myNode1);
                }
            }
        return null;
    }




    public void addChild(MyNode myNode) {
        children.add(myNode);
    }

    public boolean isLeaf() {

        if (this.children.size() == 0)
            return true;

        else
            return false;
    }

    public boolean isRoot() {
        return (this.parent == null);
    }

    public void showTree() {

        System.out.println(this.toString());

        if (!this.children.isEmpty()) {
            for (int i = 0; i < this.children.size(); i++) {
                this.children.get(i).showTree();
            }
        }
    }

    public void addAttribute(String key, String value) {
        attr.put(key, value);
    }

    public void deleteChild(MyNode myNode) {
        children.remove(myNode);

    }

    //todo foreach
    public MyNode getRoot() {
        return root;

    }

    public void setParent(MyNode parent) {
        this.parent = parent;
    }

    public MyNode getParent() {
        return parent;
    }

    public void setName(String name) {
        this.name = name;

    }

    public void setRoot(MyNode root) {

        if (root == null) {
            this.root = this;
            return;
        }

        this.root = root;

        for (int i = 0; i < this.getChildren().size(); i++) {
            this.getChildren().get(i).setRoot(root);
        }
    }

    public String getName() {

        return name;

    }

    public void setAttr(Map<String, String> attr) {
        this.attr = attr;
    }

    public Map<String, String> getAttr() {
        return attr;
    }

    public List<MyNode> getChildren() {
        return children;
    }

    @Override
    public String toString() {

        String parentsName = null;
        if (parent != null) {
            parentsName = parent.getName();
        }
        return "Node" + "{" + "name:" + name + ", attrs:" + attr + ", children:" + children +
                ", parrent:" + parentsName + "}";
    }


}


