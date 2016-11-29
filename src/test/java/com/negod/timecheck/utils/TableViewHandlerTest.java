/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck.utils;

import com.isp.lpt.JfxTestRunner;
import com.negod.timecheck.database.dao.ProjectDao;
import com.negod.timecheck.database.exceptions.DaoException;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
@RunWith(JfxTestRunner.class)
public class TableViewHandlerTest {

    @FXML
    TableView projectsTable;

    public TableViewHandlerTest() {
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
     * Test of populateTable method, of class TableViewHandler.
     */
    @Ignore
    @Test
    public void testPopulateTable() throws DaoException {
        System.out.println("populateTable");

        ProjectDao dao = new ProjectDao();

        TableViewHandler instance = new TableViewHandler(projectsTable, dao);

    }

}
