package com.gilleland.george.objects;

import com.gilleland.george.exceptions.ImproperInsertionOrderException;
import com.gilleland.george.exceptions.InvalidPolynomialExpressionException;
import com.gilleland.george.exceptions.NodeNotFoundException;
import com.gilleland.george.interfaces.LinkListInterface;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wes Gilleland on 10/25/2015.
 */
public class Polynomial implements LinkListInterface {
    private final RegularExpression matcher = new RegularExpression("(\\+|-)(\\d)+(x(\\^\\d)?)?");
    public Term head = null;
    public String expression;
    public List<String> parts = new ArrayList<>();

    public Polynomial(String expression) throws InvalidPolynomialExpressionException {
        this.setExpression(expression);
        this.validateExpression();

        for (String term : this.parts) {
            String[] t = term.split("x\\^");
            this.insert(Integer.parseInt(t[0]), Integer.parseInt(t[1]));
        }
    }

    private void validateExpression() throws InvalidPolynomialExpressionException {
        if (this.getExpression().charAt(0) != '-' && this.getExpression().charAt(0) != '+') {
            this.setExpression(null);
            throw new InvalidPolynomialExpressionException();
        }

        int j = 0;
        for (int i = 1; i < this.getExpression().length(); i++) {
            if (this.getExpression().charAt(i) == '-' || this.getExpression().charAt(i) == '+') {
                String t = this.getExpression().substring(j, i);
                j = i;
                if (!this.isValidPart(t)) {
                    this.setExpression(null);
                    throw new InvalidPolynomialExpressionException();
                }
                this.getParts().add(t);
            }
        }
        String t = this.getExpression().substring(j);
        if (!this.isValidPart(t)) {
            this.setExpression(null);
            throw new InvalidPolynomialExpressionException();
        }
        this.getParts().add(t);
    }

    /**
     * A simple null check on the head term. If null, then the list is assumed empty
     *
     * @return Is the head null?
     */
    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    /**
     * Attempts to insert a polynomial term into the first position of the list. If inserting at head would
     * break sequential order, then an error is thrown
     *
     * @param data_items Requires two integer parameters: coefficient, and exponent (in that order) to be
     *                   passed to the underlying term object
     * @throws ImproperInsertionOrderException
     * @see Term
     */
    @Override
    public void insertFirst(int... data_items) throws ImproperInsertionOrderException {
        Term newTerm = new Term(data_items[0], data_items[1]);
        if (newTerm.getExponent() <= this.head.getExponent()) {
            throw new ImproperInsertionOrderException();
        }
        newTerm.next = this.head;
        this.head = newTerm;
    }

    /**
     * Attempts to insert a polynomial term into the last position of the list. If inserting at last would
     * break sequential order, then an error is thrown
     *
     * @param data_items Requires two integer parameters: coefficient, and exponent (in that order) to be
     *                   passed to the underlying term object
     * @throws ImproperInsertionOrderException
     * @see Term
     */
    @Override
    public void insertLast(int... data_items) throws ImproperInsertionOrderException {
        if (this.isEmpty()) {
            this.insertFirst(data_items[0], data_items[1]);
            return;
        }
        Term newTerm = new Term(data_items[0], data_items[1]);
        Term last;
        try {
            last = (Term) this.findNode(-1);
        } catch (NodeNotFoundException e) {
            // Always succeeds
            return;
        }
        if (newTerm.getExponent() > last.getExponent()) {
            throw new ImproperInsertionOrderException();
        }
        last.next = newTerm;
    }

    /**
     * Inserts a polynomial term (node) into the linked list, keeping sequential order indexed by
     * exponents.
     *
     * @param data_items Requires two integer parameters: coefficient, and exponent (in that order) to be
     *                   passed to the underlying Term object
     * @see Term
     */
    @Override
    public void insert(int... data_items) {
        if (this.isEmpty()) {
            try {
                this.insertFirst(data_items[0], data_items[1]);
                return;
            } catch (ImproperInsertionOrderException e) {
                e.printStackTrace();
            }
        }
        Term newTerm = new Term(data_items[0], data_items[1]);
        Term current = this.head;
        while (current.next != null && ((Term) current.next).getExponent() < newTerm.getExponent()) {
            current = (Term) current.next;
        }
        newTerm.next = current.next;
        current.next = newTerm;
    }

    /**
     * Sequentially searches from the beginning of the list to the end, looking for the specified
     * key. If the key given is -1, then it always returns the last node. Otherwise it will search for
     * the key (in this case, the exponent of the polynomial term) and return it if it finds it. If it exhausts
     * the search and cannot find the key, then it throws an exception
     *
     * @param key The key (exponent) to search for
     * @return The polynomial term node with the exponent (key) searched for
     * @throws NodeNotFoundException
     */
    @Override
    public Link findNode(int key) throws NodeNotFoundException {
        Term current = this.head;
        if (key == -1) {
            // find the last one
            while (true) {
                if (current.next == null) {
                    break;
                }
            }
        } else {
            while (current.getExponent() != key) {
                if (current.next == null) {
                    throw new NodeNotFoundException();
                } else {
                    current = (Term) current.next;
                }
            }
        }
        return current;
    }

    /**
     * Deletes a node from the linked list. Searches sequentially through the list until either
     * it comes to the node before the node with the key being searched for or the end of the list.
     * If it reaches the end of hte list and doesn't find it, it throws an exception. Otherwise, it
     * de-references the target node by setting the target node's next property to null and setting the
     * node before the target node's next property to the node after the target node.
     *
     * @param key The key to search for. In this case, the linked list is indexed by the exponent of the
     *            polynomial term
     * @throws NodeNotFoundException
     */
    @Override
    public void deleteNode(int key) throws NodeNotFoundException {
        Term current = this.head;
        while (current != null) {
            if (((Term) current.next).getExponent() == key) {
                Term t = (Term) current.next.next;
                current.next.next = null;
                current.next = t;
                return;
            } else {
                current = (Term) current.next;
            }
        }
        throw new NodeNotFoundException();
    }


    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public List<String> getParts() {
        return parts;
    }

    private boolean isValidPart(String part) {
        return this.matcher.matches(part);
    }


    /**
     * Displays the current state of the linked list in a formatted way.
     * Example: {1,1} -> {2,2} -> {3,3} -> NULL
     */
    @Override
    public void displayList() {
        Term current = this.head;
        System.out.print("HEAD -> ");
        if (current == null) {
            System.out.print("NULL \n");
        }
        while (current != null) {
            current.displayLink();
            if (current.next != null) {
                System.out.print(" -> ");
            } else {
                System.out.print(" -> NULL \n");
            }
            current = (Term) current.next;
        }
    }

    @Override
    public void deleteFirst() throws NodeNotFoundException {
        if (this.isEmpty()) {
            throw new NodeNotFoundException();
        }
        this.head = (Term) head.next;
    }

    @Override
    public void deleteLast() throws NodeNotFoundException {
        if (this.isEmpty()) {
            throw new NodeNotFoundException();
        }
        Term current = this.head;
        while (current.next.next != null) {
            current = (Term) current.next;
        }
        current.next = null;
    }
}
