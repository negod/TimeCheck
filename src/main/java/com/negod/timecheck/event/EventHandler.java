/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck.event;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
@Slf4j
public abstract class EventHandler {

    private List<Enum> eventsRegistered = new ArrayList<>();

    public void listenForEvent(Enum event) {
        try {
            if (EventQueue.getInstance().listenForEvent(event, this)) {
                log.debug("Event registered. Number of listeners for event {} = {}", event.name(), EventQueue.getInstance().getListenersCount(event));
                eventsRegistered.add(event);
            } else {
                log.debug("Event failed to register. Number of listeners for event {} = {}", event.name(), EventQueue.getInstance().getListenersCount(event));
            }
        } catch (Exception e) {
            log.error("Failed to listen to event ", e);
        }
    }

    public void sendEvent(Enum event, Object data) {
        EventQueue.getInstance().sendEvent(event, data);
    }

    public void unRegister() {
        EventQueue.getInstance().unRegister(eventsRegistered, this);
    }

    public abstract void onEvent(final Event event);

}
