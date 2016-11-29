/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck.utils;

import com.isp.lpt.JfxTestRunner;
import com.negod.timecheck.database.exceptions.DaoException;
import com.negod.timecheck.mockdata.TestEntity;
import com.negod.timecheck.mockdata.TestEntityDao;
import com.negod.timecheck.mockdata.TestEntityMock;
import java.util.Map;
import java.util.Optional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
@RunWith(JfxTestRunner.class)
public class DialogHandlerTest {

    public DialogHandlerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws InterruptedException {
//        Thread t = new Thread("JavaFX Init Thread") {
//            public void run() {
//                Application.launch(AsNonApp.class, new String[0]);
//            }
//        };
//        t.setDaemon(true);
//        t.start();
    }

//    public static class AsNonApp extends Application {
//
//        @Override
//        public void start(Stage primaryStage) throws Exception {
//            // noop
//        }
//    }
    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConvertToEntity() throws DaoException {
        System.out.println("convertToEntity");

        TestEntityDao dao = new TestEntityDao();

        DialogHandler<TestEntity, TestEntityDao> instance = new DialogHandler(Optional.empty(), dao);

        TestEntity entity = TestEntityMock.getEntity();
        Map<String, String> returnedValues = TestEntityMock.getEntityKeyValue();

        Optional<TestEntity> newEntity = instance.convertToEntity(returnedValues);

        assertEquals(true, newEntity.isPresent());

        assertEquals(entity.getStringValue(), newEntity.get().getStringValue());
        assertEquals(entity.getBooleanValue(), newEntity.get().getBooleanValue());
        assertEquals(entity.getDoubleValue(), newEntity.get().getDoubleValue());
        assertEquals(entity.getIntegerValue(), newEntity.get().getIntegerValue());
        assertEquals(entity.getLongValue(), newEntity.get().getLongValue());

    }

}
