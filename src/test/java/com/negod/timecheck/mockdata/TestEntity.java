/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck.mockdata;

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
public class TestEntity extends GenericEntity {

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    @Column(name = "stringValue", insertable = true, unique = true)
    private String stringValue;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    @Column(name = "integerValue", insertable = true, unique = true)
    private Integer integerValue;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    @Column(name = "doubleValue", insertable = true, unique = true)
    private Double doubleValue;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    @Column(name = "longValue", insertable = true, unique = true)
    private Long longValue;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    @Column(name = "booleanValue", insertable = true, unique = true)
    private Boolean booleanValue;

}
