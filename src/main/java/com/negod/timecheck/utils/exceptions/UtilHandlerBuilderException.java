/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck.utils.exceptions;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
public class UtilHandlerBuilderException extends Exception {

    /**
     * Creates a new instance of <code>UtilHandlerBuilderException</code>
     * without detail message.
     */
    public UtilHandlerBuilderException() {
    }

    /**
     * Constructs an instance of <code>UtilHandlerBuilderException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public UtilHandlerBuilderException(String msg) {
        super(msg);
    }
}
