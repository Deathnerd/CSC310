package com.gilleland.george.objects;

import com.gilleland.george.interfaces.Container;
import com.gilleland.george.interfaces.Tree;

/**
 * Created by Wes Gilleland on 11/23/2015.
 */
public class Trie implements Tree, Container {
    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isFull() {
        return false;
    }
}
