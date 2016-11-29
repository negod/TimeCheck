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
import javafx.scene.control.TableView;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
@Slf4j
public class TableViewFactory<E extends GenericEntity, T extends GenericDao> {

    private Optional<TableView<E>> tableView = Optional.empty();
    private Optional<T> dao = Optional.empty();

    public static TableViewFactory getNewInstance() {
        return new TableViewFactory();
    }

    public TableViewHandler build() throws UtilHandlerBuilderException {
        if (tableView.isPresent() && dao.isPresent()) {
            return new TableViewHandler(tableView.get(), dao.get());
        } else {
            log.error("Table view box and DAO must be present! [Is present ?] ComboBox {}, DAO {}", tableView.isPresent(), dao.isPresent());
            throw new UtilHandlerBuilderException("Combo box and DAO must be present!");
        }
    }

    public TableViewFactory setTableView(TableView<E> comboBox) {
        this.tableView = Optional.ofNullable(comboBox);
        return this;
    }

    public TableViewFactory setDao(T dao) {
        this.dao = Optional.ofNullable(dao);
        return this;
    }

}
