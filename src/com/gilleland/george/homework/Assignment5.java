package com.gilleland.george.homework;

import com.gilleland.george.exceptions.ZeroDivisionError;
import com.gilleland.george.objects.Choice;
import com.gilleland.george.objects.HomeworkAssignment;
import com.gilleland.george.objects.StackLinkedList;
import com.gilleland.george.utils.*;

import java.util.EmptyStackException;

/**
 * Created by Wes Gilleland on 10/21/2015.
 */
public class Assignment5 extends HomeworkAssignment {
    /**
     * The input string from the user
     */
    public String input_string;

    /**
     * The infix equation before it's converted to postfix
     */
    public String infix_string;

    /**
     * The converted postifix equation as a string
     */
    public String postfix_string;

    /**
     * The result of the postfix expression when it's evaluated
     */
    public Number postfix_result = 0;

    /**
     * The main entry method
     */
    @Override
    public void run() {
        Menu menu = new Menu();
        Choice choice;
        while (true) {
            choice = menu.display(
                    new Choice("Read", "Read an expression in infix notation"),
                    new Choice("Convert", "Convert infix to postfix"),
                    new Choice("Evaluate", "Evaluate the postfix expression")
            );
            if (choice.getIndex() > 0) {
                this._tryMethodCall(choice.getName().toLowerCase());
            } else {
                return;
            }
        }
    }

    /**
     * Reads in a new polynomial expression, replacing the previous one
     * with the new value. Will also alert the user if they attempted to pass
     * an invalid expression
     */
    public void read() {
        this.printPrompt();
        this.getInput();
        this.infix_string = this.input_string;
        this.printExpression("infix");
    }

    /**
     * Using a linked list stack implementation, convert the current infix expression
     * to a postfix expression
     */
    public void convert() {
        this.postfix_string = "";

        StackLinkedList operations_stack = new StackLinkedList();

        for (char current_char : this.infix_string.toCharArray()) {
            if (Utils.isNumber(current_char)) {
                this.postfix_string += current_char;
            } else {
                this.postfix_string += " ";
                if (operations_stack.isEmpty()) {
                    operations_stack.push(current_char);
                }
                /*
                 * If the current character is a closing parenthesis, then we need to process
                 * a subexpression
                 */
                else if (Utils.isOneOf(current_char, ')')) {
                    this.handleSubExpression(operations_stack);
                }
                /*
                 * If the the current character is an operator and there's an operator on the stack,
                 * then we need to pop from the stack until the top element of the stack is not an operator.
                 */
                else if (Utils.isPlusOrMinus(current_char) && operations_stack.topIsCharacter() &&
                        Utils.isOneOf(((Character) operations_stack.peek()).charValue(), '+', '-', '*', '/')) {
                    this.handlePlusAndMinus(operations_stack);
                    /*
                     * Operator is handled, push the current character to the stack
                     */
                    operations_stack.push(current_char);
                }
                /*
                 * If the current character is multiplication or division and there is a multiplication
                 * or division on the stack, then they have special order preference than + or - and must
                 * be treated differently. Keep popping from the stack until the top element is another
                 * multiplication or division operator.
                 */
                else if (Utils.isMultOrDiv(current_char) &&
                        operations_stack.topIsCharacter() &&
                        Utils.isMultOrDiv(((Character) operations_stack.peek()).charValue())) {
                    this.handleMultAndDiv(operations_stack);
                    /*
                     * Operator is handled, push the current character to the stack
                     */
                    operations_stack.push(current_char);
                }
                /*
                 * The current character isn't anything special and can be pushed to the current stack
                 */
                else {
                    operations_stack.push(current_char);
                }
            }
        }

        /*
         * Clean up the remaining operations in the stack
         */
        this.cleanupRemaining(operations_stack);
        /*
         * Let the user see what the resulting postfix expression is
         */
        this.printExpression("postfix");
    }

    /**
     * Handles the subexpressions (expressions inside parenthesis) while converting
     * from infix to postfix
     *
     * @param operations_stack The current working stack
     */
    private void handleSubExpression(StackLinkedList operations_stack) {
        /*
         * While the operations_stack isn't empty and the top is not the matching opening
         * parenthesis, pop each of the operators currently onto the operations_stack that belong
         * to the subexpression on to the postfix string. When the current character is
         * the opening parenthesis, pop it and break the loop. If the last pop would
         * result in an empty operations_stack, then break the loop.
         */
        while (!operations_stack.isEmpty() && operations_stack.topIsCharacter()) {
            char current_top = (Character) operations_stack.peek();
            if (Utils.isOneOf(current_top, '(')) {
                operations_stack.pop();
                break;
            }

            char c;
            try {
                c = (Character) operations_stack.pop();
            } catch (EmptyStackException e) {
                break;
            }
            this.postfix_string += " " + c + " ";
        }
    }

    /**
     * Cleans up the remaining operators on the stack and puts them in the postfix string.
     * Also runs a small regular expression to get rid of extra whitespace in the
     * resulting string.
     *
     * @param operations_stack The current stack
     */
    private void cleanupRemaining(StackLinkedList operations_stack) {
        /*
         * Empty out the stack onto the postfix string
         */
        if (!operations_stack.isEmpty()) {
            while (!operations_stack.isEmpty()) {
                this.postfix_string += " " + operations_stack.pop() + " ";
            }
        }
        // Replace the spaces of two or more with singles
        this.postfix_string = this.postfix_string.replaceAll("( ){2,}", " ");
    }

    /**
     * Handles the part of converting from infix to postfix dealing with multiplication
     * and division. Since they have special order, they have to be treated just a little
     * differently from Addition and Subtraction
     *
     * @param operations_stack The current stack
     */
    private void handleMultAndDiv(StackLinkedList operations_stack) {
        /*
         * While the stack isn't empty and the top is a character (for Java's sake),
         * check if the current top element in the stack is multiplication or division
         * If it isn't, then break out of the function. Otherwise, pop the character
         * off of the stack and append it to the postfix string
         */
        while (!operations_stack.isEmpty() && operations_stack.topIsCharacter()) {
            char current_top = (Character) operations_stack.peek();
            if (!Utils.isMultOrDiv(current_top)) {
                break;
            }

            char c;
            try {
                c = (Character) operations_stack.pop();
            } catch (EmptyStackException e) {
                return;
            }
            this.postfix_string += " " + c + " ";
        }
    }

    /**
     * Handles the part of converting from infix to postfix dealing with addition
     * and subtraction.
     *
     * @param operations_stack The current stack
     */
    private void handlePlusAndMinus(StackLinkedList operations_stack) {
        /*
         * While the stack isn't empty and the top is a character (for Java's sake),
         * check if the current top element in the stack is some kind of operator.
         * If it isn't, then break out of the function. Otherwise, pop the character
         * off of the stack and append it to the postfix string
         */
        while (!operations_stack.isEmpty() && operations_stack.topIsCharacter()) {
            char current_top = (Character) operations_stack.peek();
            if (!Utils.isOneOf(current_top, '+', '-', '*', '/')) {
                return;
            }

            char c;
            try {
                c = (Character) operations_stack.pop();
            } catch (EmptyStackException e) {
                return;
            }
            this.postfix_string += " " + c + " ";
        }
    }

    /**
     * Given that there's a current postfix expression, evaluate it and report the answer
     */
    public void evaluate() throws ZeroDivisionError {
        /*
         * Create a new empty stack to use for processing
         */
        StackLinkedList stack = new StackLinkedList();

        //Initialize the operand
        int operand = 0;
        boolean has_operand = false;

        /*
         * Initialize temporary terms for calculation of difference
         * operations (division and subtraction) as those cannot be
         * done in an arbitrary fashion.
         */
        float left_operand;
        float right_operand;
        /*
         * For each of the characters in the postfix string, first check to
         * see if it's a space. If it is and we have a term, then push that term
         * onto the stack and reset the term and has_operand boolean.
         *
         * Otherwise, check if the current character is a number. If it is, then
         * build up the term digit by digit.
         *
         * If the current character is a plus, then pop the first two elements
         * out of the stack, add them together, and push the operand onto the stack.
         *
         * If the current character is a multiplication, then pop the first two elements
         * out of the stack, multiply them together, and push the operand onto the stack.
         *
         * If the current character is a -, then pop out the right term into a temporary
         * variable as well as the left term into another temporary variable. Then push
         * the operand of subtracting the right from the left onto the stack.
         *
         * If the current character is a division, then pop out the right term into a
         * temporary variable as well as the left term into another temporary variable.
         * Then check if the right term is 0. If it is, throw a new ZeroDivisionError.
         * If not, then push the operand of dividing the left term by the right term onto
         * the stack.
         */
        for (char current_character : this.postfix_string.toCharArray()) {
            if (Utils.isOneOf(current_character, ' ') && has_operand) {
                stack.push(operand);
                operand = 0;
                has_operand = false;
            } else {
                if (Utils.isNumber(current_character)) {
                    operand = operand * 10 + (current_character - '0');
                    has_operand = true;
                } else if (current_character == '+') {
                    try {
                        stack.push(((Number) stack.pop()).intValue() + ((Number) stack.pop()).intValue());
                    } catch (EmptyStackException e) {
                        //
                    }
                } else if (current_character == '*') {
                    try {
                        stack.push(((Number) stack.pop()).intValue() * ((Number) stack.pop()).intValue());
                    } catch (EmptyStackException e) {
                        //
                    }
                } else if (current_character == '-') {
                    try {
                        right_operand = ((Number) stack.pop()).intValue();
                        left_operand = ((Number) stack.pop()).intValue();
                        stack.push(left_operand - right_operand);
                    } catch (EmptyStackException e) {
                        //
                    }
                } else if (current_character == '/') {
                    try {
                        right_operand = ((Number) stack.pop()).intValue();
                        left_operand = ((Number) stack.pop()).intValue();
                        if (right_operand == 0) {
                            throw new ZeroDivisionError();
                        }
                        stack.push(left_operand / right_operand);
                    } catch (EmptyStackException e) {
                        //
                    }
                }
            }
        }
        /*
         * The operand is the only remaining value on the stack. Pop it
         * onto the postfix_result variable
         */
        this.postfix_result = (Number) stack.pop();
        this.printPostfixResult();
    }

    /**
     * Prints the result of the postfix evaluation
     */
    private void printPostfixResult() {
        System.out.printf("%s = %d\n\n", this.postfix_string, this.postfix_result.intValue());
    }

    /**
     * Prints a prompt for the user to enter a polynomial expression
     */
    private void printPrompt() {
        System.out.print("Please enter an infix equation [ex: 1+5*(3/5)-4]: ");
    }

    /**
     * Gets the input of a polynomial string from the user and sets it to the input_string property
     */
    private void getInput() {
        this.input_string = Menu.in.next();
    }

    /**
     * Prints the specified expression for user validation
     */
    private void printExpression(String type) {
        switch (type) {
            case "infix":
                System.out.printf("The current infix expression is: %s\n", this.infix_string);
                break;
            case "postfix":
                if (this.postfix_string != null) {
                    System.out.printf("The current postfix expression is: %s\n", this.postfix_string);
                } else {
                    System.out.println("The postfix expression has not been determined. Please input a valid infix expression and run the Convert option");
                }
                break;
        }
    }
}
