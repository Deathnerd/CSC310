package com.gilleland.george.homework;

import com.gilleland.george.utils.HomeworkAssignment;
import com.gilleland.george.utils.Menu;
import com.gilleland.george.utils.NotSortedException;
import com.gilleland.george.utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Wes Gilleland on 9/28/2015.
 */
public class Assignment3 extends HomeworkAssignment {
    private static final Logger log = Logger.getLogger(Assignment3.class.getName());
    public SortAndSearch sortAndSearch = new SortAndSearch();
    public Random rand = new Random();
    public ArrayList<String> dataset = new ArrayList<>();

    private Scanner in = new Scanner(System.in);

    @Override
    public void run() {
        System.out.println(Assignment3.class.getClassLoader().getResource("logging.properties"));
        while (true) {
            ArrayList<String> menu_choices = new ArrayList<String>() {{
                add("Read");
                add("Generate");
                add("Print");
                add("Sort");
                add("Search");
            }};
            // Get the numeric choice, -1 if exit
            int choice = menu_choices.indexOf(Menu.displayS(menu_choices));
            if (choice >= 0) {
                try {
                    // because I'm lazy and don't feel like cluttering my code. We'll
                    // dynamically call the method
                    Object ret = this.getClass().getMethod(menu_choices.get(choice).toLowerCase()).invoke(this);
                } catch (NoSuchMethodException e) {
                    System.out.println("I'm afraid I can't do that, Dave... Check the logs");
                    String msg = "Attempted to call an undefined method of " + choice;
                    String error = e.toString();
                    log.log(Level.FINE, msg, error);
                } catch (InvocationTargetException e) {
                    System.out.println("There was an error processing your command. Please check the logs");
                    String error = e.getTargetException().toString();
                    String msg = "Invocation target exception. Invoked metohd " + choice + " threw an error of: " + error;
                    log.log(Level.FINE, msg, error);
                } catch (IllegalAccessException e) {
                    System.out.println("I'm afraid I can't do that, Dave... Check the logs");
                    String msg = "Illegal access exception. Failed to access method " + choice;
                    String error = e.toString();
                    log.log(Level.FINE, msg, error);
                }
            } else {
                break;
            }
        }
    }

    /**
     * Reads in a list of strings and store them in {@link #dataset}
     */
    public void read() {
        System.out.println("Input a string to add to the dataset (note, this will append to the end of the data and it will be marked as unsorted!)");
        System.out.println("When you are done, simply press enter without inputting anything else");
        while (!this.in.hasNext("")) {
            if (this.in.hasNextDouble() || this.in.hasNextLong() || this.in.hasNextFloat() || this.in.hasNextInt()) {
                System.out.println("No numbers please!");
                continue;
            }
            String input = this.in.nextLine();
            if (!input.equals("")) {
                this.dataset.add(input);
            } else {
                break;
            }
        }
    }

    /**
     * Prints the current dataset and whether or not it's sorted
     */
    public void print() {
        System.out.println("The current data set:");
        System.out.println(this.dataset.toString());
        System.out.printf("It %s sorted\n", (this.sortAndSearch.is_sorted) ? "is" : "isn't");
    }

    /**
     * Present the user with the option to sort a data set using three sorting methods
     * TODO: Fix Merge sort
     */
    public void sort() {
        ArrayList<String> choices = new ArrayList<String>() {{
            add("Merge");
            add("Quick");
            add("Shell");
        }};

        String choice = Menu.displayS(choices);

        if (choice.equalsIgnoreCase("merge")) {
            // do stuff for merge sortAndSearch
            this.sortAndSearch.mergeSort(this.dataset);
        } else if (choice.equalsIgnoreCase("quick")) {
            // do stuff for quick sortAndSearch
            this.sortAndSearch.quickSort(this.dataset);
        } else if (choice.equalsIgnoreCase("shell")) {
            this.sortAndSearch.shellSort(this.dataset);
        }
        // Sync the data sets
        // TODO: Get rid of this data set maybe?
        this.dataset = this.sortAndSearch.dataset;
    }

    /**
     * Opens the scrabble_words.txt file and randomly generates between 7 and 15 words
     * and adds them to the dataset. For the lazy user and programmer!
     *
     * @throws FileNotFoundException
     */
    public void generate() throws FileNotFoundException {
        this.dataset.clear();
        this.sortAndSearch.is_sorted = false;
        boolean unique;
        int bound = rand.nextInt(15);
        if (bound <= 6) {
            bound = 10;
        }
        for (int i = 0; i < bound; i++) {
            String choose = Utils.choose(new File(getClass().getResource("scrabble_words.txt").getPath()));
            unique = true;
            for (String element : this.dataset) {
                // check for unique first character values
                if (element.charAt(0) == choose.charAt(0)) {
                    // TODO: Make this more efficient
                    i--; // try again!
                    unique = false;
                }
            }
            if (unique) {
                this.dataset.add(choose);
            }
        }
    }

    /**
     * Present the user with the options to search for a key in the data set
     */
    public void search() {
        ArrayList<String> choices = new ArrayList<String>() {{
            add("Binary");
            add("Interpolation");
        }};

        String choice = Menu.displayS(choices);

        int position = -1;
        try {
            if (choice.equalsIgnoreCase("binary")) {
                System.out.println("Enter a key to search for: ");
                position = this.sortAndSearch.binarySearch(this.in.nextLine().charAt(0));
            } else if (choice.equalsIgnoreCase("interpolation")) {
                System.out.println("Enter a key to search for: ");
                position = this.sortAndSearch.interpolationSearch(this.in.nextLine().charAt(0));
            } else {
                System.out.println("Invalid choice! Try again!");
                return;
            }

            if (position >= 0) {
                System.out.printf("Key was found at index %d with a value of %s. Yay!\n", position, this.sortAndSearch.dataset.get(position));
            } else {
                System.out.println("The key requested was not found in the data set!");
            }
        } catch (NotSortedException e) {
            System.out.println("You have to sort the data set first! I recommend the quickSort function!");
            System.out.println("Please sort the input and try again!");
        }
    }
}

