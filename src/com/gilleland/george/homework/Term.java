package com.gilleland.george.homework;

import com.gilleland.george.utils.Link;

/**
 * Created by Wes Gilleland on 10/25/2015.
 */
public class Term extends Link {
    private int coefficient;
    private int exponent;

    public Term(int coefficient, int exponent) {
        this.setCoefficient(coefficient);
        this.setExponent(exponent);
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public int getExponent() {
        return exponent;
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    @Override
    public void displayLink() {
        if (this.coefficient > 0) {
            System.out.printf("{+%d,%d}", this.coefficient, this.exponent);
        } else {
            System.out.printf("{%d,%d}", this.coefficient, this.exponent);
        }
    }
}
