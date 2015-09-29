package com.gilleland.george.utils;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Wes Gilleland on 9/28/2015.
 */
public class Menu {

    private static Scanner in = new Scanner(System.in);
    private static String default_prompt = "Enter your choice: ";

    /**
     * Display a displayWithCallbacks for program navigation with a 0 exit option
     * @param choices The choices to display
     */
    public static int display(ArrayList<String> choices){
        int i = 1;
        for(String choice: choices){
            System.out.printf("%d. %s\n", i++, choice);
        }
        System.out.println("0. Exit");
        System.out.println(Menu.default_prompt);
        return Menu.in.nextInt();
    }

    /**
     * Displays a displayWithCallbacks and attempts to run the callback associated with the choice
     * @param choices The choices to display
     * @param callbacks The callbacks for each choice for the user to run
     * @return The choice that the user entered
     */
    public static int displayWithCallbacks(ArrayList<String> choices, ArrayList<HomeworkAssignment> callbacks){
        int choice = Menu.display(choices);
        if(choice > 0) {
            HomeworkAssignment callback = callbacks.get(choice-1);
            callback.run();
        }
        return choice;
    }
}
