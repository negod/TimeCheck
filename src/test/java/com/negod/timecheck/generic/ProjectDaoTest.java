/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck.generic;

import com.negod.timecheck.database.dao.ProjectDao;
import com.negod.timecheck.database.entity.Project;
import com.negod.timecheck.mockdata.ProjectEntityMock;
import java.util.Optional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectDaoTest {

    private static final String UPDATED_NAME = "UPDATED_NAME";

    public ProjectDaoTest() {
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

//    /**
//     * Test of extractSearchFields method, of class GenericDao.
//     */
//    @Test
//    public void testExtractSearchFields() {
//        System.out.println("extractSearchFields");
//        GenericDao instance = null;
//        Set<String> expResult = null;
//        Set<String> result = instance.extractSearchFields(null);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of persist method, of class GenericDao.
     */
    @Test
    public void testPersist() throws Exception {
        Optional<Project> persist = persist();
        Optional<Project> getById = getById(persist.get().getId(), persist);
        Optional<Project> update = update(persist, UPDATED_NAME);
        Optional<Project> delete = delete(update);
    }

    private Optional<Project> delete(Optional<Project> project) throws Exception {
        System.out.println("delete");

        ProjectDao instance = new ProjectDao();

        Optional<Project> getById = instance.getById(project.get().getId());

        assertNotNull(getById);
        assertTrue(getById.isPresent());

        instance.delete(project.get().getId());

        Optional<Project> verifyResult = instance.getById(project.get().getId());

        assertFalse(verifyResult.isPresent());

        return project;

    }

    private Optional<Project> persist() throws Exception {

        System.out.println("persist");
        Project entity = ProjectEntityMock.getProject();

        ProjectDao instance = new ProjectDao();
        Optional<Project> result = instance.persist(entity);

        assertTrue(result.isPresent());
        assertTrue(result.get().getInternalId() == 1L);
        assertTrue(result.get().getId().matches("[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}"));
        assertTrue(result.get().getUpdatedDate() != null);

        return result;

    }

    private Optional<Project> update(Optional<Project> project, String newName) throws Exception {
        System.out.println("update");

        ProjectDao instance = new ProjectDao();

        Optional<Project> byId = instance.getById(project.get().getId());
        Optional<Project> byIdOld = project;

        assertTrue(byId.isPresent());
        assertEquals(ProjectEntityMock.NAME, byId.get().getName());

        byId.get().setName(newName);

        Optional<Project> update = instance.update(byId.get());
        Optional<Project> verifyResult = instance.getById(update.get().getId());

        assertFalse(verifyResult.get().equals(byIdOld.get()));
        assertTrue(verifyResult.get().getName().equals(newName));
        assertTrue(verifyResult.get().getId().equals(byIdOld.get().getId()));
        assertFalse(verifyResult.get().getUpdatedDate().equals(byIdOld.get().getUpdatedDate()));

        return verifyResult;

    }

    private Optional<Project> getById(String id, Optional<Project> project) throws Exception {

        System.out.println("getById");

        ProjectDao instance = new ProjectDao();
        Optional<Project> expResult = project;

        assertNotNull(expResult);
        assertTrue(expResult.isPresent());

        Optional<Project> getById = instance.getById(id);

        assertTrue(getById.isPresent());
        assertEquals(expResult.get(), getById.get());
        return getById;

    }

}
