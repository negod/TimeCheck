/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck.event;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
@Slf4j
public class EventRegistry {

    private final ConcurrentHashMap<Enum, Set<EventHandler>> LISTENERS = new ConcurrentHashMap<>();
    private static final EventRegistry INSTANCE = new EventRegistry();

    protected EventRegistry() {
    }

    public static final EventRegistry getInstance() {
        return INSTANCE;
    }

    public void register(Enum event) {
        log.debug("Registering event {}", event.name());
        LISTENERS.put(event, new HashSet<>());
    }

    public void register(Enum[] events) {
        log.debug("Registering events {}", events.toString());
        for (Enum event : events) {
            LISTENERS.put(event, new HashSet<>());
        }
    }

    public ConcurrentHashMap<Enum, Set<EventHandler>> getListeners() {
        return LISTENERS;
    }

}
