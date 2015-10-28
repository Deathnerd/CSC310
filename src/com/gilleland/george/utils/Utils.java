package com.gilleland.george.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Wes Gilleland on 9/29/2015.
 */
public class Utils {

    /**
     * Does what it says on the tin: Given a LinkedHashMap, return its keys inside of an
     * ArrayList
     *
     * @param hashmap The hashmap
     * @return The ArrayList containing the LinkedHashMap's keys
     */
    public static ArrayList<String> getArrayListOfKeys(LinkedHashMap hashmap) {
        ArrayList<String> ret = new ArrayList<>();
        for (Object key : hashmap.keySet()) {
            ret.add((String) key);
        }
        return ret;
    }

    /**
     * Checks if the characters in two strings at one given position are equal to another.
     * Returns false if the position is out of bounds for any of the strings.
     * Note: this assumes that the position is relative to the zero index of the strings
     *
     * @param str1     The first string
     * @param str2     The second string
     * @param position The zero-indexed position of the characters to compare
     * @return False if not equal or position out of bounds, otherwise true
     */
    public static boolean charEquals(String str1, String str2, int position) {
        boolean is_in_bounds = isInBounds(str1, str2, position);
        return is_in_bounds // do out of bounds check first
                && str1.charAt(position) == str2.charAt(position); // then check for the actual equality
    }

    /**
     * Determines if the ASCII value of two characters at a given position in two strings
     * are greater than each other in the form of str1[position] > str2[position]
     * Returns false if the position is out of bounds for any of the strings.
     * Note: this assumes that the position is relative to the zero index of the strings
     *
     * @param str1     The left-hand side of the comparison
     * @param str2     The right-hand side of the comparison
     * @param position The zero-indexed position of the characters to compare
     * @return False if the character at position in string 1 is not greater than the character
     * at position in string 2 or position out of bounds for either string, otherwise true
     */
    public static boolean charGreaterThan(String str1, String str2, int position) {
        return isInBounds(str1, str2, position) // do out of bounds check first
                && str1.charAt(position) > str2.charAt(position); // run the comparison
    }

    /**
     * Determines if the ASCII value of two characters at a given position in two strings
     * are less than each other in the form of str1[position] < str2[position]
     * Returns false if the position is out of bounds for any of the strings.
     * Note: this assumes that the position is relative to the zero index of the strings
     *
     * @param str1     The left-hand side of the comparison
     * @param str2     The right-hand side of the comparison
     * @param position The zero-indexed position of the characters to compare
     * @return False if the character at position in string 1 is not less than the character
     * at position in string 2 or position out of bounds for either string, otherwise true
     */
    public static boolean charLessThan(String str1, String str2, int position) {
        return isInBounds(str1, str2, position) // do out of bounds check first
                && str1.charAt(position) < str2.charAt(position); // run the comparison
    }

    /**
     * Checks that a given position is within bounds of both strings
     *
     * @param str1     The first string
     * @param str2     The second string
     * @param position The zero-indexed position
     * @return It should be obvious, but this is here to make my IDE happy
     */
    private static boolean isInBounds(String str1, String str2, int position) {
        return position >= 0 && position <= str1.length() - 1 && position <= str2.length() - 1;
    }

    /**
     * Internally performs the negation of {@link #charLessThan(String, String, int)}
     *
     * @param str1     The left-hand side of the comparison
     * @param str2     The right-hand side of the comparison
     * @param position The zero-indexed position of the characters to compare
     * @return str1[position] <= str2[position] (assuming in-bounds)
     */
    public static boolean charLessThanEqual(String str1, String str2, int position) {
        return isInBounds(str1, str2, position) && !Utils.charLessThan(str1, str2, position);
    }

    /**
     * Internally performs the negation of {@link #charGreaterThan(String, String, int)}
     *
     * @param str1     The left-hand side of the comparison
     * @param str2     The right-hand side of the comparison
     * @param position The zero-indexed position of the characters to compare
     * @return str1[position] >= str2[position] (assuming in-bounds)
     */
    public static boolean charGreaterThanEqual(String str1, String str2, int position) {
        return isInBounds(str1, str2, position) && !Utils.charGreaterThan(str1, str2, position);
    }

    /**
     * Randomly chooses a line from a given file
     *
     * @param f The pointer to the file to get the random lines from
     * @return The random string from file
     * @throws FileNotFoundException
     */
    public static String choose(File f) throws FileNotFoundException {
        String result = null;
        Random rand = new Random();
        int n = 0;
        for (Scanner sc = new Scanner(f); sc.hasNext(); ) {
            ++n;
            String line = sc.nextLine();
            if (rand.nextInt(n) == 0)
                result = line;
        }
        return result;
    }

    /**
     * Converts a string to a properly capitalized string with the first letter uppercase
     *
     * @param value The string to capitalize
     * @return The capitalized string (foo becomes Foo)
     */
    public static String upperCaseFirst(String value) {
        // Convert String to char array.
        char[] array = value.toCharArray();
        // Modify first element in array.
        array[0] = Character.toUpperCase(array[0]);
        // Return string.
        return new String(array);
    }

    public static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    public static boolean isCaret(char c) {
        return c == '^';
    }

    public static boolean isOperator(char c) {
        return c == '-' || c == '+';
    }
}
