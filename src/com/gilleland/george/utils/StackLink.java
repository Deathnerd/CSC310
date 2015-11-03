package com.gilleland.george.utils;

/**
 * Created by Wes Gilleland on 11/2/2015.
 */
public class StackLink<E> {
    /**
     * The data being stored in this link (node) of the stack. Can be any object type,
     * but for the Stacks homework expect {@link Character} or {@link Integer}
     */
    private E data;
    /**
     * The next link/node/element in the Stack and the underlying Linked List
     */
    private StackLink next;

    /**
     * <p>Automagically takes any type of data (but for Stacks homework expect {@link Integer} and {@link Character}
     * or their primitive types) and stores it on this link/node</p>
     *
     * @param data The data to set for this link
     * @see {@link #data}
     */
    public StackLink(E data) {
        this.data = data;
    }

    /**
     * <p>Automagically takes any type of data (but for stacks homework expect {@link Integer} and {@link Character}
     * or their primitive types) and stores it on this link/node. Also sets the next element for this
     * link to point to</p>
     *
     * @param data         The data for this link to hold
     * @param next_element The next element to point this element to
     * @see {@link #data}
     */
    public StackLink(E data, StackLink next_element) {
        this.data = data;
        this.next = next_element;
    }

    /**
     * Accessor method for the next {@link StackLink} that this element is pointing to
     *
     * @return The object that's next in the chain
     */
    public StackLink getNext() {
        return this.next;
    }

    /**
     * Setter method for the next {@link StackLink} that this element is pointing to
     *
     * @param next The StackLink element to set as the next element for this link to point to
     */
    public void setNext(StackLink next) {
        this.next = next;
    }

    /**
     * Accessor method for the data stored on this node
     *
     * @return The data stored on this node
     * @see {@link #data}
     */
    public E getData() {
        return this.data;
    }

    /**
     * Setter method for the data stored on this node
     *
     * @param data The data to store
     * @see {@link #data}
     */
    public void setData(E data) {
        this.data = data;
    }
}
