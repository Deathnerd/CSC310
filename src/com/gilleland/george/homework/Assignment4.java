package com.gilleland.george.homework;

import com.gilleland.george.utils.Choice;
import com.gilleland.george.utils.HomeworkAssignment;
import com.gilleland.george.utils.Menu;

/**
 * Created by Wes Gilleland on 10/21/2015.
 */
public class Assignment4 extends HomeworkAssignment {
    @Override
    public void run() {
        Choice choice = Menu.display(
                new Choice("Read"),
                new Choice("Print"),
                new Choice("Add"),
                new Choice("Subtract")
        );
        //TODO: The rest of the assignment
    }
}
