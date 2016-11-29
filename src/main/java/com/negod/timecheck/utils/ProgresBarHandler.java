/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck.utils;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
public class ProgresBarHandler {

    private static final String[] VALUES = {"red-bar", "orange-bar", "yellow-bar", "green-bar"};

    public static void setProgress(final Double value, final ProgressBar bar) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                if (value < 0.5) {
                    setStyle(bar, VALUES[0]);
                } else if (value >= 0.5 && value < 0.75) {
                    setStyle(bar, VALUES[1]);
                } else if (value >= 0.75 && value < 0.95) {
                    setStyle(bar, VALUES[2]);
                } else if (value >= 0.95) {
                    setStyle(bar, VALUES[3]);
                }

                bar.setProgress(value);
            }
        });
    }

    private static void setStyle(ProgressBar bar, String style) {
        bar.getStyleClass().removeAll(VALUES);
        bar.getStyleClass().add(style);
    }

}
