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
public class DialogException extends Exception {

    /**
     * Creates a new instance of <code>DialogException</code> without detail
     * message.
     */
    public DialogException() {
    }

    /**
     * Constructs an instance of <code>DialogException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public DialogException(String msg) {
        super(msg);
    }
}
