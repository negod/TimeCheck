/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck;

import javafx.application.Platform;
import javafx.scene.control.TextField;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
public class TextFieldSetter {

    public static void setTextFieldValue(final String id, final String promptText, final TextField textField) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                textField.setId(id);
                textField.setPromptText(promptText);
            }
        });
    }

}
