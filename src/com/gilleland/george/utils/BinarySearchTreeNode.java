package com.gilleland.george.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Wes Gilleland on 11/4/2015.
 */
public class BinarySearchTreeNode<V> implements Comparator<V>, Comparable<V> {
    BinarySearchTreeNode<V> left;
    BinarySearchTreeNode<V> right;
    V data;

    public BinarySearchTreeNode(V data) {
        this.data = data;
    }

    @Override
    public int compareTo(V o) {
//        return (this.data).compareTo()
        return 0;
    }

    @Override
    public int compare(V o1, V o2) {
        return 0;
    }
}
