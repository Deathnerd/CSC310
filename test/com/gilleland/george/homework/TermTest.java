package com.gilleland.george.homework;

import com.gilleland.george.objects.Term;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Wes Gilleland on 10/25/2015.
 */
public class TermTest {
    Term t;

    @Before
    public void setUp() throws Exception {
        this.t = new Term(10, 5);
    }

    @After
    public void tearDown() throws Exception {
        this.t = null;
    }

    @Test
    public void testGetCoefficient() throws Exception {
        assertEquals(10, this.t.getCoefficient());
    }

    @Test
    public void testGetExponent() throws Exception {
        assertEquals(5, this.t.getExponent());
    }
}