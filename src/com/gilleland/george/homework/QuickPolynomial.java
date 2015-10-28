package com.gilleland.george.homework;

import com.gilleland.george.utils.TermNotFoundException;
import com.gilleland.george.utils.Utils;

/**
 * A quick and dirty method of making a polynomial parser using a Linked List
 * approach. I tried to make a proper abstracted Linked List Polynomial solution
 * but confused myself and ran out of time. I refuse to delete the code because I
 * intend to go back and fix it, so this will do. This is not very efficient and is
 * based off of old code of mine (that still works!) and is not representative of my
 * current skill when it comes to programming. However, it shows that I have a
 * grasp on the concept of linked lists and can implement them. I have gone through
 * and cleaned up the code by adding expressive method/property names, and adding some
 * helpful comments.
 * <p>
 * Created by Wes Gilleland on 10/27/2015.
 */
public class QuickPolynomial {
    private QuickTerm head;
    public int length = 0;

    /**
     * Constructor. Sets the head to null upon instantiation
     */
    QuickPolynomial() {
        this.head = null;
    }

    /**
     * Prints out the string representation of the current polynomial expression
     */
    void print() {
        Helper helper = new Helper(null, head);
        QuickTerm current = helper.current;
        String polynomial = "";
        while (current != null) {
            polynomial += this.ensureSignage(current.getData()) + "x^" + current.getKey();
            helper.moveNext();
            current = helper.current;
        }
        System.out.printf("The current polynomial is: %s \n", polynomial);
    }

    /**
     * Empty the polynomial
     */
    void empty() {
        this.head = null;
    }

    /**
     * Search for the first node with the matching key
     *
     * @param key The key value to search for
     * @return references to the node and its predecessor if key is found, otherwise
     * will return null
     */
    Helper search(int key) throws TermNotFoundException {
        //search starts from  left to right
        Helper helper = new Helper(null, head);
        while (helper.current != null && helper.current.getKey() >= key) {
            if (key == helper.current.getKey()) {
                return helper;
            }
            helper.moveNext();
        }
        throw new TermNotFoundException();
    }

    /**
     * Insert a node (exponent, coefficient) into the list
     *
     * @param exponent    The exponent for the current term that the node is representing
     * @param coefficient The coefficient for the current term that the node is representing
     */
    void insert(int exponent, String coefficient) {
        Helper helper = new Helper(null, head);

        while (helper.current != null && exponent < helper.current.getKey()) {
            helper.moveNext();
        }

        QuickTerm newTerm = new QuickTerm(exponent, coefficient);
        if (helper.current != null && helper.current.getKey() == exponent) {
            helper.current.setKey(exponent);
            helper.current.setData(coefficient);
        } else if (helper.previous == null) {       //new node should become the first in the list
            newTerm.setNext(head);
            this.head = newTerm;
        } else {
            helper.previous.setNext(newTerm);
            newTerm.setNext(helper.current);
        }
        this.length++;
    }

    /**
     * Takes in a term's components (coefficient and exponent) and adds them (arithmetically)
     * to the current polynomial linked list. If such an operation would produce a 0 coefficient,
     * then the representative term is deleted from the linked list
     *
     * @param exponent    The exponent of the term
     * @param coefficient The coefficient of the term
     */
    void addition(int exponent, String coefficient) {
        Helper helper = new Helper(null, this.head);
        coefficient = this.ensureSignage(coefficient);
        while (helper.current != null) {
            if (helper.current.getKey() == exponent) {
                coefficient = String.valueOf(Integer.parseInt(helper.current.getData()) + Integer.parseInt(coefficient));
                if (Integer.parseInt(coefficient) == 0) {
                    try {
                        this.delete(helper.current.getKey());
                    } catch (TermNotFoundException e) {
                        //
                    }
                    return;
                }
                helper.current.setData(this.ensureSignage(coefficient));
                return;
            }
            helper.moveNext();
        }
        this.insert(exponent, coefficient);
    }

    /**
     * Takes in a term's components (coefficient and exponent) and subtracts them (arithmetically)
     * to the current polynomial linked list. If such an operation would produce a 0 coefficient,
     * then the representative term is deleted from the linked list
     *
     * @param exponent    The exponent of the term
     * @param coefficient The coefficient of the term
     */
    void subtraction(int exponent, String coefficient) {
        Helper helper = new Helper(null, head);
        coefficient = this.ensureSignage(coefficient);

        while (helper.current != null) {
            if (helper.current.getKey() == exponent) {
                coefficient = String.valueOf(Integer.parseInt(helper.current.getData()) - Integer.parseInt(coefficient));
                if (Integer.parseInt(coefficient) == 0) {
                    try {
                        this.delete(helper.current.getKey());
                    } catch (TermNotFoundException e) {
                        //
                    }
                    return;
                }
                helper.current.setData(this.ensureSignage(coefficient));
                return;
            }
            helper.moveNext();
        }
        this.insert(exponent, this.ensureSignage("" + (Integer.parseInt(coefficient) * (-1))));
    }

    /**
     * Deletes the first occurrence of a node whose key (exponent) matches the
     * one passed.
     *
     * @param exponent The exponent (key) to search by
     */
    void delete(int exponent) throws TermNotFoundException {
        Helper helper = this.search(exponent);

        final QuickTerm next = helper.current.getNext();
        final QuickTerm prev = helper.previous;

        if (prev != null) {
            prev.setNext(next);
        } else {
            this.head = next;
        }

        this.length--;
    }

    /**
     * Ensures that there are signs in front of the coefficients
     *
     * @param coefficient The (possibly) unsigned number as a string
     * @return The signed number as a string
     */
    private String ensureSignage(String coefficient) {
        return (!Utils.isOperator(coefficient.charAt(0))) ? "+" + coefficient : coefficient;
    }
}

/**
 * For all intents and purposes, a Linked List node. This is a Term
 * (named QuickTerm to not conflict with my unfinished generalized abstraction)
 * representing a term in a polynomial expression (4x^3 for example). Like all nodes,
 * It requires a key (the exponent of the term for this) and some data
 * (the coefficient of the term for this).
 */
class QuickTerm {
    /**
     * The key of the node, which is representative of the
     * exponent in the polynomial expression
     */
    private int key;
    /**
     * The data in the node, which is representative of the
     * coefficient in the polynomial expression
     */
    private String data;
    /**
     * The reference pointing to the next member in the list.
     * Is initialized to null upon term instantiation
     */
    private QuickTerm next;    // reference to the successor

    /**
     * For all intents and purposes, a Linked List node. This is a Term
     * (named QuickTerm to not conflict with my unfinished generalized abstraction)
     * representing a term in a polynomial expression (4x^3 for example). Like all nodes,
     * It requires a key (the exponent of the term for this) and some data
     * (the coefficient of the term for this).
     *
     * @param key  The exponent of the polynomial term
     * @param data The coefficient of the polynomial term
     */
    QuickTerm(int key, String data) {
        this.key = key;
        this.data = data;
        this.next = null;
    }

    /**
     * Accessor method for the key (exponent)
     *
     * @return the key of the term
     */
    int getKey() {
        return this.key;
    }

    /**
     * Accessor method for the data (coefficient)
     *
     * @return the data of the term
     */
    String getData() {
        return this.data;
    }

    /**
     * Accessor method for the next term
     *
     * @return the reference to the next term
     */
    QuickTerm getNext() {
        return this.next;
    }

    /**
     * Updater method for the key (exponent)
     *
     * @param key The new value of the key
     */
    void setKey(int key) {
        this.key = key;
    }

    /**
     * Updater method for the data (coefficient)
     *
     * @param data The new value for the data
     */
    void setData(String data) {
        this.data = data;
    }

    /**
     * Updater method for the next term reference
     *
     * @param next the new term to point to as next
     */
    void setNext(QuickTerm next) {
        this.next = next;
    }
}

/**
 * A convenience object that holds reference to a node in a Linked List
 * and provides a method for traversing (one direction) through
 * the linked list. Not needed but I'm admittedly too lazy to refactor the
 * above code to not need it
 */
class Helper {
    /**
     * Reference to the previous node
     */
    public QuickTerm previous;
    /**
     * Reference to the current node
     */
    public QuickTerm current;

    /**
     * A convenience object that holds reference to a node in a Linked List
     * and provides a method for traversing (one direction) through
     * the linked list. Not needed but I'm admittedly too lazy to refactor the
     * above code to not need it
     *
     * @param previous Set a previous node
     * @param current  Set the current node
     */
    Helper(QuickTerm previous, QuickTerm current) {
        this.previous = previous;
        this.current = current;
    }

    /**
     * Advance the pointer of the current node to the next node in the
     * reference chain
     */
    void moveNext() {
        if (this.current != null) {
            this.previous = this.current;
            this.current = this.current.getNext();
        }
    }
}