package com.gilleland.george.homework;

import com.gilleland.george.exceptions.ElementNotFoundException;
import com.gilleland.george.exceptions.EmptyHeapException;
import com.gilleland.george.objects.Choice;
import com.gilleland.george.objects.Heap;
import com.gilleland.george.objects.HomeworkAssignment;
import com.gilleland.george.utils.Menu;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Wes Gilleland on 11/23/2015.
 */
public class Assignment8 extends HomeworkAssignment {
    protected Menu menu = new Menu();
    private Scanner in = new Scanner(System.in);
    private Heap<Integer> heap = null;

    /**
     * Main entry method for this assignment
     */
    @Override
    public void run() {
        Choice choice;
        while (true) {
            choice = this.menu.display(
                    new Choice("build", "Build a heap"),
                    new Choice("print_heap", "Print the heap"),
                    new Choice("insert", "Insert a node"),
                    new Choice("delete", "Delete the largest node"),
                    new Choice("search_heap", "Search the heap for a key")
            );
            if (choice.getIndex() > 0) {
                this._tryMethodCall(choice.getName().toLowerCase());
            } else {
                return;
            }
        }
    }

    /**
     * Builds a new heap. Alerts the user (not in this order particularly)
     * if there's an already existing heap and it's empty, there's an already
     * existing heap and it's got stuff in it, or this is the first run and
     * the first heap is being created.
     */
    public void build() {
        if (this.heap == null) {
            System.out.println("No heap exists! Creating a new heap!");
        } else if (this.heap.isEmpty()) {
            System.out.println("There's a heap, but it's empty. Not doing a thing!");
            return;
        } else {
            System.out.println("There's a heap already in memory. I'll destroy it and create a new one!");
        }
        this.heap = new Heap<>();
    }

    /**
     * Prints the contents of the heap in a breadth-first traversal
     * pattern. If the heap is empty, the user is alerted and the
     * method aborts
     */
    public void print_heap() {
        if (this.heap == null || this.heap.isEmpty()) {
            System.out.println("There's nothing in the heap!");
            return;
        }
        this.heap.displayHeap();
    }

    /**
     * Provides a method for the user to enter a number onto the heap.
     */
    public void insert() {
        System.out.print("Enter a number to insert into the heap: ");
        int n = this.in.nextInt();
        this.heap.insert(n);
        System.out.printf("\n Successfully inserted %d into the heap!\n", n);
    }

    /**
     * Deletes the largest item on the heap. If the heap is empty, it is aborted.
     */
    public void delete() {
        if (this.heap.isEmpty()) {
            System.out.println("The heap is empty! I can't remove something from nothing!");
            return;
        }
        try {
            System.out.printf("Removed %d from the heap! \n", this.heap.remove());
        } catch (EmptyHeapException e) {
            //
        }
    }

    /**
     * Performs a breadth-first search across the heap to find a given numeric key.
     * If the key cannot be found, the user is alerted. If the heap is empty, the user
     * is alerted and the search is aborted
     */
    public void search_heap() {
        if (this.heap.isEmpty()) {
            System.out.println("The heap is empty! Nothing to find!");
            return;
        }
        try {
            System.out.print("Enter a number to search for in the current heap: ");
            HashMap<String, Object> result = this.heap.search(this.in.nextInt());
            System.out.printf("The key" + result.get("object") + " was found in the heap at position " + result.get("index"));
        } catch (EmptyHeapException e) {
            //
        } catch (ElementNotFoundException e) {
            System.out.println("That key cannot be found in the current heap!");
        }
    }
}
