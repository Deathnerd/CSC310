package com.gilleland.george.objects;

/**
 * An object containing properties and methods to
 * represent a Game for Assignment 7
 */
public class Game implements Comparable<Game> {
    /**
     * The name of the current Game
     */
    protected String data = "";

    /**
     * Return a new Game object with name
     *
     * @param name The name for the game
     */
    public Game(String name) {
        this.data = name;
    }

    /**
     * A method for determining the order of this Game object
     * to another Game object (is it equal to, less than, or greater than?)
     *
     * @param other The other game object to compare to
     * @return 0 if the objects are the same, < 0 if this object is less than the other,
     * > 0 if this object is greater than the other
     * @see Comparable#compareTo(Object)
     */
    @Override
    public int compareTo(Game other) {
        final int EQUAL = 0;

        if (this == other) {
            return EQUAL;
        }

        int comparison = this.data.compareTo(other.data);
        if (comparison != EQUAL) {
            return comparison;
        }

        assert this.equals(other) : "compareTo inconsistent with equals";
        return EQUAL;
    }

    /**
     * Convenience method to return a string representation of the current game
     *
     * @return The current game's name
     */
    @Override
    public String toString() {
        return String.format("%s", this.data);
    }
}
