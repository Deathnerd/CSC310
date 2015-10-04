package com.gilleland.george.utils;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.logging.Logger;

/**
 * Every Homework Assignment must inherit from this interface. It provides a
 * uniform way of defining a main entry point for logic and allows me to
 * dynamically call them
 * Created by Wes Gilleland on 9/28/2015.
 */
public class HomeworkAssignment implements HomeworkAssignmentInterface {
    private static final Logger log = Logger.getLogger(HomeworkAssignment.class.getName());
    @Override
    public void run() throws NotImplementedException {
        throw new NotImplementedException();
    }
}
