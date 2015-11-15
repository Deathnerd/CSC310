package com.gilleland.george;

import com.gilleland.george.homework.*;
import com.gilleland.george.objects.Choice;
import com.gilleland.george.objects.HomeworkAssignment;
import com.gilleland.george.utils.Menu;

import java.io.File;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        System.out.println("Please choose an Assignment so that you may deliver judgement upon my poor soul\n");
        /**
         * The choices to display for the main menu
         */
        Menu menu = new Menu();
        Choice choice = menu.display(
                new Choice("Assignment 3", "Assignment 3: Sorting and Searching"),
                new Choice("Assignment 4", "Assignment 4: Polynomial (Linked List)"),
                new Choice("Assignment 5", "Assignment 5: Infix to Postfix Converter (Stacks)"),
                new Choice("Assignment 6", "Assignment 6: Circular Queues (Arrays)"),
                new Choice("Assignment 7", "Assignment 7: Binary Search Trees using Breadth-First Traversal")
        );

        switch (choice.getName()) {
            case "Assignment 3":
                System.out.println("Good choice! Running Assignment 3!\n\n");
                HomeworkAssignment assignment3 = new Assignment3();
                assignment3.run();
                break;
            case "Assignment 4":
                System.out.println("Polynomial Linked List! A personal favorite! Running it now!\n\n");
                HomeworkAssignment assignment4 = new Assignment4();
                assignment4.run();
                break;
            case "Assignment 5":
                System.out.println("Infix to Postfix expression conversion and evaluation using Linked List Stacks coming right up!\n\n");
                HomeworkAssignment assignment5 = new Assignment5();
                assignment5.run();
                break;
            case "Assignment 6":
                System.out.println("Circular Queues using arrays! Whoo hoo!");
                HomeworkAssignment assignment6 = new Assignment6();
                assignment6.run();
                break;
            case "Assignment 7":
                System.out.println("Alright! Let's search for some games!");
                HomeworkAssignment assignment7 = new Assignment7();
                assignment7.run();
                break;
            default:
                //
        }
        System.out.println("Thanks for running my homework assignments!");
    }
}
