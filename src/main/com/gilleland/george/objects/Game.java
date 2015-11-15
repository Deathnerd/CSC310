package com.gilleland.george.objects;

/**
 * Created by Wes Gilleland on 11/13/2015.
 */
public class Game implements Comparable<Game> {
    private String data = "";

    public Game(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    @Override
    public int compareTo(Game other) {
        final int EQUAL = 0;

        if (this == other) {
            return EQUAL;
        }

        int comparison = this.data.compareTo(other.getData());
        if (comparison != EQUAL) {
            return comparison;
        }

        assert this.equals(other) : "compareTo inconsistent with equals";

        return EQUAL;
    }

    @Override
    public String toString() {
        return String.format("%s", this.data);
    }
}
