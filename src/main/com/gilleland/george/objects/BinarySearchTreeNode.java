package com.gilleland.george.objects;

/**
 * Created by Wes Gilleland on 11/4/2015.
 */
public class BinarySearchTreeNode<V extends Comparable<V>> implements Comparable<V> {
    /**
     * The left-hand child of this node
     */
    protected BinarySearchTreeNode<V> left = null;
    /**
     * The right-hand child of this node
     */
    protected BinarySearchTreeNode<V> right = null;
    /**
     * The data stored on this node
     */
    protected V data;
    /**
     * How many instances of this data are on this node
     */
    protected long count = 1;

    /**
     * Initializes a new node with initial data
     *
     * @param data The data to set on this node
     */
    public BinarySearchTreeNode(V data) {
        this.data = data;
    }

    /**
     * Safe getter method for the data on this node
     *
     * @return The data on this node
     */
    public V getData() {
        return data;
    }

    /**
     * Safe setter method for the data on this node
     *
     * @param data The data to set on this node
     */
    public void setData(V data) {
        this.data = data;
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

    /**
     * Convenience method to return a string representation
     * of the node
     *
     * @return A string representation of the node's value
     */
    @Override
    public String toString() {
        return String.format("%s", this.data.toString());
    }
}
