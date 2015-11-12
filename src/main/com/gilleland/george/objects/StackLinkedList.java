package com.gilleland.george.objects;

import java.util.EmptyStackException;

/**
 * Created by Wes Gilleland on 11/2/2015.
 */
public class StackLinkedList {
    /**
     * The head node for the underlying Linked List
     */
    public StackLink head;
    /**
     * The number of elements currently in the stack
     */
    private int size = 0;

    /**
     * Implementation of a Stack using an underlying Linked List.
     * This creates a blank stack and sets the head to null.
     */
    public StackLinkedList() {
        this.head = null;
    }

    /**
     * <p>Implementation of a Stack using an underlying Linked List.
     * Through the magic of Java's autoboxing and varargs, this can take any
     * initial values for the stack (for Stacks homework, expect Character or Number
     * data (or their primitives)), in the order that they will be inserted into the stack.
     * </p>
     * <p>
     * For example, StackLinkedList(12, '+', 3) will make a stack with the following structure:
     * </p>
     * <pre>
     * |  3  | TOP
     * | '+' |
     * | 12  | BOTTOM
     * -------
     * </pre>
     *
     * @param data Any number of arguments of any {@link Object} type to be added to the stack
     */
    public StackLinkedList(Object... data) {
        for (Object d : data) {
            this.push(d);
        }
    }

    /**
     * <p>A simple range check on the current size of the stack to determine if it's empty</p>
     *
     * @return Is this stack empty?
     */
    public boolean isEmpty() {
        return this.size <= 0;
    }

    /**
     * <p>Push new data to the top of the stack.</p>
     * <p>Increases the size by 1</p>
     *
     * @param data Number or Character data (or their primitives). Yay for Java's
     *             autoboxing!
     */
    public void push(Object data) {
        this.head = new StackLink<>(data, this.head);
        this.size++;
    }

    /**
     * <p>Return but do not remove the data from the top of the stack.</p>
     * <p>Does not decrease the size.</p>
     * <p>Note, you will have to do your own type checks for this. For the Stacks homework, expect data
     * of either Number or Character types</p>
     *
     * @return The data from the top of the stack
     * @throws EmptyStackException
     */
    public Object peek() throws EmptyStackException {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        return this.head.getData();
    }

    /**
     * <p>Returns the data on the top of the stack and removes it from the stack.</p>
     * <p>Decrements the size by 1.</p>
     * <p>Note, you will have to do your own type checks for this. For the Stacks homework, expect data
     * of either Number or Character types</p>
     *
     * @return The data on the top of the stack
     * @throws EmptyStackException
     */
    public Object pop() throws EmptyStackException {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        StackLink temp = this.head;

        this.head = this.head.getNext();
        this.size--;
        return temp.getData();
    }

    /**
     * <p>Get the current size of the stack (number of elements currently in the stack)</p>
     *
     * @return The current size of the stack
     */
    public int getSize() {
        return this.size;
    }
}
