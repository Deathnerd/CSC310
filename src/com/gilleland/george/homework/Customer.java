package com.gilleland.george.homework;

import java.util.Objects;
import java.util.Random;

/**
 * Created by Wes Gilleland on 11/9/2015.
 */
public class Customer implements Comparable<Customer> {
    private String name;
    private int service_number;

    Customer(String name, int service_number) {
        this.name = name;
        this.service_number = service_number;
    }

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

    @Override
    public int compareTo(Customer o) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;

        if (this == o) {
            return EQUAL;
        }
        int other_service_number = o.getServiceNumber();
        if (this.service_number == other_service_number) {
            return EQUAL;
        }
        if (this.service_number > other_service_number) {
            return AFTER;
        }
        if (this.service_number < other_service_number) {
            return BEFORE;
        }
        int comparison = this.name.compareTo(o.getName());
        if (comparison != EQUAL) {
            return comparison;
        }

        assert this.equals(o) : "compareTo inconsistent with equals";
        return EQUAL;
    }

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

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name) + Objects.hashCode(this.service_number);
    }

    @Override
    public String toString() {
        return String.format("%s [%d]", this.name, this.service_number);
    }
}
