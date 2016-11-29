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
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 * @param <E>
 * @param <T>
 */
@Slf4j
public class ComboBoxHandler<E extends GenericEntity, T extends GenericDao> extends ComponentEventHandler<E, T> {

    private final ComboBox<E> comboBox;
    private final String fieldName;

    protected ComboBoxHandler(ComboBox comboBox, T dao, String fieldName) {
        super(dao);
        this.comboBox = comboBox;
        this.fieldName = fieldName;
        populateData(fieldName);
    }

    private void populateData(String field) {

        if (super.getDao().isPresent() && Optional.ofNullable(field).isPresent()) {
            try {
                comboBox.setItems(super.GetAll());

                comboBox.setCellFactory((ListView<E> p) -> {
                    final ListCell<E> cell = new ListCell<E>() {
                        @Override
                        protected void updateItem(E t, boolean bln) {
                            super.updateItem(t, bln);
                            Optional<String> fieldValue = FieldHelper.getFieldValue(t, field);
                            if (t != null && fieldValue.isPresent()) {
                                super.setText(fieldValue.get());
                            } else {
                                setText(null);
                            }

                        }

                    };
                    return cell;
                });

                comboBox.setButtonCell((ListCell) comboBox.getCellFactory().call(null));

            } catch (DaoException ex) {
                log.error("Error when populating table for type {}", super.getDao().get().getClassName());
            }

        } else {
            log.error("Fieldname not present when initializing ComboBoxModel {}", super.getDao().get().getClassName());
        }

    }

    @Override
    public void onDataChange(ListChangeListener.Change change) {
        System.out.println("DATA changed in ComboBox handler");
    }

}
