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
 *
 * @author Wes Gilleland
 */
public class QuickPolynomial {
    public int length = 0;
    private QuickTerm head;

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
        Helper helper = new Helper(null, this.head);
        String polynomial = "";
        while (helper.current != null) {
            polynomial += this.ensureSignage(helper.current.getData()) + "x^" + helper.current.getKey();
            helper.moveNext();
        }
        System.out.printf("The current polynomial is: %s \n", polynomial);
    }

    /**
     * Empty the polynomial linked list by destroying the head of the chain
     */
    void empty() {
        this.head = null;
    }

    /**
     * Search for the first node with the matching key and return it.
     *
     * @param key The key value to search for
     * @return references to the node and its predecessor if key is found, otherwise
     * will return null
     * @throws TermNotFoundException when the search is exhausted
     */
    Helper search(int key) throws TermNotFoundException {
        Helper helper = new Helper(null, this.head);

        /*
         * Starting at the beginning, iterate over the linked list's
         * structure, checking for the key given. If it's found, return
         * the node as a Helper object. Otherwise, throw a TermNotFoundException
         */
        while (helper.current != null) {
            if (key == helper.current.getKey()) {
                return helper;
            }
            helper.moveNext();
        }
        throw new TermNotFoundException();
    }

    /**
     * Insert a node (exponent, coefficient) into the list. This will also take care of
     * inserting new data into existing keys
     *
     * @param exponent    The exponent for the current term that the node is representing
     * @param coefficient The coefficient for the current term that the node is representing
     */
    void insert(int exponent, String coefficient) {
        Helper helper = new Helper(null, this.head);

        /*
         * From the start of the linked list, search for the term that has the key we're looking for
         * while also making sure that the insertion order of greatest key to least key is preserved
         */
        while (helper.current != null && exponent < helper.current.getKey()) {
            helper.moveNext();
        }

        // Make the new term to insert
        QuickTerm newTerm = new QuickTerm(exponent, coefficient);
        /*
         * Here's where the magic happens. First, check if the node we stopped at is not null to prevent
         * null pointer exceptions, and that its key is equal to our given key. If so, then update its
         * data with the new value.
         *
         * If the above fails and we're at the head of the non-empty linked list (helper.previous == null),
         * then set the reference for our new term's next pointer to our current head and set the head of
         * our linked list to the new term. This will in effect insert the new term (node) into the beginning
         * of our list.
         *
         * If both of those aren't conditions to be met, then we're inserting our new term into the space between
         * two existing nodes in the list. Set the previous node's next reference to our new node, and set the
         * new node's next reference pointer to our current term.
         */
        if (helper.current != null && helper.current.getKey() == exponent) {
            helper.current.setData(coefficient);
        } else if (helper.previous == null) {       //new node should become the first in the list
            newTerm.setNext(head);
            this.head = newTerm;
        } else {
            helper.previous.setNext(newTerm);
            newTerm.setNext(helper.current);
        }
        // We've added a new term so we must increase our current size for easy reference
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
        /*
         * Starting with the head, check if the exponent given matches the current node's
         * exponent. If so, then we need to calculate the result of adding the two coefficients
         * together. If that would equal zero, then there's no point for the new coefficient
         * to be in the final result and should be deleted. Otherwise if the key is found and the
         * new coefficient isn't 0, then the current node's data needs to be updated with the new
         * coefficient value.
         */
        while (helper.current != null) {
            if (helper.current.getKey() == exponent) {
                coefficient = String.valueOf(Integer.parseInt(helper.current.getData()) + Integer.parseInt(coefficient));
                if (Integer.parseInt(coefficient) == 0) {
                    this.delete(helper);
                    return;
                }
                helper.current.setData(this.ensureSignage(coefficient));
                return;
            }
            helper.moveNext();
        }

        /*
         * If the end of the list is reached, then a term with that exponent power does not
         * exist and should be inserted as a new term.
         */
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
        Helper helper = new Helper(null, this.head);
        coefficient = this.ensureSignage(coefficient);

        /*
         * Starting at the beginning of the list, iterate over each node. If a node with the
         * given key (exponent) is reached, then calculate the result of subtracting the given
         * coefficient from the current node's coefficient. If that operation would yield a result
         * of 0, then the current node should be deleted (de-referenced) from the link chain. Otherwise
         * if the result was non-zero, set the current node's data to the result's value.
         */
        while (helper.current != null) {
            if (helper.current.getKey() == exponent) {
                coefficient = String.valueOf(Integer.parseInt(helper.current.getData()) - Integer.parseInt(coefficient));
                if (Integer.parseInt(coefficient) == 0) {
                    this.delete(helper);
                    return;
                }
                helper.current.setData(this.ensureSignage(coefficient));
                return;
            }
            helper.moveNext();
        }
        /*
         * If the end of the list is reached, then a term with the exponent given does not
         * exist and should be inserted as a new term. However, since this is subtraction, the
         * coefficient should be inverted before being inserted.
         */
        this.insert(exponent, this.ensureSignage("" + (Integer.parseInt(coefficient) * (-1))));
    }

    /**
     * Deletes the first occurrence of a node whose key (exponent) matches the
     * one passed. Calls {@link #search(int)} to find the term to delete
     * and does not encapsulate the TermNotFoundException thrown by it. This was
     * done because you should be aware that you might be attempting to delete,
     * which is technically an access, a node that's not there. Opening up the black
     * box for this does not violate OOP standards.
     *
     * @param exponent The exponent (key) to search by
     * @throws TermNotFoundException if trying to delete a term that doesn't exist with the given key
     */
    public void delete(int exponent) throws TermNotFoundException {
        /*
         * Search for the term in the linked list by exception. Will throw a
         * TermNotFoundException if it cannot find one.
         */
        this.delete(this.search(exponent));
    }

    /**
     * Given a node, delete it from the linked list and decrement the count
     *
     * @param node The node to delete
     */
    public void delete(Helper node) {
        // hold the current node's previous and next references
        final QuickTerm next = node.current.getNext();
        final QuickTerm prev = node.previous;

        /*
         * If the previous node reference is not null, then we can just override
         * its next pointer to point to the next pointer pointed by current's next
         * pointer. This will effectively skip over the current node and leave it
         * dangling in the reference chain in memory for the garbage collector to
         * clean up.
         *
         * Otherwise, if the previous pointer is null then we're at the first element
         * and and we can just replace our head element with the current element's next element.
         * This will also leave the current node unreachable and dangling for the
         * garbage collector to clean up.
         */
        if (prev != null) {
            prev.setNext(next);
        } else {
            this.head = next;
        }
        // Decrement the member counter on successful deletion
        this.length--;
    }

    /**
     * Ensures that there are signs in front of the coefficients.
     * <p>
     * For example, given that you pass a string "4", then "+4" will
     * be returned. Does not need to be concerned with 0 because it is never
     * called under a situation where it can have a 0 passed to it or where
     * returning a +0 will be of a concern because if a coefficient is 0 then
     * the node is deleted from the linked list (polynomial). Strings with
     * "-" as the first character will fall through
     *
     * @param coefficient The (possibly) unsigned number as a string
     * @return The signed number as a string
     */
    private String ensureSignage(String coefficient) {
        return (!Utils.isPlusOrMinus(coefficient.charAt(0))) ? "+" + coefficient : coefficient;
    }

    /**
     * A simple check on the length property to determine if the linked list is empty
     *
     * @return Whether the length of the linked list is 0 or not
     */
    public boolean isEmpty() {
        return this.length == 0;
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
     * Updater method for the key (exponent)
     *
     * @param key The new value of the key
     */
    void setKey(int key) {
        this.key = key;
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
     * Updater method for the data (coefficient)
     *
     * @param data The new value for the data
     */
    void setData(String data) {
        this.data = data;
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