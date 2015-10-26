package com.gilleland.george;

import com.gilleland.george.homework.Assignment3;
import com.gilleland.george.homework.Assignment4;
import com.gilleland.george.utils.Choice;
import com.gilleland.george.utils.HomeworkAssignment;
import com.gilleland.george.utils.Menu;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Please choose an Assignment so that you may deliver\n" +
                "judgement upon my poor soul\n");
        /**
         * The choices to display for the main menu
         */
        Choice choice = Menu.display(
                new Choice("Assignment 3: Sorting and Searching"),
                new Choice("Assignment 4: Polynomial (Linked List)")
        );

        switch (choice.getIndex()) {
            case 1:
                System.out.println("Good choice! Running Assignment 3!\n\n");
                HomeworkAssignment assignment3 = new Assignment3();
                assignment3.run();
                break;
            case 2:
                System.out.println("Polynomial Linked List! A personal favorite! Running it now!\n\n");
                HomeworkAssignment assignment4 = new Assignment4();
                assignment4.run();
                break;
            default:
                //
        }
        System.out.println("Thanks for running my homework assignments!");
    }
}
