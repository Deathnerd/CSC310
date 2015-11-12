package com.gilleland.george.utils;

/**
 * A simple object for Menu choices
 */
public class Choice {
    private String name;
    private String prompt;
    private int index;

    public Choice(String name) {
        this.name = name;
        this.prompt = null;
    }

    public Choice(String name, String prompt) {
        this.name = name;
        this.prompt = prompt;
    }

    @Override
    public String toString() {
        if (this.index == 0) {
            return this.name;
        }
        return String.format("%d. %s", this.index, this.name);
    }

    /*
     *=======================
     * Getters and setters
     *=======================
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrompt() {
        return this.prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
