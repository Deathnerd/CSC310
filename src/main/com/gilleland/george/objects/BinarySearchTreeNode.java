package com.gilleland.george.objects;

/**
 * Created by Wes Gilleland on 11/4/2015.
 */
public class BinarySearchTreeNode<V extends Comparable<V>> implements Comparable<V> {
    protected BinarySearchTreeNode<V> left = null;
    protected BinarySearchTreeNode<V> right = null;
    protected V data;
    protected long count = 1;

    public BinarySearchTreeNode(V data) {
        this.data = data;
    }

    public V getData() {
        return data;
    }

    public void setData(V data) {
        this.data = data;
    }

    public BinarySearchTreeNode<V> getRight() {
        return right;
    }

    public void setRight(BinarySearchTreeNode<V> right) {
        this.right = right;
    }

    public BinarySearchTreeNode<V> getLeft() {
        return left;
    }

    public void setLeft(BinarySearchTreeNode<V> left) {
        this.left = left;
    }

    /**
     * Do not use. Not complete yet
     *
     * @param o The other thing to compare to
     * @return Stuff
     * @deprecated
     */
    @Override
    public int compareTo(V o) {
        return this.data.compareTo(o);
    }

    @Override
    public String toString() {
        return String.format("%s", this.data.toString());
    }
}
