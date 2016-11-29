/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck.timer;

import com.negod.timecheck.event.EventHandler;
import com.negod.timecheck.event.events.TimerEvent;
import java.util.TimerTask;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
public class AppTimerTask extends TimerTask {

    EventHandler time;

    public AppTimerTask(EventHandler time) {
        this.time = time;
    }

    @Override
    public void run() {
        time.sendEvent(TimerEvent.TIMEOUT, null);
    }

}
