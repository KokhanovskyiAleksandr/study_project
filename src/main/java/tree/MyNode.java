package tree;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.Predicate;


public class MyNode {
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

    public MyNode depthSearch(String key, String value) {
        Predicate<MyNode> predicate = (n) -> value.equals(n.getAttr().get(key));
        return DS(predicate);
    }

    public MyNode depthSearch(String name) {
        Predicate<MyNode> predicate = (n) -> name.equals(n.getName());
        return DS(predicate);
    }

    private MyNode DS(Predicate<MyNode> predicate) {
        return rec(this, predicate);
    }

    private MyNode rec(MyNode myNode, Predicate predicate) {

        if (predicate.test(myNode)) {
            return myNode;
        }
        if (!myNode.getChildren().isEmpty()) {
            ChildrenList childrenList = (ChildrenList) myNode.getChildren();
            for (MyNode myNode1 : childrenList) {
                MyNode tmp = rec(myNode1, predicate);
                if (tmp != null) {
                    return tmp;
                }
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
        String parentsName = parent != null ? parent.getName() : "Empty";
        return System.lineSeparator() +
                "Node" +
                "{" +
                "name:" + name + ", " +
                "attrs:" + attr + ", " +
                "children:" + children + ", " +
                "parent:" + parentsName +
                "}";
    }
}


