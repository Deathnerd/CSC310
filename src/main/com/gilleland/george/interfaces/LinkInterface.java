package com.gilleland.george.interfaces;

import com.gilleland.george.objects.Link;

/**
 * Created by Wes Gilleland on 10/25/2015.
 */
public interface LinkInterface {
    Link previous = null;
    Link next = null;

    void displayLink();
}
