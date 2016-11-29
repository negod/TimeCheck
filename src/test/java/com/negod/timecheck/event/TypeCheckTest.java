/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck.event;

import com.negod.timecheck.database.entity.Project;
import com.negod.timecheck.event.exceptions.TypeCastException;
import com.negod.timecheck.mockdata.ProjectEntityMock;
import com.negod.timecheck.mockdata.TestEntity;
import com.negod.timecheck.mockdata.TestEntityMock;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
@RunWith(Parameterized.class)
public class TypeCheckTest {

    public static final Boolean BOOLEAN_VALUE = TestEntityMock.BOOLEAN_VALUE;
    public static final Double DOUBLE_VALUE = TestEntityMock.DOUBLE_VALUE;
    public static final Integer INTEGER_VALUE = TestEntityMock.INTEGER_VALUE;
    public static final Long LONG_VALUE = TestEntityMock.LONG_VALUE;
    public static final Date UPDATED_DATE = TestEntityMock.UPDATED_DATE;
    public static final String STRING_VALUE = TestEntityMock.STRING_VALUE;
    public static final TestEntity ENTITY = TestEntityMock.getEntity();

    public TypeCheckTest() {
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

    @Parameter
    public static Object INPUT;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Parameters(name = "test for {0}")
    public static List<Object> data() {
        List<Object> list = new ArrayList<Object>();
        list.add(BOOLEAN_VALUE);
        list.add(DOUBLE_VALUE);
        list.add(INTEGER_VALUE);
        list.add(LONG_VALUE);
        list.add(UPDATED_DATE);
        list.add(STRING_VALUE);
        list.add(ENTITY);
        list.add(null);
        return list;
    }

    @Ignore
    @Test()
    public void testGetDataWrongObject() throws TypeCastException {
        TypeCheck instance = new TypeCheck(INPUT);
        Optional current = instance.getAsObject();

        if (INPUT != null) {
            assertTrue(current.isPresent());
        } else {
            assertFalse(current.isPresent());
        }

        if (!(INPUT instanceof Double)) {
            current = instance.getAsDouble();
            assertFalse(current.isPresent());
        }

        if (!(INPUT instanceof Integer)) {
            current = instance.getAsInteger();
            assertFalse(current.isPresent());
        }

        if (!(INPUT instanceof Long)) {
            current = instance.getAsLong();
            assertFalse(current.isPresent());
        }

        if (!(INPUT instanceof String) && INPUT != null) {
            current = instance.getAsString();
            assertTrue(current.isPresent());
            assertTrue(INPUT.equals(current.get().toString()));
        }

    }

    /**
     * Test of getAsObject method, of class TypeCheck.
     */
    @Test
    public void testGetAsObject() throws Exception {
        System.out.println("getAsObject");
        TypeCheck instance = new TypeCheck(ENTITY);
        Optional<TestEntity> asObject = instance.getAsObject();
        assertTrue(asObject.isPresent());
        assertTrue(ENTITY.equals(asObject.get()));
    }

    @Test
    public void testGetAsObjectNull() throws Exception {
        System.out.println("getAsObject");
        TestEntity entity = TestEntityMock.getEntity();
        TypeCheck instance = new TypeCheck(null);
        Optional<TestEntity> asObject = instance.getAsObject();
        assertFalse(asObject.isPresent());
    }

    @Test
    public void testGetAsObjectWrongObject() throws Exception {
        System.out.println("getAsObject");
        Project project = ProjectEntityMock.getProject();
        TestEntity entity = TestEntityMock.getEntity();
        TypeCheck instance = new TypeCheck(project);
        Optional<TestEntity> asObject = instance.getAsObject();
        assertTrue(asObject.isPresent());
    }

    /**
     * Test of getAsLong method, of class TypeCheck.
     */
    @Test
    public void testGetAsLong() throws Exception {
        System.out.println("getAsLong");
        TypeCheck instance = new TypeCheck(LONG_VALUE);
        Optional<Long> asObject = instance.getAsObject();
        assertTrue(asObject.isPresent());
        assertTrue(LONG_VALUE.equals(asObject.get()));
    }

    /**
     * Test of getAsString method, of class TypeCheck.
     */
    @Test
    public void testGetAsString() throws Exception {
        System.out.println("getAsLong");
        TypeCheck instance = new TypeCheck(STRING_VALUE);
        Optional<String> asObject = instance.getAsObject();
        assertTrue(asObject.isPresent());
        assertTrue(STRING_VALUE.equals(asObject.get()));
    }

    /**
     * Test of getAsInteger method, of class TypeCheck.
     */
    @Test
    public void testGetAsInteger() throws Exception {
        System.out.println("getAsLong");
        TypeCheck instance = new TypeCheck(INTEGER_VALUE);
        Optional<Integer> asObject = instance.getAsObject();
        assertTrue(asObject.isPresent());
        assertTrue(INTEGER_VALUE.equals(asObject.get()));
    }

    /**
     * Test of getAsDouble method, of class TypeCheck.
     */
    @Test
    public void testGetAsDouble() throws Exception {
        System.out.println("getAsLong");
        TypeCheck instance = new TypeCheck(DOUBLE_VALUE);
        Optional<Double> asObject = instance.getAsObject();
        assertTrue(asObject.isPresent());
        assertTrue(DOUBLE_VALUE.equals(asObject.get()));
    }

}
