package com.gilleland.george.homework;

import com.gilleland.george.utils.QueueOverflowError;
import com.gilleland.george.utils.QueueUnderflowError;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Wes Gilleland on 11/9/2015.
 */
public class CircularQueue {

    public final int max_size;
    public int front = 0;
    public int rear = -1;
    Customer[] data;
    private int num_items = 0;

    CircularQueue(int max_size) {
        this.max_size = max_size;
        this.data = new Customer[this.max_size];
    }

    CircularQueue(int max_size, Customer... initial_data) {
        this.max_size = max_size;
        this.data = new Customer[this.max_size];
        try {
            this.enqueue(initial_data);
        } catch (QueueOverflowError e) {
            System.out.println("Could not instantiate new Circular Queue: Queue overflowed!");
        }
    }

    /**
     * A convenience method to take in any number of customers and attempt to add them to the queue
     *
     * @param customers The customers to add to the circular queue
     * @throws QueueOverflowError
     */
    public final void enqueue(Customer... customers) throws QueueOverflowError {
        for (Customer customer : customers) {
            this.enqueue(customer);
        }
    }

    public final void enqueue(Customer c) throws QueueOverflowError {
        if (this.isFull()) {
            throw new QueueOverflowError();
        }
        if (this.rear == this.max_size - 1) {
            this.rear = -1;
        }
        this.data[++this.rear] = c;
        this.num_items++;
    }

    /**
     * @return The customer at the front of the queue
     * @throws QueueUnderflowError
     */
    public Customer dequeue() throws QueueUnderflowError {
        if (this.isEmpty()) {
            throw new QueueUnderflowError();
        }
        Customer temp = this.data[this.front++];
        if (this.front == this.max_size) {
            this.front = 0;
        }
        this.num_items--;
        return temp;
    }

    public void reset() {
        this.front = 0;
        this.rear = -1;
        this.data = new Customer[this.max_size];
    }

    /**
     * Checks if the queue is empty
     *
     * @return is the number of items in the queue equal to 0?
     */
    public boolean isEmpty() {
        return this.num_items == 0;
    }

    /**
     * Checks if the queue is full
     *
     * @return is the number of items in the queue equal to the max size?
     */
    public boolean isFull() {
        return this.num_items == this.max_size;
    }
}
