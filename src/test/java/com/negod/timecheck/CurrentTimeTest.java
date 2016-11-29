/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck;

import com.negod.timecheck.controller.TodayTabController;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Date;
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
public class CurrentTimeTest {

    TodayTabController clazz = new TodayTabController();

    Long ONE_HOUR_IN_MILLIS = 3600000L;
    String TIME_STRING = "01:00:00";

    public CurrentTimeTest() {
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

    @Ignore
    @Test
    public void date() {
        Long millisecondsFromHours = clazz.getMillisecondsFromHours(1);
        assertEquals(millisecondsFromHours, ONE_HOUR_IN_MILLIS);
    }

    @Test
    public void date2() {
        LocalTime parse = LocalTime.parse(TIME_STRING);
        Date date = new Date(ONE_HOUR_IN_MILLIS);
        Instant instant = Instant.ofEpochMilli(date.getTime());
    }
}
