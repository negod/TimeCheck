/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck.mockdata;

import com.negod.timecheck.database.entity.Project;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
public class ProjectEntityMock {

    public static final String NAME = "PROJECT_NAME";

    public static Project getProject() {
        Project project = new Project();
        project.setName(NAME);
        return project;
    }

}
