package com.gilleland.george.utils;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Every Homework Assignment must inherit from this interface. It provides a
 * uniform way of defining a main entry point for logic and allows me to
 * dynamically call them
 * Created by Wes Gilleland on 9/28/2015.
 */
public class HomeworkAssignment implements HomeworkAssignmentInterface {
    private static final Logger log = Logger.getLogger(HomeworkAssignment.class.getName());

    /**
     * {@inheritDoc}
     *
     * @throws NotImplementedException
     */
    @Override
    public void run() throws NotImplementedException {
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     *
     * @param method_name The method (in lowercase) to call
     * @return
     */
    @Override
    public boolean _tryMethodCall(String method_name) {
        boolean success = false;
        String msg = null;
        String error = null;
        try {
            // Where the magic happens! I love reflection :)
            this.getClass().getMethod(method_name).invoke(this);
            success = true;
        } catch (NoSuchMethodException e) {
            System.out.println("I'm afraid I no idea what you're talking about. Check the logs");
            error = e.toString();
            msg = "Attempted to call an undefined method of " + method_name;
        } catch (InvocationTargetException e) {
            System.out.println("There was an error processing your command. Please check the logs");
            error = e.getTargetException().toString();
            msg = "Invocation target exception. Invoked method " + method_name + " threw an error of: " + error;
        } catch (IllegalAccessException e) {
            System.out.println("I'm afraid I can't do that, Dave... Check the logs");
            error = e.toString();
            msg = "Illegal access exception. Failed to access method " + method_name;
        } catch (NotImplementedException e) {
            System.out.println("It would seem this method isn't implemented yet! Check the logs!");
            error = e.toString();
            msg = "Method " + method_name + " not implemented for class " + this.getClass();
        } finally {
            if (msg != null && error != null) {
                log.log(Level.FINE, msg, error);
            }
        }
        return success;
    }
}
