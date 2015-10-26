package com.gilleland.george.homework;

import com.gilleland.george.utils.Choice;
import com.gilleland.george.utils.HomeworkAssignment;
import com.gilleland.george.utils.InvalidPolynomialExpressionException;
import com.gilleland.george.utils.Menu;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Scanner;

/**
 * Created by Wes Gilleland on 10/21/2015.
 */
public class Assignment4 extends HomeworkAssignment {
    public Scanner in = new Scanner(System.in);
    private Polynomial<Integer> polynomial = null;

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
                break;
            }
        }
    }

    /**
     * Reads in a new polynomial expression, replacing the previous one
     * with the new value. Will also alert the user if they attempted to pass
     * an invalid expression
     */
    public void read() {
        System.out.println("Please input a polynomial expression: ");
        try {
            this.polynomial = new Polynomial<>(this.in.nextLine());
        } catch (InvalidPolynomialExpressionException e) {
            System.out.println("\n That was invalid! Please try again!");
        }
    }

    public void add() {
        throw new NotImplementedException();
    }

    public void subtract() {
        throw new NotImplementedException();
    }

    public void print() {
        if(this.polynomial.getExpression() != null){
            System.out.printf("The current polynomial is %s\n", this.polynomial.getExpression());
        } else {
            System.out.println("There's currently no polynomial loaded.");
        }
    }
}
