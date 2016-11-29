/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck;

import com.negod.timecheck.controller.TodayTabController;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
public class FXMLControllerTest {

    public FXMLControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getOverTimeInMillis method, of class TodayTabController.
     */
    @Test
    public void testGetOverTimeInMillis() {
        System.out.println("getOverTimeInMillis");
        Long currentTime = 2000L;
        Long workHours = 1000L;
        TodayTabController instance = new TodayTabController();
        Long expResult = 1000L;
        Long result = instance.getOverTimeInMillis(currentTime, workHours);
        assertEquals(expResult, result);
    }

    /**
     * Test of getMillisecondsFromHours method, of class TodayTabController.
     */
    @Ignore
    @Test
    public void testGetMillisecondsFromHours() {
        System.out.println("getMillisecondsFromHours");
        Integer hours = 8;
        TodayTabController instance = new TodayTabController();
        Long expResult = 28800000L;
        Long result = instance.getMillisecondsFromHours(hours);
        assertEquals(expResult, result);
    }

}
