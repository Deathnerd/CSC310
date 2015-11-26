package com.gilleland.george.interfaces;

/**
 * Created by Wes Gilleland on 11/23/2015.
 */
public interface Container {
    int num_items = 0;
    int max_size = 10;

    boolean isEmpty();

    boolean isFull();
}
