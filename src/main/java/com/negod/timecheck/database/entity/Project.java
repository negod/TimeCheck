/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck.database.entity;

import com.negod.timecheck.generic.GenericEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
@Data()
@Entity
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Indexed
public class Project extends GenericEntity {

    public Project(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Project() {
    }

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    @Column(name = "name", insertable = true, unique = true)
    private String name;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    @Column(name = "age", insertable = true, unique = true)
    private Integer age;

}
