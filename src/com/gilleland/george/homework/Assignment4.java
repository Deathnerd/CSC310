package com.gilleland.george.homework;

import com.gilleland.george.utils.*;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wes Gilleland on 10/21/2015.
 */
public class Assignment4 extends HomeworkAssignment {
    QuickPolynomial polynomial = new QuickPolynomial();
    /**
     * The string of input the user gave when entering a polynomial for
     * either the add, subtract, or read operations
     */
    String input_string;
    /**
     * The coefficient of the current term item
     */
    String coefficient = "";
    /**
     * The exponent oof the current term item
     */
    int exponent = 0;
    /**
     * A handy-dandy dynamic array to store each part of the
     */
    List<String> terms = new ArrayList<>();
    /**
     * A regular expression to match a polynomial as a term with the following format:
     * sign(coefficient)x^(exponent)
     * Ex: +4x^3 or -3x^2 or -312x^0
     */
    private final RegularExpression valid_term = new RegularExpression("(\\+|-)(\\d)+(x(\\^\\d)?)?");

    /**
     * The main entry method
     */
    @Override
    public void run() {
        Menu menu = new Menu();
        while (true) {
            Choice choice = menu.display(
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

    /**
     * Validates a given term against the valid_term regular expression
     *
     * @param term The term to validate
     * @return did the term match the pattern for valid_term?
     */
    private boolean isValidTerm(String term) {
        return this.valid_term.matches(term);
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

    /**
     * Reads in a new polynomial expression and performs polynomial addition
     * on the current existing polynomial with it. If there is not a polynomial that
     * currently exists, it will assume the current polynomial is 0 and perform
     * addition on that.
     */
    public void add() {
        this.printPrompt();
        this.getInput();
        this.parseExpression("add");
    }

    /**
     * Reads in a new polynomial expression and performs polynomial subtraction
     * on the current existing polynomial with it. If there is not a polynomial that
     * currently exists, it will assume the current polynomial is 0 and perform
     * subtraction on that.
     */
    public void subtract() {
        this.printPrompt();
        this.getInput();
        this.parseExpression("subtract");
    }

    /**
     * Prints a prompt for the user to enter a polynomial expression
     */
    private void printPrompt() {
        System.out.print("Enter a polynomial: (e.g., +4x^3+3x^2-5x^0): ");
    }

    /**
     * Gets the input of a polynomial string from the user and sets it to the input_string property
     */
    private void getInput() {
        this.input_string = Menu.in.next();
    }

    /**
     * Parses the polynomial expression given by the user. It will alert the user if they have
     * entered an invalid expression. After it has validated the expression, it will then perform
     * the requested function on the polynomial with the user's given polynomial expression.
     *
     * @param operation add, subtract, or read
     */
    private void parseExpression(String operation) {
        try {
            this.validateExpression();
        } catch (InvalidPolynomialExpressionException e) {
            System.out.println("The expression you entered was not valid!");
            return;
        }
        for (String part : this.terms) {
            this.coefficient = this.termCoefficient(part);
            this.exponent = this.termExponent(part);
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

    /**
     * Validates that the string that the user has input is a correctly formatted
     * polynomial string. Does not directly check for insertion order as that is handled
     * via the QuickPolynomial class upon its runtime
     *
     * @throws InvalidPolynomialExpressionException If there is an error with the formatting of
     *                                              the input
     */
    private void validateExpression() throws InvalidPolynomialExpressionException {
        // empty the old terms from the previous operation
        this.terms.clear();
        /*
         * Do a quick check if the first character is an operator. A valid expression
         * for a polynomial must have an operator before each coefficient, even if that
         * coefficient is positive and at the beginning
         */
        if (!Utils.isOperator(this.input_string.charAt(0))) {
            this.input_string = null;
            throw new InvalidPolynomialExpressionException();
        }

        /*
         * Start parsing the expression. This is accomplished by looping through the
         * string and, when an operator (+ or -) is reached, the term is validated
         * against the regular expression to see if it conforms to the pattern of what
         * is deemed valid (see the valid_term property for more information). If it is, then
         * that term is added to the current parts to be further processed and used to
         * manipulate the linked list through either the read(), add(), or subtract() methods.
         *
         * However if the term does not match the pattern for a valid polynomial term, then
         * an InvalidPolynomialExpression error is thrown and everything is reset. This process
         * is repeated until all terms in the input string have been added to the parts list
         */
        int j = 0;
        for (int i = 1; i < this.input_string.length(); i++) {
            if (Utils.isOperator(this.input_string.charAt(0))) {
                String term = this.input_string.substring(j, i);
                j = i;
                this.addTerm(term);
            }
        }
        /*
         * This takes care of the last remaining part as the loop above will leave it out
         */
        this.addTerm(this.input_string.substring(j));
    }

    /**
     * Determines if a term is valid and adds it to the parts list if it is. Throws an exception
     * if otherwise
     *
     * @param term The term to add to the parts list
     * @throws InvalidPolynomialExpressionException If the term doesn't match the pattern of a valid term
     */
    private void addTerm(String term) throws InvalidPolynomialExpressionException {
        if (!this.isValidTerm(term)) {
            this.input_string = null;
            throw new InvalidPolynomialExpressionException();
        }
        this.terms.add(term);
    }

    /**
     * Given a valid term, return its coefficient (sign and all)
     *
     * @param term The polynomial term to parse
     * @return the coefficient from a term
     */
    private String termCoefficient(String term) {
        return term.split("x\\^")[0];
    }

    /**
     * Given a valid term, return its exponent
     *
     * @param term The polynomial term to parse
     * @return the exponent from a term
     */
    private Integer termExponent(String term) {
        return Integer.parseInt(term.split("x\\^")[1]);
    }

    /**
     * Prints out a formatted string representation of the current polynomial.
     * Will alert a user that there is not a polynomial currently defined if
     * it's determined that it is empty
     */
    public void print() {
        //print the polynomial
        if (!this.polynomial.isEmpty()) {
            this.polynomial.print();
        } else {
            System.out.println("No polynomial defined, please enter one\n");
        }
    }
}
