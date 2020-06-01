package tree;


import exception.ParentException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ChildrenList extends ArrayList<MyNode> {
    private MyNode parent;


    public ChildrenList(MyNode parent) {
        this.parent = parent;
    }


    @Override
    public boolean add(MyNode node) {
        if (parent != null) {
            node.setParent(parent);
            node.setRoot(getRoot(node));
            return super.add(node);
        } else
            throw new ParentException("you have a parent");
    }

    @Override
    public boolean addAll(Collection<? extends MyNode> c) {
        for (MyNode tmpNode : c) {
            tmpNode.setParent(parent);
            tmpNode.setRoot(getRoot(tmpNode));
        }
        return super.addAll(c);
    }

    private MyNode getRoot(MyNode myNode) {
        if (myNode.isRoot()) return myNode;
        return getRoot(myNode.getParent());
    }

}
