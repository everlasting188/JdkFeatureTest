package algorithm.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class NTree<T> implements Iterable<NTreeNode<T>> {

    T data;
    NTreeNode<T> parent;
    List<NTreeNode<T>> children;

    public NTree(T data) {
        this.data = data;
        this.children = new LinkedList<NTreeNode<T>>();
    }

    public NTreeNode<T> addChild(T child) {
        NTreeNode<T> childNode = new NTreeNode<T>(child);
        childNode.parent = this;
        this.children.add(childNode);
        return childNode;
    }

	@Override
	public Iterator<NTreeNode<T>> iterator() {
		return null;
	}
}