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

//    Predicate<MyNode> search = new Predicate<MyNode>() {
//        @Override
//        public boolean test(MyNode s) {
//            return false;
//        }
//    };



    public MyNode wideSearch(String key, String value) {

        Queue<MyNode> queue = new LinkedList<MyNode>();
        queue.add(this);
        queue.addAll(this.getChildren());
        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {

                if (queue.element().getAttr().containsKey(key) && queue.element().getAttr().containsValue(value)) {
                    System.out.println(queue.element());
                    return queue.element();
                }
                if (!queue.element().getChildren().isEmpty()) {
                    queue.addAll(queue.element().getChildren());
                }
                queue.remove();
            }
        }

        return null;
    }

    public MyNode wideSearch(String name) {
        Queue<MyNode> queue = new LinkedList<MyNode>();
        queue.add(this);
        queue.addAll(this.getChildren());
        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {

                if (queue.element().name.equals(name)) {
                    System.out.println(queue.element());
                    return queue.element();
                }
                if (!queue.element().getChildren().isEmpty()) {
                    queue.addAll(queue.element().getChildren());
                }
                queue.remove();
            }
        }

        return null;
    }

    public MyNode depthSearch(String name, ChildrenList childrenList) {

        for (MyNode myNode : childrenList) {

            if (myNode.getName().equals(name)) {
                System.out.println(myNode + "its");
                return myNode;
            }
            if (!myNode.getChildren().isEmpty()) {
                ChildrenList childrenList1 = (ChildrenList) myNode.getChildren();
                depthSearch(name, childrenList1);
            }

        }
        return null;
    }

    public MyNode depthSearch(String key, String value, ChildrenList childrenList) {

        for (MyNode myNode : childrenList) {

            if (myNode.getAttr().containsKey(key) && myNode.getAttr().containsValue(value)) {
                System.out.println(myNode + "its");
                return myNode;
            }
            if (!myNode.getChildren().isEmpty()) {
                ChildrenList childrenList1 = (ChildrenList) myNode.getChildren();
                depthSearch(key, value, childrenList1);
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


