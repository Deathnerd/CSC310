package com.gilleland.george.homework;

import com.gilleland.george.utils.QueueOverflowError;
import com.gilleland.george.utils.QueueUnderflowError;

/**
 * A circular queue for {@link com.gilleland.george.homework.Assignment6} using
 * an array to implement the queue structure.
 */
public class CircularQueue {

    /**
     * The maximum amount of items the queue can hold. If this is exceeded,
     * a {@link QueueOverflowError} is generated
     *
     * @see QueueOverflowError
     */
    public final int max_size;
    /**
     * The index in the array that the current front item of the queue is
     * stored at.
     */
    public int front = 0;
    /**
     * The index in the array that the current rear item in the queue is
     * stored at.
     */
    public int rear = -1;
    /**
     * The array holding the {@link Customer} objects for this Circular Queue
     * structure.
     *
     * @see Customer
     */
    Customer[] data;
    private int num_items = 0;

    /**
     * Initialize a Circular Queue that will hold up to {@link #max_size} number
     * of elements in it.
     *
     * @param max_size The maximum number of elements this Circular Queue can hold
     */
    CircularQueue(int max_size) {
        this.max_size = max_size;
        this.data = new Customer[this.max_size];
    }

    /**
     * <p>A convenience constructor that will take in any number of {@link Customer} objects
     * as initial data and attempt to queue them up. It will print out an error if the queue
     * overflows while attempting this</p>
     *
     * @param max_size     The maximum size of the queue
     * @param initial_data The Customer objects to enqueue
     * @see #enqueue(Customer...)
     */
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

    /**
     * <p>Attempts to add a {@link Customer} object to the rear of the current queue.
     * If the queue is full, then a {@link QueueOverflowError} is thrown.</p>
     * <p>Otherwise it will reposition the {@link #rear} index and add the customer
     * to the queue at the new position of {@link #rear}. It will then increment the
     * value stored in {@link #num_items} by 1, indicating that the number of items
     * stored in the queue has increased by 1.</p>
     *
     * @param customer The customer to attempt to add
     * @throws QueueOverflowError
     * @see #isFull()
     */
    public final void enqueue(Customer customer) throws QueueOverflowError {
        if (this.isFull()) {
            throw new QueueOverflowError();
        }
        if (this.rear == this.max_size - 1) {
            this.rear = -1;
        }
        this.data[++this.rear] = customer;
        this.num_items++;
    }

    /**
     * <p>Attempts to dequeue a {@link Customer} from the front of the
     * queue. If the queue is empty, then it will throw a {@link QueueUnderflowError}.</p>
     * <p>Otherwise, it will remove the {@link Customer} stored at the index of {@link #front},
     * re-position the {@link #front} index, and decrement the value stored in {@link #num_items}
     * before returning the {@link Customer} object that was at the front of the queue.</p>
     *
     * @return The customer at the front of the queue
     * @throws QueueUnderflowError
     * @see #isEmpty()
     * @see Customer
     * @see QueueUnderflowError
     */
    public Customer dequeue() throws QueueUnderflowError {
        if (this.isEmpty()) {
            throw new QueueUnderflowError();
        }
        Customer customer = this.data[this.front++];
        if (this.front == this.max_size) {
            this.front = 0;
        }
        this.num_items--;
        return customer;
    }

    /**
     * <p>Resets the queue by resetting the {@link #front} to 0 and
     * {@link #rear} to -1 and re-initializing {@link #data} to
     * a new {@link Customer} array with the size of {@link #max_size}</p>
     *
     * @see Customer
     */
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
