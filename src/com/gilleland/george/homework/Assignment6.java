package com.gilleland.george.homework;

import com.gilleland.george.utils.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Wes Gilleland on 11/9/2015.
 */
public class Assignment6 extends HomeworkAssignment {

    private final int num_customers_per_queue = 10;
    ArrayList<String> customers_input = new ArrayList<>();
    private CircularQueue[] queues = new CircularQueue[]{
            new CircularQueue(this.num_customers_per_queue),
            new CircularQueue(this.num_customers_per_queue),
            new CircularQueue(this.num_customers_per_queue),
            new CircularQueue(this.num_customers_per_queue)
    };
    private ArrayList<Customer> customers = new ArrayList<>();

    /**
     * The main entry method
     */
    @Override
    public void run() {
        Menu menu = new Menu();
        Choice choice;
        while (true) {
            choice = menu.display(
                    new Choice("Input", "Input a list of customers"),
                    new Choice("Assign", "Randomly assign each customer a service number"),
                    new Choice("Display_customers", "Display all the customers with their service numbers"),
                    new Choice("Categorize", "Categorize the customers into four counters"),
                    new Choice("Display_counters", "Display the counters")
            );
            if (choice.getIndex() > 0) {
                this._tryMethodCall(choice.getName().toLowerCase());
            } else {
                return;
            }
        }
    }

    /**
     * This is the method used to get a list of customers from the user
     */
    public void input() {
        this.customers_input.clear();
        while (true) {
            System.out.print("Enter a list of customer names separated by a comma (Alice,Bob,Fred) or Done to exit: ");
            String next = Menu.in.next();
            if (next.equalsIgnoreCase("Done")) {
                System.out.println("Okay!");
                break;
            }
            Collections.addAll(this.customers_input, next.split(","));
        }
    }

    /**
     * This is the method that will assign each customer with a service number
     */
    public void assign() {
        this.customers.clear();
        System.out.println("Queuing up customers!");
        for (String customer : this.customers_input) {
            Customer c = new Customer(customer.trim(), (new Random()).nextInt(40));
            this.customers.add(c);
        }
    }

    /**
     *
     */
    public void display_customers() {
        if (this.customers.isEmpty()) {
            System.out.println("There are no customers!");
            return;
        }
        this.customers.sort(null);
        System.out.println("The current customers are: ");
        for (Customer c : this.customers) {
            System.out.println(c.toString());
        }
    }

    /**
     *
     */
    public void categorize() {
        for (CircularQueue q : this.queues) {
            q.reset();
        }
        for (Customer c : this.customers) {
            try {
                int service_number = c.getServiceNumber();
                if (service_number < 10) {
                    this.queues[0].enqueue(c);
                } else if (service_number < 20 && service_number >= 10) {
                    this.queues[1].enqueue(c);
                } else if (service_number < 30 && service_number >= 20) {
                    this.queues[2].enqueue(c);
                } else {
                    this.queues[3].enqueue(c);
                }
            } catch (QueueOverflowError e) {
                //
            }
        }
        System.out.println("Done categorizing!");
    }

    /**
     *
     */
    public void display_counters() {
        int i = 1;
        for (CircularQueue q : this.queues) {
            System.out.printf("Counter %d: ", i);
            if (q.isEmpty()) {
                System.out.print("Empty!");
            }
            while (true) {
                try {
                    System.out.printf("%s ", q.dequeue().toString());
                } catch (QueueUnderflowError e) {
                    break;
                }
            }
            System.out.println();
            i++;
        }
    }
}
