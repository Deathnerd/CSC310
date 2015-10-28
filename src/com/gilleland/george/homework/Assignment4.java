package com.gilleland.george.homework;

import com.gilleland.george.utils.Choice;
import com.gilleland.george.utils.HomeworkAssignment;
import com.gilleland.george.utils.InvalidPolynomialExpressionException;
import com.gilleland.george.utils.Menu;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wes Gilleland on 10/21/2015.
 */
public class Assignment4 extends HomeworkAssignment {
    QuickPolynomial polynomial = new QuickPolynomial();
    String coefficient = "";
    String input_string;
    boolean exponentExists = false;
    int exponent = 0;
    List<String> parts = new ArrayList<>();
    private final RegularExpression matcher = new RegularExpression("(\\+|-)(\\d)+(x(\\^\\d)?)?");

    @Override
    public void run() {
        while (true) {
            Choice choice = Menu.display(
                    new Choice("Read"),
                    new Choice("Print"),
                    new Choice("Add"),
                    new Choice("Subtract")
            );
            if (choice.getIndex() > 0) {
                this._tryMethodCall(choice.getName().toLowerCase());
            } else {
                return;
            }
        }
    }

    private boolean isValidPart(String part) {
        return this.matcher.matches(part);
    }

    /**
     * Reads in a new polynomial expression, replacing the previous one
     * with the new value. Will also alert the user if they attempted to pass
     * an invalid expression
     */
    public void read() {
        //build the new polynomial
        this.polynomial.empty();
        this.printPrompt();
        this.getInput();
        this.parseExpression("read");
    }

    public void add() {
        this.printPrompt();
        this.getInput();
        this.parseExpression("add");
    }

    public void subtract() {
        this.printPrompt();
        this.getInput();
        this.parseExpression("subtract");
    }

    private void printPrompt() {
        System.out.print("Enter a polynomial: (e.g., +4x^3+3x^2-5x^0): ");
    }

    private void getInput() {
        this.input_string = Menu.in.next();
    }

    private void parseExpression(String operation) {
        try {
            this.validateExpression();
        } catch (InvalidPolynomialExpressionException e) {
            System.out.println("The expression you entered was not valid!");
            return;
        }
        for (String part : this.parts) {
            this.coefficient = this.partCoefficient(part);
            this.exponent = this.partExponent(part);
            switch (operation) {
                case "subtract":
                    this.polynomial.subtraction(this.exponent, this.coefficient);
                    break;
                case "add":
                    this.polynomial.addition(this.exponent, this.coefficient);
                    break;
                case "read":
                    this.polynomial.insert(this.exponent, this.coefficient);
                    break;
            }
        }
    }

    private void validateExpression() throws InvalidPolynomialExpressionException {
        this.parts.clear();
        if (this.input_string.charAt(0) != '-' && this.input_string.charAt(0) != '+') {
            this.input_string = null;
            throw new InvalidPolynomialExpressionException();
        }

        int j = 0;
        for (int i = 1; i < this.input_string.length(); i++) {
            if (this.input_string.charAt(i) == '-' || this.input_string.charAt(i) == '+') {
                String t = this.input_string.substring(j, i);
                j = i;
                if (!this.isValidPart(t)) {
                    this.input_string = null;
                    throw new InvalidPolynomialExpressionException();
                }
                this.parts.add(t);
            }
        }
        String t = this.input_string.substring(j);
        if (!this.isValidPart(t)) {
            this.input_string = null;
            throw new InvalidPolynomialExpressionException();
        }
        this.parts.add(t);
    }

    private String partCoefficient(String part) {
        return part.split("x\\^")[0];
    }

    private Integer partExponent(String part) {
        return Integer.parseInt(part.split("x\\^")[1]);
    }

    public void print() {
        //print the polynomial
        if (polynomial.length > 0) {
            polynomial.print();
        } else {
            System.out.println("No polynomial defined, please enter one\n");
        }
    }
}
