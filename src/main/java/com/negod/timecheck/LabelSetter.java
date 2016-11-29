/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck;

import com.negod.timecheck.event.Event;
import com.negod.timecheck.event.exceptions.TypeCastException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
public class LabelSetter {

    private final static SimpleDateFormat FORMATTED_DATE = new SimpleDateFormat("HH:mm:ss");
    private final static TimeZone UTC_TIMEZONE = TimeZone.getTimeZone("UTC");

    public static void setLabelValue(final Event event, final Labeled label) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    label.setText(event.getData().getAsString().get());
                } catch (TypeCastException ex) {
                    System.out.println("Cast exception" + ex.getMessage());
                }
            }
        });
    }

    public static void setLabelValue(final String data, final Labeled label) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                label.setText(data);
            }
        });
    }

    public static void setLabelValueAsTimeFromMillis(final Long time, final Labeled label) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String timeText = getTimeAsStringFromMillis(time);
                label.setText(timeText);
            }
        });
    }

    /**
     * Returns a time in the format HH:mm:ss
     *
     * @param addedTime The time to convert
     * @return
     */
    public static String getTimeAsStringFromMillis(Long addedTime) {
        FORMATTED_DATE.setTimeZone(UTC_TIMEZONE);
        return FORMATTED_DATE.format(new Date(addedTime));
    }

}
