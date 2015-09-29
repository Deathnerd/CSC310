package com.gilleland.george;

import com.gilleland.george.homework.assignment1;
import com.gilleland.george.utils.HomeworkAssignment;
import com.gilleland.george.utils.Menu;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int choice = Menu.display(new ArrayList<String>() {{
            add("foo");
            add("bar");
        }});

        System.out.println("The user chose: " + choice);

        choice = Menu.displayWithCallbacks(new ArrayList<String>() {{
            add("Assignment 1");
        }},
        new ArrayList<HomeworkAssignment>(){{
            add(new assignment1());
        }});

        System.out.println("The user chose: " + choice);
    }
}
