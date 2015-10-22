package com.gilleland.george.utils;

/**
 * A simple object for Menu choices
 */
public class Choice {
    private String name;
    private int index;

    public Choice(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        if (this.index == 0) {
            return this.name;
        }
        return String.format("%d. %s", this.index, this.name);
    }
}
