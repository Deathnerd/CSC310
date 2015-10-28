package com.gilleland.george.homework;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Wes Gilleland on 10/25/2015.
 */
public class PolynomialTest {
    Polynomial poly;
    Polynomial poly2;
    String[] goodExpressions = new String[2];
    String[] badExpressions = new String[2];

    @BeforeMethod
    public void setUp() throws Exception {
        this.goodExpressions[0] = "+4x^2-5x^0";
        this.goodExpressions[1] = "-6x^4+3x^2+15x^1";
        this.badExpressions[0] = "4x^2-5x^1";
        this.badExpressions[1] = "+3x^2-6x^4+15x^1";
    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    public void testIsEmpty() throws Exception {

    }

    @Test
    public void testInsertFirst() throws Exception {

    }

    @Test
    public void testInsertLast() throws Exception {

    }

    @Test
    public void testInsert() throws Exception {

    }

    @Test
    public void testFindNode() throws Exception {

    }

    @Test
    public void testDeleteNode() throws Exception {

    }

    @Test
    public void testGetExpression() throws Exception {

    }

    @Test
    public void testSetExpression() throws Exception {

    }

    @Test
    public void testGetParts() throws Exception {

    }

    @Test
    public void testDisplayList() throws Exception {

    }

    @Test
    public void testDeleteFirst() throws Exception {

    }

    @Test
    public void testDeleteLast() throws Exception {

    }
}