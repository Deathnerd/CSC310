package com.gilleland.george.interfaces;

import com.gilleland.george.exceptions.ImproperInsertionOrderException;
import com.gilleland.george.objects.Link;
import com.gilleland.george.exceptions.NodeNotFoundException;

/**
 * Created by Wes Gilleland on 10/25/2015.
 */
public interface LinkListInterface {
    Link first = null;
    Link last = null;

    boolean isEmpty();

    void insertFirst(int... data_items) throws ImproperInsertionOrderException;

    void insertLast(int... data_items) throws ImproperInsertionOrderException;

    void insert(int... data_items);

    Link findNode(int key) throws NodeNotFoundException;

    void deleteNode(int key) throws NodeNotFoundException;

    void displayList();

    void deleteFirst() throws NodeNotFoundException;

    void deleteLast() throws NodeNotFoundException;
}
