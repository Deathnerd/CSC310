package com.gilleland.george;

import com.gilleland.george.homework.Assignment3;
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
        ArrayList<String> main_menu = new ArrayList<String>(){{
            add("Assignment 3: Sorting and Searching");
        }};
        // get the choice the user chose
        int choice = Menu.display(main_menu);
        switch (choice){
            case 1:
                System.out.println("Good choice! Running Assignment 3!\n\n");
                HomeworkAssignment assignment3 = new Assignment3();
                assignment3.run();
            default:
                //
        }
        System.out.println("Thanks for running my homework assignments!");
    }
}
