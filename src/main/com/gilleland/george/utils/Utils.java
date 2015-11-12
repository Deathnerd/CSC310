package com.gilleland.george.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Utilities class
 *
 * @author Wes Gilleland
 */
public class Utils {

    /**
     * Randomly chooses a word from a list of words
     *
     * @param words The list of words to choose from
     * @return The first element after shuffling all words in the list
     */
    public static String choose(List<String> words) {
        Collections.shuffle(words);
        return words.get(0);
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
    public static boolean isOneOf(char character, Character... ops) {
        return Arrays.asList(ops).stream().anyMatch((c) -> c == character);
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
