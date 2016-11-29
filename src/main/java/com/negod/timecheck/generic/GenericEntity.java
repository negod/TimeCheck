/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck.generic;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.search.annotations.Field;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
@MappedSuperclass
@Data
public class GenericEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long internalId;

    @NotNull(message = "External id cannot be null and should be set to UUID")
    @Column(unique = true, updatable = false, insertable = true, name = "extId")
    @Pattern(regexp = "[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}")
    @Field()
    private String id;

    @NotNull(message = "Updated date cannot be null and all CRUD operations must have a date")
    @Column(name = "updatedTime")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updatedDate;

    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = new Date();
    }

    @PrePersist
    protected void onCreate() {
        this.updatedDate = new Date();
        this.id = UUID.randomUUID().toString();
    }

}
