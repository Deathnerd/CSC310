package com.gilleland.george.homework;

import com.gilleland.george.utils.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Wes Gilleland on 11/9/2015.
 */
public class Assignment6 extends HomeworkAssignment {

    /**
     * The number of customers each queue can have. Is used by {@link #queues}
     * to determine their max sizes
     */
    private final int num_customers_per_queue = 10;
    /**
     * Where the customers names are held until they are assigned numbers
     * and stored as {@link Customer} objects in to {@link #customers}
     *
     * @see Customer
     * @see #assign()
     */
    ArrayList<String> customers_input = new ArrayList<>();
    /**
     * The "counters" the customers are queued into
     */
    private CircularQueue[] queues = new CircularQueue[]{
            new CircularQueue(this.num_customers_per_queue),
            new CircularQueue(this.num_customers_per_queue),
            new CircularQueue(this.num_customers_per_queue),
            new CircularQueue(this.num_customers_per_queue)
    };
    /**
     * When the customers are assigned numbers and turned into {@link Customer}
     * objects, this is where they go.
     * @see Customer
     * @see #assign()
     * @see #assign(ArrayList)
     */
    private ArrayList<Customer> customers = new ArrayList<>();
    /**
     * The menu interaction system
     */
    private Menu menu = new Menu();

    /**
     * The main entry method
     */
    @Override
    public void run() {
        Choice choice;
        while (true) {
            choice = this.menu.display(
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
     * <p>This is the method used to get a list of customers from the user</p>
     * <p>First it clears the last input of customers before adding the new customers.
     * This is an assumption that, if the user is entering new customers, then they would
     * like to start fresh and enter new customers.</p>
     * <p>It then takes in customers as a comma-delimited string, splits them up along the
     * comma, and adds them all to the {@link #customers_input} property using
     * {@link java.util.Collections#addAll(Collection, Object[])}</p>
     *
     * @see java.util.Collections#addAll(Collection, Object[])
     */
    public void input() {
        this.customers_input.clear();
        System.out.print("Enter a list of customer names separated by a comma (Alice,Bob,Fred): ");
        String next = Menu.in.next();
        Collections.addAll(this.customers_input, next.split(","));
    }

    /**
     * <p>This is the method that will assign each customer with a service number.</p>
     * <p>Before doing anything, it clears out the current {@link #customers}
     * (not to be confused with {@link #customers_input}). This is an assumption that
     * if the user is manually assigning numbers, then they are done with the current customers.
     * It then uses {@link #customers_input}, assigns each customer a random number (from 0 to 40)
     * as a {@link Customer} object and adds them to the {@link #customers} variable where it
     * will then be used by the {@link #display_counter(int, int, int)}, {@link #display_customers()},
     * {@link #recategorize()}, {@link #categorize()}, and {@link #assign(ArrayList)} methods.</p>
     *
     * @see #customers
     * @see #customers_input
     * @see Customer
     * @see #display_counter(int, int, int)
     * @see #display_customers()
     * @see #recategorize()
     * @see #categorize()
     * @see #assign(ArrayList)
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
     * <p>Displays the customers currently with numbers assigned to them.</p>
     * <p>If there are no customers with assigned numbers ({@link #customers} is empty),
     * then the user is told and the method exits. If there are customers that have been
     * entered ({@link #customers_input} is not empty), but not assigned numbers, then
     * the user is told and the method exits.</p>
     *
     * @see #customers
     * @see #customers_input
     */
    public void display_customers() {
        if (this.customers.isEmpty()) {
            System.out.println("There are no customers with assigned numbers. Please assign service numbers to them!");
            return;
        } else if (!this.customers_input.isEmpty()) {
            System.out.println("There are some customers entered without assigned numbers. You may assign them numbers in the main menu.");
            return;
        }
        System.out.println("The current customers are: ");
        for (Customer c : this.customers) {
            System.out.println(c.toString());
        }
    }

    /**
     * <p>This is the method that's called from the user-driven menu
     * to categorize the customers to their respective queues based
     * on their service numbers. If an attempt is made to add the
     * customer to the queue and it is full, then the user will be notified
     * which customer was not able to be queued and the program will continue.</p>
     * <p> If there were customers that could not be queued, then the user
     * will be given another opportunity to assign the customers new numbers
     * and attempt to categorize them again. If that fails, then the customers
     * are discarded and the program continues. The user is notified of failures
     * and that condition.</p>
     */
    public void categorize() {
        /*
         * Reset all of the queues.
         * This assumes that when the user is ready to categorize,
         * they're done with their current queues and would like to
         * discard them.
         */
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
                this.customers.remove(c);
            } catch (QueueOverflowError e) {
                System.out.printf("Could not queue up %s. The corresponding queue is full!", c.toString());
            }
        }
        System.out.println("Done categorizing!");

        /*
         * There are still some customers waiting to be categorized.
         * Give the user an opportunity to re-assign them some numbers.
         */
        if (!this.customers.isEmpty()) {
            boolean reassign = this.menu.displayYesNo("There were some customers that could not be categorized. Would you like to attempt to reassign them numbers?");
            if (reassign) {
                this.assign(this.customers);
                System.out.println("Done! Attempting to re-queue these customers. If this fails, no other attempts will be offered.");
                this.recategorize();
            }
        }
    }

    /**
     * <p>A stripped down version of the {@link #categorize()} method in that it doesn't reset
     * the queues before it attempts to add customers to the queue. This is operating under
     * the assumption that this method will only be called if the user chooses to reassign users
     * that were not able to be initially put into a queue new numbers, where they will then
     * be given another chance to enter a queue. Just like the {@link #categorize()} method, this
     * will also alert the user if the attempt to queue would overflow the queue.</p>
     * <p>This method does not offer another chance to requeue customers. If they are not queued here,
     * then they will be discarded.</p>
     */
    private void recategorize() {
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
                this.customers.remove(c);
            } catch (QueueOverflowError e) {
                System.out.printf("Could not queue up %s. The corresponding queue is full!", c.toString());
            }
        }
    }

    private void assign(ArrayList<Customer> customers) {
        this.customers.clear();
        for (Customer c : customers) {
            c.setServiceNumber((new Random()).nextInt(40));
            this.customers.add(c);
        }
    }

    /**
     * <p>This is the method that's called from the user-driven menu
     * to display the customers currently queued at the counters.</p>
     * <p>It will dequeue the counters, but not re-queue them.</p>
     */
    public void display_counters() {
        this.display_counter(1, -1, 10);
        this.display_counter(2, 10, 20);
        this.display_counter(3, 20, 30);
        this.display_counter(4, 30, -1);
    }

    /**
     * <p>This method is called by {@link #display_counters()} to present a formatted
     * version of the contents of the queues. It does this by dequeueing the queues until
     * they are empty (in this implementation, a try/catch intercepts a {@link QueueUnderflowError}
     * and breaks the infinite loop) and printing the current Customer's string representation.</p>
     *
     * @param counter_number  The number of the counter to show. Also is the normal (non-zero)
     *                        index of the queue in {@link #queues}
     * @param beginning_range The beginning range for each counter, ie, the service numbers that
     *                        the customers should have
     * @param end_range       The end of the range for each counter, ie, the service numbers that
     *                        the customers should have
     * @see QueueUnderflowError
     * @see Customer#toString()
     * @see Customer
     */
    private void display_counter(int counter_number, int beginning_range, int end_range) {
        if (beginning_range == -1) {
            System.out.printf("Counter %d (< %d): ", counter_number, end_range);
        } else if (end_range == -1) {
            System.out.printf("Counter %d (> %d): ", counter_number, beginning_range);
        } else {
            System.out.printf("Counter %d (%d <= x < %d): ", counter_number, beginning_range, end_range);
        }
        CircularQueue counter = this.queues[counter_number - 1];
        if (counter.isEmpty()) {
            System.out.print("Empty!");
        }
        ArrayList<String> t = new ArrayList<>();
        while (true) {
            try {
                t.add(counter.dequeue().toString());
            } catch (QueueUnderflowError e) {
                break;
            }
        }
        System.out.println(String.join(", ", t));
    }
}
