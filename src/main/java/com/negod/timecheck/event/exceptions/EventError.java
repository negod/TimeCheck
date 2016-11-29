/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck.event.exceptions;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
public class EventError extends Exception {

    /**
     * Creates a new instance of <code>EventError</code> without detail message.
     */
    public EventError() {
    }

    /**
     * Constructs an instance of <code>EventError</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public EventError(String msg) {
        super(msg);
    }
}
