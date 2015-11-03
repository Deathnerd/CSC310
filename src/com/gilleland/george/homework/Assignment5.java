package com.gilleland.george.homework;

import com.gilleland.george.utils.Choice;
import com.gilleland.george.utils.HomeworkAssignment;
import com.gilleland.george.utils.Menu;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

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
    public double postfix_result = 0;

    /**
     * The result of the infix expression when it's evaluated. Calculated
     * as a debugging step to ensure postfix has been evaluated and converted
     * properly
     */
    public Object infix_result = 0;

    /**
     * Unlocks a little more verbosity and a couple of new menu choices
     */
    public boolean debug;

    /**
     * The main entry method
     */
    @Override
    public void run() {
        Menu menu = new Menu();
        Choice choice;
        this.debug = menu.displayYesNo("Would you like to enable debugging mode?") == 1;
        while (true) {
            if (this.debug) {
                choice = menu.display(
                        new Choice("Read", "Read an expression in infix notation"),
                        new Choice("Convert", "Convert infix to postfix"),
                        new Choice("Evaluate", "Evaluate the postfix expression"),
                        new Choice("PrintInfix", "Print the current infix expression"),
                        new Choice("PrintPostfix", "Print the current postfix expression")
                );
            } else {
                choice = menu.display(
                        new Choice("Read", "Read an expression in infix notation"),
                        new Choice("Convert", "Convert infix to postfix"),
                        new Choice("Evaluate", "Evaluate the postfix expression")
                );
            }
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
        if (this.debug) {
            System.out.println("Evaluating infix string...");
            this.evaluateInfix();
        }
        this.printinfix();
    }

    /**
     * Using a linked list stack implementation, convert the current infix expression
     * to a postfix expression
     */
    public void convert() {
        // do the conversion with the stacks and stuff
        // get the postfix string
        throw new NotImplementedException();
//        System.out.println("Conversion successful!");
//        this.printpostfix();
    }

    /**
     * Given that there's a current postfix expression, evaluate it and report the answer
     */
    public void evaluate() {
        // do the evaluation
        throw new NotImplementedException();
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

    /**
     * Convenience debugging menu choice for checking the current infix expression
     */
    private void printinfix() {
        this.printExpression("infix");
    }

    /**
     * Convenience debugging menu choice for checking the current postfix expression
     */
    private void printpostfix() {
        this.printExpression("postfix");
    }

    /**
     * A convenience debugging method for evaluating the current infix expression
     */
    private void evaluateInfix() {
        ScriptEngineManager mgr = new ScriptEngineManager();
        try {
            this.infix_result = mgr.getEngineByName("JavaScript").eval(this.infix_string);
            System.out.println("The infix expression evaluated to " + this.infix_result);
        } catch (ScriptException e) {
            System.out.println("The infix expression couldn't be evaluated!");
        }
    }
}
