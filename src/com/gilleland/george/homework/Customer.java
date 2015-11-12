package com.gilleland.george.homework;

import java.util.Objects;

/**
 * An object to store customer data. Is a comparable so it can be sorted
 */
public class Customer implements Comparable<Customer> {
    /**
     * The name of the customer
     */
    private String name;
    /**
     * The customer's service number
     */
    private int service_number;

    /**
     * An object to store customer data. Is a comparable so it can be sorted
     *
     * @param name           The name of the customer
     * @param service_number The service number for the customer
     */
    Customer(String name, int service_number) {
        this.name = name;
        this.service_number = service_number;
    }

    /**
     * Compares one Customer object to another by their service numbers
     *
     * @param other The other Customer object
     * @return <table>
     * <tr>
     * <td>-1</td>
     * <td>This object is less than the other</td>
     * </tr>
     * <tr>
     * <td>0</td>
     * <td>The objects are equal</td>
     * </tr>
     * <tr>
     * <td>1</td>
     * <td>This object is greater than the other</td>
     * </tr>
     * </table>
     */
    @Override
    public int compareTo(Customer other) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;

        if (this == other) {
            return EQUAL;
        }
        int other_service_number = other.getServiceNumber();
        if (this.service_number == other_service_number) {
            return EQUAL;
        }
        if (this.service_number > other_service_number) {
            return AFTER;
        }
        if (this.service_number < other_service_number) {
            return BEFORE;
        }
        int comparison = this.name.compareTo(other.getName());
        if (comparison != EQUAL) {
            return comparison;
        }

        assert this.equals(other) : "compareTo inconsistent with equals";
        return EQUAL;
    }
    /*
     * GETTERS AND SETTERS BELOW
     */

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getServiceNumber() {
        return this.service_number;
    }

    public void setServiceNumber(int service_number) {
        this.service_number = service_number;
    }

    /**
     * Determines whether this customer is equal to some object or not.
     * Will check for correct types before attempting to cast to a Customer
     * object and do more equality checking. Currently checks for {@link #service_number}
     * and {@link #name} equalities between the two objects
     *
     * @param o The other object to test against
     * @return True if the other object is in fact this object, or if it's
     * another Customer object and if they have the same property values. False
     * if it's not a Customer object or they're both Customer objects and they do not
     * have completely equal properties
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) o;
        return this.service_number == other.getServiceNumber() && Objects.equals(this.name, other.getName());
    }

    /**
     * Generates a hash code based on the addition of the hash codes
     * of {@link #name} and {@link #service_number}
     *
     * @return The hash code for this object
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(this.name) + Objects.hashCode(this.service_number);
    }

    /**
     * Returns the customer's name and their service number for presentation
     *
     * @return Example: John [117]
     */
    @Override
    public String toString() {
        return String.format("%s [%d]", this.name, this.service_number);
    }
}
