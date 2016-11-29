/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck.utils;

import com.negod.timecheck.generic.GenericDao;
import com.negod.timecheck.generic.GenericEntity;
import com.negod.timecheck.utils.exceptions.UtilHandlerBuilderException;
import java.util.Optional;
import javafx.scene.control.ComboBox;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
@Slf4j
public class ComboBoxFactory<E extends GenericEntity, T extends GenericDao> {

    private Optional<ComboBox<E>> comboBox = Optional.empty();
    private Optional<T> dao = Optional.empty();
    private Optional<String> fieldName = Optional.empty();

    public static ComboBoxFactory getNewInstance() {
        return new ComboBoxFactory();
    }

    public ComboBoxHandler build() throws UtilHandlerBuilderException {
        if (comboBox.isPresent() && dao.isPresent() && fieldName.isPresent()) {
            return new ComboBoxHandler(comboBox.get(), dao.get(), fieldName.get());
        } else {
            log.error("Combo box and DAO must be present! [Is present ?] ComboBox {}, DAO {}", comboBox.isPresent(), dao.isPresent());
            throw new UtilHandlerBuilderException("Combo box and DAO must be present!");
        }
    }

    /**
     * @param comboBox The actual combobox that will be manipulated
     * @return
     */
    public ComboBoxFactory setComboBox(ComboBox<E> comboBox) {
        this.comboBox = Optional.ofNullable(comboBox);
        return this;
    }

    /**
     * @param dao The dao that will be used to retrieve the data for the
     * combobox
     * @return
     */
    public ComboBoxFactory setDao(T dao) {
        this.dao = Optional.ofNullable(dao);
        return this;
    }

    /**
     * @param fieldName The fieldname in the entity that will be used to
     * populate the combobox
     * @return
     */
    public ComboBoxFactory setFieldName(String fieldName) {
        this.fieldName = Optional.ofNullable(fieldName);
        return this;
    }

}
