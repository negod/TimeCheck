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

//    /**
//     * Test of update method, of class GenericDao.
//     */
//    @Test
//    public void testUpdate() throws Exception {
//        System.out.println("update");
//        GenericEntity entity = null;
//        GenericDao instance = null;
//        Optional expResult = null;
//        Optional result = instance.update(entity);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of delete method, of class GenericDao.
//     */
//    @Test
//    public void testDelete_String() {
//        System.out.println("delete");
//        String externalId = "";
//        GenericDao instance = null;
//        Boolean expResult = null;
//        Boolean result = instance.delete(externalId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of delete method, of class GenericDao.
//     */
//    @Test
//    public void testDelete_GenericType() throws Exception {
//        System.out.println("delete");
//        GenericEntity entity = null;
//        GenericDao instance = null;
//        Boolean expResult = null;
//        Boolean result = instance.delete(entity);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getById method, of class GenericDao.
//     */
//
//    /**
//     * Test of getAll method, of class GenericDao.
//     */
//    @Test
//    public void testGetAll_GenericFilter() throws Exception {
//        System.out.println("getAll");
//        GenericFilter filter = null;
//        GenericDao instance = null;
//        Optional<List<T>> expResult = null;
//        Optional<List<T>> result = instance.getAll(filter);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAll method, of class GenericDao.
//     */
//    @Test
//    public void testGetAll_Pagination() throws Exception {
//        System.out.println("getAll");
//        Pagination pagination = null;
//        GenericDao instance = null;
//        Optional<List<T>> expResult = null;
//        Optional<List<T>> result = instance.getAll(pagination);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAll method, of class GenericDao.
//     */
//    @Test
//    public void testGetAll_0args() throws Exception {
//        System.out.println("getAll");
//        GenericDao instance = null;
//        Optional<List<T>> expResult = null;
//        Optional<List<T>> result = instance.getAll();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of get method, of class GenericDao.
//     */
//    @Test
//    public void testGet() throws Exception {
//        System.out.println("get");
//        GenericDao instance = null;
//        Optional expResult = null;
//        Optional result = instance.get(null);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of executeTypedQueryList method, of class GenericDao.
//     */
//    @Test
//    public void testExecuteTypedQueryList_TypedQuery() throws Exception {
//        System.out.println("executeTypedQueryList");
//        GenericDao instance = null;
//        Optional<List<T>> expResult = null;
//        Optional<List<T>> result = instance.executeTypedQueryList(null);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of executeTypedQueryList method, of class GenericDao.
//     */
//    @Test
//    public void testExecuteTypedQueryList_TypedQuery_Pagination() throws Exception {
//        System.out.println("executeTypedQueryList");
//        GenericDao instance = null;
//        Optional<List<T>> expResult = null;
//        Optional<List<T>> result = instance.executeTypedQueryList(null);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of executeTypedQuery method, of class GenericDao.
//     */
//    @Test
//    public void testExecuteTypedQuery() throws Exception {
//        System.out.println("executeTypedQuery");
//        GenericDao instance = null;
//        Optional expResult = null;
//        Optional result = instance.executeTypedQuery(null);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of indexEntity method, of class GenericDao.
//     */
//    @Test
//    public void testIndexEntity() {
//        System.out.println("indexEntity");
//        GenericDao instance = null;
//        Boolean expResult = null;
//        Boolean result = instance.indexEntity();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of indexDb method, of class GenericDao.
//     */
//    @Test
//    public void testIndexDb() {
//        System.out.println("indexDb");
//        GenericDao instance = null;
//        Boolean expResult = null;
//        Boolean result = instance.indexDb();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getEm method, of class GenericDao.
//     */
//    @Test
//    public void testGetEm() {
//        System.out.println("getEm");
//        GenericDao instance = null;
//        EntityManager expResult = null;
//        EntityManager result = instance.getEm();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getEntityClass method, of class GenericDao.
//     */
//    @Test
//    public void testGetEntityClass() {
//        System.out.println("getEntityClass");
//        GenericDao instance = null;
//        Class expResult = null;
//        Class result = instance.getEntityClass();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getClassName method, of class GenericDao.
//     */
//    @Test
//    public void testGetClassName() {
//        System.out.println("getClassName");
//        GenericDao instance = null;
//        String expResult = "";
//        String result = instance.getClassName();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getSearchFields method, of class GenericDao.
//     */
//    @Test
//    public void testGetSearchFields() {
//        System.out.println("getSearchFields");
//        GenericDao instance = null;
//        Set<String> expResult = null;
//        Set<String> result = instance.getSearchFields();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setEm method, of class GenericDao.
//     */
//    @Test
//    public void testSetEm() {
//        System.out.println("setEm");
//        EntityManager em = null;
//        GenericDao instance = null;
//        instance.setEm(em);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of equals method, of class GenericDao.
//     */
//    @Test
//    public void testEquals() {
//        System.out.println("equals");
//        Object o = null;
//        GenericDao instance = null;
//        boolean expResult = false;
//        boolean result = instance.equals(o);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of canEqual method, of class GenericDao.
//     */
//    @Test
//    public void testCanEqual() {
//        System.out.println("canEqual");
//        Object other = null;
//        GenericDao instance = null;
//        boolean expResult = false;
//        boolean result = instance.canEqual(other);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of hashCode method, of class GenericDao.
//     */
//    @Test
//    public void testHashCode() {
//        System.out.println("hashCode");
//        GenericDao instance = null;
//        int expResult = 0;
//        int result = instance.hashCode();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toString method, of class GenericDao.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        GenericDao instance = null;
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
