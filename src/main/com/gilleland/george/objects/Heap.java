package com.gilleland.george.objects;

import com.gilleland.george.exceptions.ElementNotFoundException;
import com.gilleland.george.exceptions.EmptyHeapException;
import com.gilleland.george.interfaces.Container;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Wes Gilleland on 11/23/2015.
 */
public class Heap<T extends Comparable<T>> implements Container {
    protected T[] heap;
    private int max_size = 10;
    private int num_items = 0;

    public Heap() {
        this.heap = (T[]) new Comparable[max_size];
    }

    /**
     * Insert at the bottom of the heap and perform
     * a bubble up operation at the end to preserve correct
     * order of the heap
     *
     * @param item The item to insert into the heap
     */
    public void insert(T item) {
        if (this.isFull()) {
            this.heap = this.resize();
        }
        this.heap[this.num_items] = item;
        this.bubbleUp();
        this.num_items++;
    }

    /**
     * Removes and returns the largest element in the heap (the head)
     *
     * @return The largest element in the heap (the head)
     * @throws EmptyHeapException If the heap is empty
     */
    public T remove() throws EmptyHeapException {
        if (this.isEmpty()) {
            throw new EmptyHeapException();
        }
        T temp = this.peek();
        this.heap[0] = this.heap[this.num_items];
        this.bubbleDown();
        this.heap[this.num_items] = null;
        this.num_items--;
        return temp;
    }

    /**
     * // TODO: 11/23/2015 Clean up the description
     * Given a key to search for, do a linear search across the backing array
     * for the heap.
     *
     * @param thing The thing to search for
     * @return A {@link HashMap} with the keys "index" and "object" each containing
     * the key's index and the object in the heap respectively
     * @throws EmptyHeapException
     * @throws ElementNotFoundException
     */
    public HashMap<String, Object> search(T thing) throws EmptyHeapException, ElementNotFoundException {
        if (this.isEmpty()) {
            throw new EmptyHeapException();
        }
        for (int i = 0; i < this.heap.length; i++) {
            if (this.heap[i].compareTo(thing) == 0) {
                HashMap<String, Object> temp = new HashMap<>();
                temp.put("index", i);
                temp.put("object", this.heap[i]);
                return temp;
            }
        }
        throw new ElementNotFoundException();
    }

    /**
     * Is the number of items in the heap zero?
     *
     * @return Is the heap empty?
     */
    @Override
    public boolean isEmpty() {
        return num_items == 0;
    }

    /**
     * Is the number of items in the heap equal to the length
     * of the backing array?
     *
     * @return Is the heap full?
     */
    @Override
    public boolean isFull() {
        return num_items == this.heap.length;
    }

    public T peek() throws EmptyHeapException {
        if (this.isEmpty()) {
            throw new EmptyHeapException();
        }
        return this.heap[0];
    }

    /**
     * Returns the internal array representation of the heap as a string
     *
     * @return The internal array representation of the heap as a string
     */
    @Override
    public String toString() {
        return Arrays.toString(this.heap);
    }

    /**
     * Display the current contents of the heap in a breadth-first search pattern
     */
    public void displayHeap() {
        System.out.println("The current contents of the heap are: ");
        String t = "[";
        for (T aHeap : this.heap) {
            if (aHeap != null) {
                t += aHeap + ",";
            }
        }
        System.out.printf("%s]\n", t.substring(0, t.lastIndexOf(',')));
    }

    /**
     * Preserves the heap min structure upon insertion
     */
    private void bubbleUp() {
        int index = this.num_items;
        while (index >= 0 && this.heap[index].compareTo(getParent(index)) > 0) {
            this.swap(index, this.getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    /**
     * Preserves the heap min structure when a node is removed
     */
    private void bubbleDown() {
        int index = 0;
        while (this.getLeftChildIndex(index) <= this.num_items) {
            int smaller = getLeftChildIndex(index);
            int comparison = this.heap[this.getLeftChildIndex(index)].compareTo(this.heap[this.getRightChildIndex(index)]);
            if (this.getRightChildIndex(index) <= this.num_items && comparison > 0) {
                smaller = this.getRightChildIndex(index);
            }
            comparison = this.heap[index].compareTo(this.heap[smaller]);
            if (comparison > 0) {
                swap(index, smaller);
            } else {
                return;
            }
            index = smaller;
        }
    }

    private T getParent(int child_index) {
        return this.heap[this.getParentIndex(child_index)];
    }

    private int getParentIndex(int child_index) {
        return child_index / 2;
    }

    private T getLeftChild(int parent_index) {
        return this.heap[this.getLeftChildIndex(parent_index)];
    }

    private int getLeftChildIndex(int parent_index) {
        return (2 * parent_index) + 1;
    }

    private boolean hasLeftChild(int parent_index) {
        return this.getRightChild(parent_index) != null;
    }

    private T getRightChild(int parent_index) {
        return this.heap[this.getRightChildIndex(parent_index)];
    }

    private int getRightChildIndex(int parent_index) {
        return (2 * parent_index) + 2;
    }

    private boolean hasRightChild(int parent_index) {
        return this.getRightChild(parent_index) != null;
    }

    /**
     * <p>Resizes the backing array of the heap to double its current size.</p>
     * <p>Note: Size starts at 10 items</p>
     * TODO: 11/23/2015 Figure out an algorithm to scale gracefully to predict large data sets
     *
     * @return The resized array for the heap
     */
    private T[] resize() {
        max_size = this.heap.length * 2;
        return Arrays.copyOf(this.heap, this.max_size);
    }

    /**
     * Internal convenience method to swap two array elements
     *
     * @param index_1 The index of the first element
     * @param index_2 The index of the second element
     */
    private void swap(int index_1, int index_2) {
        T temp = this.heap[index_1];
        this.heap[index_1] = this.heap[index_2];
        this.heap[index_2] = temp;
    }
}
