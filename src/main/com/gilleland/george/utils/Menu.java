package com.gilleland.george.utils;

import com.gilleland.george.objects.Choice;

import java.util.Scanner;

/**
 * Created by Wes Gilleland on 9/28/2015.
 */
public class Menu {

    public static Scanner in = new Scanner(System.in);
    private String default_prompt = "Enter your choice: ";
    private String choice_format = "%d. %s\n";
    // TODO: consolidate the exit components into an actual Choice object
    private int exit_number = 0;
    private String exit_text = "Exit";
    private String incorrect_choice_prompt = "Please choose a correct selection from the following menu";

    public String getIncorrectChoicePrompt() {
        return this.incorrect_choice_prompt;
    }

    public void setIncorrectChoicePrompt(String prompt) {
        this.incorrect_choice_prompt = prompt;
    }

    public String getDefaultPrompt() {
        return this.default_prompt;
    }

    public void setDefaultPrompt(String default_prompt) {
        this.default_prompt = default_prompt;
    }

    public String getChoiceFormat() {
        return this.choice_format;
    }

    public void setChoiceFormat(String choice_format) {
        this.choice_format = choice_format;
    }

    public int getExitNumber() {
        return this.exit_number;
    }

    public void setExitNumber(int exit_number) {
        this.exit_number = exit_number;
    }

    public String getExitText() {
        return this.exit_text;
    }

    public void setExitText(String exit_text) {
        this.exit_text = exit_text;
    }

    private void showIncorrectChoiceMessage() {
        System.out.println(this.incorrect_choice_prompt);
    }

    private void showExitChoice() {
        System.out.printf(this.choice_format, this.exit_number, this.exit_text);
    }

    /**
     * Display a menu for program navigation with a 0 exit option
     *
     * @param choices Any number of Choice objects representing, in order, the menu to be displayed
     * @return A Choice object representing the user's choice
     */
    public Choice display(Choice... choices) {
        int i = 1;
        for (Choice choice : choices) {
            if (choice.getPrompt() != null) {
                System.out.printf(this.choice_format, i++, choice.getPrompt());
            } else {
                System.out.printf(this.choice_format, i++, choice.getName());
            }
        }
        this.showExitChoice();
        int t = Menu.in.nextInt();
        if (t == this.exit_number) {
            Choice c = new Choice(this.exit_text);
            c.setIndex(this.exit_number - 1);
            return c;
        } else if (t < 0 || t > choices.length) {
            this.showIncorrectChoiceMessage();
            return this.display(choices);
        }
        choices[t - 1].setIndex(t);
        return choices[t - 1];
    }

    public Choice display(MenuCallback callback, Choice... choices) {
        int i = 1;
        for (Choice choice : choices) {
            if (choice.getPrompt() != null) {
                System.out.printf(this.choice_format, i++, choice.getPrompt());
            } else {
                System.out.printf(this.choice_format, i++, choice.getName());
            }
        }
        this.showExitChoice();
        int t = Menu.in.nextInt();
        if (t == this.exit_number) {
            Choice c = new Choice(this.exit_text);
            c.setIndex(this.exit_number - 1);
            return c;
        } else if (t < 0 || t > choices.length) {
            this.showIncorrectChoiceMessage();
            return this.display(choices);
        }
        return (Choice) callback.execute(choices[t - 1]);
    }

    public boolean displayYesNo(String prompt) {
        System.out.println(prompt);
        System.out.printf(this.choice_format, 1, "Yes");
        System.out.printf(this.choice_format, 2, "No");
        int t = Menu.in.nextInt();
        if (t < 1 || t > 2) {
            this.showIncorrectChoiceMessage();
            this.displayYesNo(prompt);
        }
        return t == 1;
    }
}
