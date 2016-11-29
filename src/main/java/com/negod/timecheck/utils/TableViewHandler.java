/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck.utils;

import com.negod.timecheck.database.exceptions.DaoException;
import com.negod.timecheck.event.ComponentEventHandler;
import com.negod.timecheck.generic.GenericDao;
import com.negod.timecheck.generic.GenericEntity;
import java.lang.reflect.Field;
import java.util.Optional;
import java.util.Set;
import javafx.collections.ListChangeListener;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 * @param <E>
 * @param <T>
 */
@Slf4j
public class TableViewHandler<E extends GenericEntity, T extends GenericDao> extends ComponentEventHandler<E, T> {

    private final TableView<E> tableView;

    protected TableViewHandler(TableView tableView, T dao) {
        super(dao);
        this.tableView = tableView;
        populateTable();
    }

    private void populateTable() {
        try {
            if (super.getDao().isPresent()) {
                Set<String> searchFields = super.getDao().get().getSearchFields();
                for (String searchField : searchFields) {
                    Optional<Field> field = FieldHelper.getField(super.getDao().get().getEntityClass(), searchField);
                    if (field.isPresent()) {
                        Optional<TableColumn> column = FieldHelper.getTableColumn(field.get());
                        if (column.isPresent()) {
                            tableView.getColumns().add(column.get());
                        } else {
                            log.error("Could not create Column for searchField: {} and Object: {}", searchField, super.getDao().get().getClass().getName());
                        }
                    } else {
                        log.error("Field not found for searchField: {} in Object: {}", searchField, super.getDao().get().getEntityClass().getName());
                    }
                }
                tableView.setItems(super.GetAll());
            }
        } catch (DaoException ex) {
            log.error("Error when populating table for type {}", super.getDao().get().getClassName());
        }
    }

    @Override
    public void onDataChange(ListChangeListener.Change change) {
        //System.out.println("Data changed in TableViewHandler");
    }

}
