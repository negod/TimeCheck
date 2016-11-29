/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck.utils;

import com.negod.timecheck.database.entity.Project;
import com.negod.timecheck.mockdata.ProjectEntityMock;
import com.negod.timecheck.mockdata.TestEntity;
import com.negod.timecheck.mockdata.TestEntityMock;
import java.lang.reflect.Field;
import java.util.Optional;
import javafx.scene.control.TableColumn;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
public class FieldHelperTest {

    public static final String FIELD_NAME = "name";
    public static final String FIELD_NAME_STRING_VALUE = "stringValue";
    public static final String FIELD_NAME_CAPITAL = "Name";

    public FieldHelperTest() {
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
     * Test get a field by its name from a class - happy flow
     *
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void testGetFieldNotNull() throws IllegalArgumentException, IllegalAccessException {
        System.out.println("testGetFieldNotNull");
        Project project = ProjectEntityMock.getProject();
        Optional<Field> f = FieldHelper.getField(project.getClass(), FIELD_NAME);

        assertNotNull(f);
        assertTrue(f.isPresent());
        assertEquals(FIELD_NAME, f.get().getName());
        assertEquals(ProjectEntityMock.NAME, f.get().get(project).toString());
    }

    /**
     * Test get a field by its name from a class - Entity class parameter null
     *
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void testGetClassNull() throws IllegalArgumentException, IllegalAccessException {
        System.out.println("testGetFieldClassNull");
        Optional<Field> f = FieldHelper.getField(null, FIELD_NAME);

        assertNotNull(f);
        assertFalse(f.isPresent());
    }

    /**
     * Test get a field by its name from a class - Field String name null
     *
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void testGetFieldNullName() throws IllegalArgumentException, IllegalAccessException {
        System.out.println("testGetFieldNullName");
        Project project = ProjectEntityMock.getProject();
        Optional<Field> f = FieldHelper.getField(project.getClass(), null);

        assertNotNull(f);
        assertFalse(f.isPresent());
    }

    /**
     * Test getting a Column based on a Field - Happy flow
     */
    @Test
    public void testGetTableColumnString() {
        System.out.println("testGetTableColumnString");
        TestEntity entity = TestEntityMock.getEntity();

        Optional<Field> field = FieldHelper.getField(entity.getClass(), TestEntityMock.STRING_PROPERTY_NAME);
        assertTrue("Field from FieldHelper is not present", field.isPresent());

        Optional<TableColumn> tableColumn = FieldHelper.getTableColumn(field.get());
        assertTrue("Table column is not present", tableColumn.isPresent());

        String capitalized = StringUtils.capitalize(TestEntityMock.STRING_PROPERTY_NAME);
        //Columns header
        assertEquals(capitalized, tableColumn.get().getText());

    }

    @Test
    public void testGetTableColumnInteger() {
        System.out.println("testGetTableColumnString");
        TestEntity entity = TestEntityMock.getEntity();

        Optional<Field> field = FieldHelper.getField(entity.getClass(), TestEntityMock.INTEGER_PROPERTY_NAME);
        assertTrue("Field from FieldHelper is not present", field.isPresent());

        Optional<TableColumn> tableColumn = FieldHelper.getTableColumn(field.get());
        assertTrue("Table column is not present", tableColumn.isPresent());

        String capitalized = StringUtils.capitalize(TestEntityMock.INTEGER_PROPERTY_NAME);
        //Columns header
        assertEquals(capitalized, tableColumn.get().getText());

    }

    @Test
    public void getFieldValue() {
        System.out.println("getFieldValue");
        TestEntity entity = TestEntityMock.getEntity();

        Optional<String> value = FieldHelper.getFieldValue(entity, FIELD_NAME_STRING_VALUE);
        assertTrue("Value from FieldHelper is not present", value.isPresent());

        assertEquals(entity.getStringValue(), value.get());

    }

    @Test
    public void getFieldValueEntityNull() {
        System.out.println("getFieldValueNull");
        Optional<String> value = FieldHelper.getFieldValue(null, FIELD_NAME_STRING_VALUE);
        assertFalse("Value should not be present", value.isPresent());
    }

    @Test
    public void getFieldValleFieldNameNull() {
        System.out.println("getFieldValueNull");
        TestEntity entity = TestEntityMock.getEntity();
        Optional<String> value = FieldHelper.getFieldValue(entity, null);
        assertFalse("Value should not be present", value.isPresent());
    }

}
