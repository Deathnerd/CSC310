package com.gilleland.george.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * Utilities class
 *
 * @author Wes Gilleland
 */
public class Utils {

    /**
     * <p>Randomly chooses a line from a given file</p>
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
     * <p>Checks if the character passed is a unary (+ or -) operator.</p>
     *
     * @param character The character to check
     * @return The character is a + or -
     */
    public static boolean isPlusOrMinus(char character) {
        return Utils.isOneOf(character, '+', '-');
    }

    /**
     * <p>Checks if the character passed is a multiplication sign or division sign (* or /).</p>
     *
     * @param character The character to check
     * @return If the character is a * or /
     */
    public static boolean isMultOrDiv(char character) {
        return Utils.isOneOf(character, '*', '/');
    }

    /**
     * <p>Checks if the character in the first argument is one of the characters in the remaining arguments.</p>
     *
     * @param character The character to check for belonging
     * @param ops       The characters the first argument might be
     * @return If the first parameter is equal to one of the other arguments
     */
    public static boolean isOneOf(char character, char... ops) {
        for (char op : ops) {
            if (character == op) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>Performs a range check on the ASCII value of the character passed
     * to determine if it's a number between 0 and 9 all inclusive</p>
     *
     * @param character The character to check
     * @return The character is a number between 0 and 9 all inclusive
     */
    public static boolean isNumber(char character) {
        return (character - '0' >= 0 && character - '0' <= 9);
    }

    /**
     * <p>Determine if the character is a parenthesis</p>
     *
     * @param character The character to check
     * @return The character is a parenthesis
     */
    public static boolean isParenthesis(char character) {
        return (character == ')' || character == '(');
    }

    /**
     * <p>Determine if the character is a square bracket</p>
     *
     * @param character The character to check
     * @return The character is a square bracket
     */
    public static boolean isSquareBracket(char character) {
        return (character == ']' || character == '[');
    }

    /**
     * <p>Determine if the character is a curly bracket</p>
     *
     * @param character The character to check
     * @return The character is a curly bracket
     */
    public static boolean isCurlyBracket(char character) {
        return (character == '}' || character == '{');
    }

    /**
     * <p>Checks against all known grouping characters and whether the given
     * character is one of them</p>
     * <p>The current grouping characters are as follows: <pre>(){}[]</pre> </p>
     *
     * @param character The character to check
     * @return The character is a grouping character
     */
    public static boolean isGroupingCharacter(char character) {
        return Utils.isParenthesis(character) ||
                Utils.isCurlyBracket(character) ||
                Utils.isSquareBracket(character);
    }

    public static boolean between(int var, int lower, int upper) {
        return (var <= upper && var >= lower);
    }
}
