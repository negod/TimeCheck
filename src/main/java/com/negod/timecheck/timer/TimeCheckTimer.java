/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck.timer;

import com.negod.timecheck.event.Event;
import com.negod.timecheck.event.EventHandler;
import com.negod.timecheck.event.events.TimerEvent;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
public class TimeCheckTimer extends EventHandler {

    private Long CURRENT_WORK_MILLIS = 0L;
    private Long CURRENT_BREAK_MILLIS = 0L;
    private Long INTERVAL = 1000L;

    private Boolean paused = Boolean.FALSE;
    private Boolean started = Boolean.FALSE;

    CurrentTime timer = new CurrentTime();

    public TimeCheckTimer() {
        super.listenForEvent(TimerEvent.TIMEOUT);
    }

    /**
     *
     * @param interval Interval in seconds
     */
    public Boolean startTimer(int interval) {
        this.INTERVAL = interval * 1000L;
        timer.start(INTERVAL.intValue());
        return started = Boolean.TRUE;
    }

    public Boolean startTimer() {
        timer.start(INTERVAL.intValue());
        return started = Boolean.TRUE;
    }

    public Boolean pauseTimer() {
        return paused = Boolean.TRUE;
    }

    public Boolean resumeTimer() {
        return paused = Boolean.FALSE;
    }

    public Boolean resetTimer() {
        timer.stop();
        CURRENT_WORK_MILLIS = 0L;
        return started = Boolean.FALSE;
    }

    public Boolean stopTimer() {
        timer.stop();
        return started = Boolean.FALSE;
    }

    public Boolean isPaused() {
        return paused;
    }

    public Boolean isStarted() {
        return started;
    }

    private void sendNewTime(Long addedTime) {
        super.sendEvent(TimerEvent.NEW_TIME, addedTime);
    }

    private void sendBreakTime(Long addedTime) {
        super.sendEvent(TimerEvent.BREAK_TIME, addedTime);
    }

    @Override
    public void onEvent(Event event) {
        if (event.isOfType(TimerEvent.TIMEOUT)) {
            if (!paused) {
                sendNewTime(CURRENT_WORK_MILLIS += INTERVAL);
            } else {
                sendBreakTime(CURRENT_BREAK_MILLIS += INTERVAL);
            }
        }
    }

}
