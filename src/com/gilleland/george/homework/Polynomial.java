package com.gilleland.george.homework;

import com.gilleland.george.utils.*;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wes Gilleland on 10/25/2015.
 */
public class Polynomial implements LinkListInterface {
    private Term head = null;
    private String expression;
    private List<String> parts = new ArrayList<>();
    private final RegularExpression matcher = new RegularExpression("(\\+|-)(\\d)+(x(\\^\\d)?)?");

    public Polynomial(String expression) throws InvalidPolynomialExpressionException {
        this.setExpression(expression);
        this.validateExpression();
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

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public void insertFirst(int... data_items) throws ImproperInsertionOrderException {
        Term newTerm = new Term(data_items[0], data_items[1]);
        if (newTerm.getExponent() <= this.head.getExponent()) {
            throw new ImproperInsertionOrderException();
        }
        newTerm.next = this.head;
        this.head = newTerm;
    }

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

    @Override
    public void insert(int... data_items) {
        Term newTerm = new Term(data_items[0], data_items[1]);
        Term current = this.head;
        while (current.next != null && ((Term) current.next).getCoefficient() < newTerm.getCoefficient()) {
            current = (Term) current.next;
        }
        newTerm.next = current.next;
        current.next = newTerm;
    }

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


    @Override
    public void displayList() {
    }
}
