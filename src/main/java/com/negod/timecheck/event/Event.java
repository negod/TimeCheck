/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck.event;

import com.negod.timecheck.generic.GenericEntity;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
public class Event<T> {

    private final Enum type;
    private final T data;

    public Event(Enum type, T data) {
        this.type = type;
        this.data = data;
    }

    public Boolean isOfType(Enum type) {
        return this.type.equals(type);
    }

    public Enum getType() {
        return type;
    }

    public <T> TypeCheck getData() {
        return new TypeCheck(data);
    }

}
