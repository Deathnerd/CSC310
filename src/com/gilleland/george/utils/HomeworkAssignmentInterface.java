package com.gilleland.george.utils;

/**
 * Created by Wes Gilleland on 9/28/2015.
 */
public interface HomeworkAssignmentInterface {
    /**
     * The main entry method
     */
    void run();

    /**
     * Given a method name as a string, attempt to call said method
     * on the current class
     * @param method_name The method (in lowercase) to call
     * @return True if successfully called, otherwise false if an exception occurred
     */
    boolean _tryMethodCall(String method_name);
}
