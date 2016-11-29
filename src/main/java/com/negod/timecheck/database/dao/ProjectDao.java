/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck.database.dao;

import com.negod.timecheck.database.exceptions.DaoException;
import com.negod.timecheck.database.entity.Project;
import com.negod.timecheck.generic.GenericDao;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
public class ProjectDao extends GenericDao<Project> {

    public ProjectDao() throws DaoException {
        super(Project.class);
    }

}
