package com.gilleland.george.utils;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Wes Gilleland on 10/25/2015.
 */
public class Link implements LinkInterface {
    public Link previous = null;
    public Link next = null;
    @Override
    public void displayLink() {
        throw new NotImplementedException();
    }
}
