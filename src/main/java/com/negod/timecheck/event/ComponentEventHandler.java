/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck.event;

import com.negod.timecheck.database.exceptions.DaoException;
import com.negod.timecheck.event.exceptions.EventError;
import com.negod.timecheck.event.exceptions.TypeCastException;
import com.negod.timecheck.event.events.GenericObjectEvents;
import com.negod.timecheck.generic.GenericDao;
import com.negod.timecheck.generic.GenericEntity;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 * @param <E>
 * @param <T>
 */
@Slf4j
public abstract class ComponentEventHandler<E extends GenericEntity, T extends GenericDao> extends EventHandler {
    
    private Optional<T> dao = Optional.empty();
    private final ObservableList<E> data = FXCollections.observableArrayList();
    
    public ComponentEventHandler(T dao) {
        this.dao = Optional.ofNullable(dao);
        super.listenForEvent(GenericObjectEvents.CREATE);
        super.listenForEvent(GenericObjectEvents.DELETE);
        super.listenForEvent(GenericObjectEvents.UPDATE);
        data.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change change) {
                onDataChange(change);
            }
        });
        
    }
    
    public ObservableList<E> GetAll() throws DaoException {
        Optional<List<E>> all = dao.get().getAll();
        if (all.isPresent()) {
            all.get().stream().forEach((e) -> {
                data.add(e);
            });
        }
        return data;
    }
    
    public Optional<T> getDao() {
        return dao;
    }
    
    public void add(E entity) throws DaoException {
        log.debug("Adding data to List {}", entity.toString());
        log.debug("Current number of values in list {}", data.size());
        data.add(entity);
        log.debug("Current number of values in list after adding value {}", data.size());
        for (E e : data) {
            log.debug("Objects in list {} ", e.toString());
        }
    }
    
    public void delete(E entity) {
        data.remove(entity);
    }
    
    public void update(E entity) throws DaoException {
        data.remove(entity);
        data.add(entity);
    }
    
    public abstract void onDataChange(Change change);
    
    @Override
    public void onEvent(Event event) {
        try {
            if (event.isOfType(GenericObjectEvents.CREATE)) {
                log.debug("CREATE Event invoked in ComponentEventHandler for EntityType {}", dao.get().getClassName());
                Optional<E> asObject = event.getData().getAsObject();
                if (asObject.isPresent()) {
                    this.add(asObject.get());
                } else {
                    throw new EventError("Error when recieving event [ CREATE ] ");
                }
            } else if (event.isOfType(GenericObjectEvents.DELETE)) {
                log.debug("DELETE Event invoked in ComponentEventHandler for EntityType {}", dao.get().getClassName());
                Optional<E> asObject = event.getData().getAsObject();
                if (asObject.isPresent()) {
                    this.delete(asObject.get());
                } else {
                    throw new EventError("Error when recieving event [ DELETE ] ");
                }
            } else if (event.isOfType(GenericObjectEvents.UPDATE)) {
                log.debug("UPDATE Event invoked in ComponentEventHandler for EntityType {}", dao.get().getClassName());
                Optional<E> asObject = event.getData().getAsObject();
                if (asObject.isPresent()) {
                    this.update(asObject.get());
                } else {
                    throw new EventError("Error when recieving event [ UPDATE ] ");
                }
            }
        } catch (TypeCastException | EventError | DaoException e) {
            log.error("Error when handling event in ComponentEventHandler {} ", e.getMessage());
        }
    }
    
}
