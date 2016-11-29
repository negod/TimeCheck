/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck.timer;

import com.negod.timecheck.event.Event;
import com.negod.timecheck.event.EventHandler;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
public class CurrentTime extends EventHandler {

    private Integer INTERVAL = 1000;
    private Boolean started = Boolean.FALSE;
    private Timer currentTimer;

    public CurrentTime() {
    }

    private Timer startTimer(Integer interval) {
        Timer timer = new Timer();
        TimerTask task = new AppTimerTask(this);
        timer.schedule(task, 0, interval);
        return timer;
    }

    public void start(Integer interval) {
        if (!started) {
            INTERVAL = interval;
            System.out.println("Starting Timer: " + INTERVAL);
            currentTimer = startTimer(INTERVAL);
            started = Boolean.TRUE;
        }
    }

    public void start() {
        if (!started) {
            System.out.println("Starting Timer: " + INTERVAL);
            currentTimer = startTimer(INTERVAL);
            started = Boolean.TRUE;
        }
    }

    public void stop() {
        if (started) {
            System.out.println("Stopping Timer: " + INTERVAL);
            currentTimer.cancel();
            currentTimer.purge();
            started = Boolean.FALSE;
        }
    }

    @Override
    public void onEvent(Event event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
