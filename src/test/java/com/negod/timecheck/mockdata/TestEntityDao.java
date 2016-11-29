/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck.mockdata;

import com.negod.timecheck.database.exceptions.DaoException;
import com.negod.timecheck.generic.GenericDao;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
public class TestEntityDao extends GenericDao<TestEntity> {

    public TestEntityDao() throws DaoException {
        super(TestEntity.class);
    }

}
