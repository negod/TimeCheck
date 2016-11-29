package com.negod.timecheck.event;

import com.negod.timecheck.event.exceptions.EventNotFoundException;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
@Slf4j
public class EventQueue {

    private static final EventRegistry REGISTRY = EventRegistry.getInstance();
    private static final EventQueue INSTANCE = new EventQueue();

    protected EventQueue() {
    }

    /**
     * GEt the singleton instance of the EventQueue
     *
     * @return
     */
    public static final EventQueue getInstance() {
        return INSTANCE;
    }

    /**
     *
     * Add the eventhandler to the specified event
     *
     * @param event
     * @param handler
     * @return
     * @throws EventNotFoundException
     */
    public Boolean listenForEvent(Enum event, EventHandler handler) throws EventNotFoundException {
        try {
            log.debug("Registering listener for event {} with EventHandler", event.name());
            return REGISTRY.getListeners().get(event).add(handler);
        } catch (Exception e) {
            throw new EventNotFoundException("Event not found when adding a listener: " + event.name());
        }

    }

    /**
     *
     * Send an event and/or not an object
     *
     * @param event
     * @param data
     * @return
     */
    public Boolean sendEvent(Enum event, Object data) {
        try {
            Set<EventHandler> eventListeners = REGISTRY.getListeners().get(event);
            eventListeners.stream().forEach((eventListener) -> {
                eventListener.onEvent(new Event(event, data));
            });
        } catch (Exception e) {
            log.error("Error when sending Event on EventQueue", e);
            return false;
        }
        return true;
    }

    /**
     *
     * Get the count for the listteners for the event
     *
     * @param event
     * @return
     */
    public Integer getListenersCount(Enum event) {
        return REGISTRY.getListeners().get(event).size();
    }

    /**
     *
     * Unregister the eventHandler from the events specified
     *
     * @param eventsRegistered
     * @param handler
     */
    public void unRegister(List<Enum> eventsRegistered, EventHandler handler) {
        for (Enum event : eventsRegistered) {
            Set<EventHandler> eventHandler = REGISTRY.getListeners().get(event);
            eventHandler.remove(handler);
        }
    }

}
