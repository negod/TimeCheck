/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck.utils;

import com.negod.timecheck.TextFieldSetter;
import com.negod.timecheck.event.ComponentEventHandler;
import com.negod.timecheck.event.events.GenericObjectEvents;
import com.negod.timecheck.generic.GenericDao;
import com.negod.timecheck.generic.GenericEntity;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.TypeUtils;

//dialog.setHeaderText("Look, a Custom Login Dialog");
/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
@Slf4j
public class DialogHandler<E extends GenericEntity, T extends GenericDao> extends ComponentEventHandler<E, T> {

    GenericObjectEvents OBJECT_EVENT;
    Optional<E> entity = Optional.empty();

    public DialogHandler(Optional<E> entity, T dao) {
        super(dao);
        this.entity = entity;
        OBJECT_EVENT = getObjectEventType();
    }

    private GenericObjectEvents getObjectEventType() {
        return entity.isPresent() ? GenericObjectEvents.UPDATE : GenericObjectEvents.CREATE;
    }

    private Map<String, TextField> getTextFields() {

        Set<String> entityFields = super.getDao().get().getEntityFields();
        Map<String, TextField> fields = new HashMap<>();

        for (String entityField : entityFields) {

            TextField field = new TextField();
            TextFieldSetter.setTextFieldValue(entityField, entityField, field);

//            field.textProperty().addListener((observable, oldValue, newValue) -> {
//                System.out.println("Tjoho!! New value in " + entityField);
//            });
            fields.put(entityField, field);
        }

        return fields;
    }

    public Optional<E> showModalAndWait() {

        Dialog<Pair<String, String>> dialog = new Dialog<>();
        Map<String, TextField> textFields = getTextFields();

        dialog.setTitle(getObjectEventType() + StringUtils.capitalize(super.getDao().get().getClassName()));
        dialog.setHeaderText(getObjectEventType() + StringUtils.capitalize(super.getDao().get().getClassName()));

        // Set the icon (must be included in the project).
        //dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));
        // Set the button types.
        ButtonType loginButtonType = new ButtonType(StringUtils.capitalize(OBJECT_EVENT.name()), ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        Integer order = 1;
        for (Entry<String, TextField> object : textFields.entrySet()) {
            grid.add(new Label(StringUtils.capitalize(object.getKey()) + ":"), 0, order);
            grid.add(object.getValue(), 1, order);
            order++;
        }

        // Enable/Disable login button depending on whether a username was entered.
//        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
//        loginButton.setDisable(true);
        // Do some validation (using the Java 8 lambda syntax).
//        username.textProperty().addListener((observable, oldValue, newValue) -> {
//            loginButton.setDisable(newValue.trim().isEmpty());
//        });
        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
//        Platform.runLater(() -> username.requestFocus());
        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {

            if (dialogButton == loginButtonType) {
                StringBuilder valueBuilder = new StringBuilder();
                StringBuilder keyBuilder = new StringBuilder();

                for (Entry<String, TextField> object : textFields.entrySet()) {
                    keyBuilder.append(object.getKey()).append(";");
                    valueBuilder.append(object.getValue().getText()).append(";");
                }

                return new Pair<>(keyBuilder.toString(), valueBuilder.toString());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        if (result.isPresent()) {

            Map<String, String> createMapFromResponse = createMapFromResponse(result);

            Optional<E> convertToEntity = convertToEntity(createMapFromResponse);

            if (convertToEntity.isPresent()) {
                super.sendEvent(GenericObjectEvents.CREATE, convertToEntity.get());
            }

            super.unRegister();
            return convertToEntity;

        }

        super.unRegister();
        return Optional.empty();

    }

    public Map<String, String> createMapFromResponse(Optional<Pair<String, String>> result) {
        String[] keys = result.get().getKey().split(";");
        String[] values = result.get().getValue().split(";");

        Map<String, String> returnedValues = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            returnedValues.put(keys[i], values[i]);
        }
        return returnedValues;
    }

    public Optional<E> convertToEntity(Map<String, String> returnedValues) {

        try {

            E entityClass = (E) super.getDao().get().getEntityClass().newInstance();

            Set<String> entityFields = super.getDao().get().getEntityFields();
            for (String entityField : entityFields) {

                Field field = entityClass.getClass().getDeclaredField(entityField);
                field.setAccessible(true);

                if (TypeUtils.isAssignable(field.getType(), Integer.class)) {
                    field.set(entityClass, Integer.parseInt(returnedValues.get(entityField).trim()));
                }

                if (TypeUtils.isAssignable(field.getType(), String.class)) {
                    field.set(entityClass, returnedValues.get(entityField));
                }

                if (TypeUtils.isAssignable(field.getType(), Long.class)) {
                    field.set(entityClass, Long.parseLong(returnedValues.get(entityField).trim()));
                }

                if (TypeUtils.isAssignable(field.getType(), Double.class)) {
                    field.set(entityClass, Double.parseDouble(returnedValues.get(entityField).trim()));
                }

                if (TypeUtils.isAssignable(field.getType(), Boolean.class)) {
                    field.set(entityClass, Boolean.valueOf(returnedValues.get(entityField).trim()));
                }

                field.setAccessible(false);

            }

            return Optional.ofNullable(entityClass);
        } catch (NoSuchFieldException ex) {
            log.error("Field does not exist in the entity that is being created", ex);
        } catch (SecurityException ex) {
            log.error("Security exception when trying to create Entity", ex);
        } catch (InstantiationException ex) {
            log.error("Error when instantiating Entity", ex);
        } catch (IllegalAccessException ex) {
            log.error("Error when accessing field in Entity", ex);
        }

        return Optional.empty();
    }

    @Override
    public void onDataChange(ListChangeListener.Change change) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
