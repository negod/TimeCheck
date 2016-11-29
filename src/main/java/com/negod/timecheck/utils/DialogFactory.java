/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck.utils;

import com.negod.timecheck.event.exceptions.DialogException;
import com.negod.timecheck.generic.GenericDao;
import com.negod.timecheck.generic.GenericEntity;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
@Slf4j
public class DialogFactory<E extends GenericEntity, T extends GenericDao> {

    private Optional<E> entity = Optional.empty();
    private Optional<T> dao = Optional.empty();

    public static DialogFactory getNewInstance() {
        return new DialogFactory();
    }

    public DialogFactory setEntity(E entity) {
        this.entity = Optional.ofNullable(entity);
        return this;
    }

    public DialogFactory setDao(T dao) {
        this.dao = Optional.ofNullable(dao);
        return this;
    }

    public DialogHandler build() throws DialogException {
        if (dao.isPresent()) {
            return new DialogHandler(entity, dao.get());
        } else {
            throw new DialogException("DAO must not be null!");
        }
    }

}
