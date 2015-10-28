package com.gilleland.george.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * Utilities class
 * @author Wes Gilleland
 */
public class Utils {

    /**
     * Randomly chooses a line from a given file
     *
     * @param file The pointer to the file to get the random lines from
     * @return The random string from file
     * @throws FileNotFoundException
     */
    public static String choose(File file) throws FileNotFoundException {
        String result = null;
        Random rand = new Random();
        int n = 0;
        for (Scanner sc = new Scanner(file); sc.hasNext(); ) {
            ++n;
            String line = sc.nextLine();
            if (rand.nextInt(n) == 0) {
                result = line;
            }
        }
        return result;
    }

    /**
     * Checks if the character passed is a unary (+ or -) operator.
     *
     * @param character The character to pass
     * @return The character is a + or -
     */
    public static boolean isOperator(char character) {
        return character == '-' || character == '+';
    }
}
