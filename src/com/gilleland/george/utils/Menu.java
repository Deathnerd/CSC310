package com.gilleland.george.utils;

import java.util.*;

/**
 * Created by Wes Gilleland on 9/28/2015.
 * TODO: Add incorrect menu choice exception and handling in the display
 */
public class Menu {

    private static Scanner in = new Scanner(System.in);
    private static String default_prompt = "Enter your choice: ";

    /**
     * Display a Menu for program navigation with a 0 exit option
     *
     * @param choices The choices to display
     */
    public static int display(ArrayList<String> choices) {
        int i = 1;
        for (String choice : choices) {
            System.out.printf("%d. %s\n", i++, choice);
        }
        System.out.println("0. Exit");
        System.out.println(Menu.default_prompt);
        return Menu.in.nextInt();
    }

    /**
     * Returns the canonical string value of the menu item chosen
     * @param choices The list of choices to display
     * @return A null string if the exit was chosen, otherwise the canonical string value of the menu item
     */
    public static String displayS(ArrayList<String> choices) {
        int i = 1;
        for (String choice : choices) {
            System.out.printf("%d. %s\n", i++, choice);
        }
        System.out.println("0. Exit");
        System.out.println(Menu.default_prompt);
        int choice = Menu.in.nextInt() - 1;
        if(choice != -1){
            return choices.get(choice);
        }
        return "";
    }

    /**
     * Displays a menu to perform associated callbacks.
     * @param callbacks LinkedHashMap of keys and their associated callbacks to perform
     * @return Returns the displayed number beside the choice in the menu
     * @throws CallbackFailedException If the callback failed for any reason
     */
    public static int display(LinkedHashMap<String, HomeworkAssignment> callbacks) throws CallbackFailedException{
        ArrayList<String> arrayListOfKeys = Utils.getArrayListOfKeys(callbacks);
        String choice = displayS(arrayListOfKeys);
        try{
            if(!choice.equals("")){
                (callbacks.get(choice)).run();
            }
        } catch(Exception e){
            throw new CallbackFailedException();
        }
        // Returns the displayed number beside the choice in the menu
        return arrayListOfKeys.indexOf(choice) + 1;
    }

    /**
     * Displays a displayWithCallbacks and attempts to run the callback associated with the choice
     *
     * @deprecated Use {@link #display(LinkedHashMap)} instead
     * @param choices   The choices to display
     * @param callbacks The callbacks for each choice for the user to run
     * @return The choice that the user entered
     */
    @Deprecated
    public static int displayWithCallbacks(ArrayList<String> choices, ArrayList<HomeworkAssignment> callbacks) {
        int choice = Menu.display(choices);
        // If the choice was not to exit, execute the callback
        if (choice > 0) {
            HomeworkAssignment callback = callbacks.get(choice - 1);
            callback.run();
        }
        return choice;
    }
}
