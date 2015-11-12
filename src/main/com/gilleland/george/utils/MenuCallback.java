package com.gilleland.george.utils;

import com.gilleland.george.objects.Choice;

/**
 * Created by Wes Gilleland on 11/12/2015.
 */
@FunctionalInterface
public interface MenuCallback {
    Choice execute(Choice c);
}
