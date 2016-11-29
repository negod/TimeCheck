/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck.utils;

import com.negod.timecheck.generic.GenericEntity;
import java.lang.reflect.Field;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.TypeUtils;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
@Slf4j
public class FieldHelper {

    private static Class[] classes = {String.class};

    private static <E, T> TableColumn<E, T> getColumn(String columnName) {
        return new TableColumn<>(StringUtils.capitalize(columnName));
    }

    private static <E extends GenericEntity> Optional<TableColumn> getColumnSimpleStringProperty(Field columnField) {
        TableColumn column = FieldHelper.<E, String>getColumn(columnField.getName());
        column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<E, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<E, String> param) {
                if (String.class.isAssignableFrom(columnField.getType())) {
                    try {
                        return new SimpleStringProperty((String) FieldUtils.readDeclaredField(param.getValue(), columnField.getName(), true));
                    } catch (IllegalArgumentException | IllegalAccessException ex) {
                        log.error("Error when creating SimpleStringProperty MESSAGE: {} ERROR; {}", ex.getMessage(), ex);
                    }
                }
                return new SimpleStringProperty();
            }
        });
        return Optional.ofNullable(column);
    }

    private static <E extends GenericEntity> Optional<TableColumn> getColumnSimpleIntegerProperty(Field columnField) {
        TableColumn column = FieldHelper.<E, Integer>getColumn(columnField.getName());
        column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<E, Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<E, Number> param) {
                if (Integer.class.isAssignableFrom(columnField.getType())) {
                    try {
                        return new SimpleIntegerProperty((Integer) FieldUtils.readDeclaredField(param.getValue(), columnField.getName(), true));
                    } catch (IllegalArgumentException | IllegalAccessException ex) {
                        log.error("Error when creating SimpleIntegeroperty {}", ex.getMessage());
                    }
                }
                return new SimpleIntegerProperty();
            }
        });
        return Optional.ofNullable(column);
    }

    private static <E extends GenericEntity> Optional<TableColumn> getColumnSimpleLongProperty(Field columnField) {
        TableColumn column = FieldHelper.<E, Long>getColumn(columnField.getName());
        column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<E, Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<E, Number> param) {
                if (Integer.class.isAssignableFrom(columnField.getType())) {
                    try {
                        return new SimpleLongProperty((Long) FieldUtils.readDeclaredField(param.getValue(), columnField.getName(), true));
                    } catch (IllegalArgumentException | IllegalAccessException ex) {
                        log.error("Error when creating SimpleLongProperty {}", ex.getMessage());
                    }
                }
                return new SimpleIntegerProperty();
            }
        });
        return Optional.ofNullable(column);
    }

    private static <E extends GenericEntity> Optional<TableColumn> getColumnSimpleDoubleProperty(Field columnField) {
        TableColumn column = FieldHelper.<E, Double>getColumn(columnField.getName());
        column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<E, Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<E, Number> param) {
                if (Integer.class.isAssignableFrom(columnField.getType())) {
                    try {
                        return new SimpleDoubleProperty((Double) FieldUtils.readDeclaredField(param.getValue(), columnField.getName(), true));
                    } catch (IllegalArgumentException | IllegalAccessException ex) {
                        log.error("Error when creating SimpleDoubleroperty {}", ex.getMessage());
                    }
                }
                return new SimpleDoubleProperty();
            }
        });
        return Optional.ofNullable(column);
    }

    private static <E extends GenericEntity> Optional<TableColumn> getColumnSimpleBooleanProperty(Field columnField) {
        TableColumn column = FieldHelper.<E, Boolean>getColumn(columnField.getName());
        column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<E, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<E, Boolean> param) {
                if (Integer.class.isAssignableFrom(columnField.getType())) {
                    try {
                        return new SimpleBooleanProperty((Boolean) FieldUtils.readDeclaredField(param.getValue(), columnField.getName(), true));
                    } catch (IllegalArgumentException | IllegalAccessException ex) {
                        log.error("Error when creating SimpleIntegerProperty {}", ex.getMessage());
                    }
                }
                return new SimpleBooleanProperty();
            }
        });
        return Optional.ofNullable(column);
    }

    public static <E extends GenericEntity> Optional<TableColumn> getTableColumn(Field field) {
        if (TypeUtils.isAssignable(field.getType(), String.class)) {
            return getColumnSimpleStringProperty(field);
        } else if (TypeUtils.isAssignable(field.getType(), Integer.class)) {
            return getColumnSimpleIntegerProperty(field);
        } else if (TypeUtils.isAssignable(field.getType(), Long.class)) {
            return getColumnSimpleLongProperty(field);
        } else if (TypeUtils.isAssignable(field.getType(), Double.class)) {
            return getColumnSimpleDoubleProperty(field);
        } else if (TypeUtils.isAssignable(field.getType(), Boolean.class)) {
            return getColumnSimpleBooleanProperty(field);
        }
        log.debug("[getType] Type provided is not supported");
        return Optional.empty();
    }

    /**
     *
     * @param <T> The instance of a class with @Field annotations
     * @param param The instantialted class
     * @param fieldName The fieldname to look for
     * @return
     */
    public static <T> Optional<Field> getField(Class<T> param, String fieldName) {
        if (Optional.ofNullable(param).isPresent() && Optional.ofNullable(fieldName).isPresent()) {
            Field[] declaredFields = param.getDeclaredFields();
            for (Field field : declaredFields) {
                if (field.getName().equals(fieldName)) {
                    try {
                        field.setAccessible(true);
                        return Optional.ofNullable(field);
                    } catch (IllegalArgumentException ex) {
                        log.error("Failed to get field with name {}", fieldName);
                    }
                }
            }
        } else {
            log.debug("[getField] FieldName or param is null fieldName: {} param: {}", fieldName, param);
        }
        log.trace("[getField] Field does not exist, fieldName: {} param: {}", fieldName, param);
        return Optional.empty();
    }

    /**
     *
     * @param <T> The return type
     * @param <E> The entity type
     * @param entity The entity to get the value from
     * @param fieldName The fieldname to get the value from
     * @return
     */
    public static <T, E extends GenericEntity> Optional<T> getFieldValue(E entity, String fieldName) {
        if (Optional.ofNullable(entity).isPresent() && Optional.ofNullable(fieldName).isPresent()) {

            Optional<Field> field = FieldHelper.getField(entity.getClass(), fieldName);
            if (field.isPresent()) {
                try {
                    field.get().setAccessible(true);
                    return Optional.ofNullable((T) field.get().get(entity));
                } catch (IllegalAccessException ex) {
                    log.error("Failed to get fieldvalue for field {}", fieldName);
                }
            }

        } else {
            log.trace("[getFieldValue] entity or fieldName is NULL: entity: {} fieldname: {}", entity, fieldName);
        }
        return Optional.empty();
    }

}
