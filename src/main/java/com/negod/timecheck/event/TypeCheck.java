/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck.event;

import com.negod.timecheck.event.exceptions.TypeCastException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
@Slf4j
public class TypeCheck {

    Object data;

    public TypeCheck(Object data) {
        this.data = data;
    }

    public <T> Optional<T> getAsObject() throws TypeCastException {
        try {
            return Optional.ofNullable((T) data);
        } catch (ClassCastException ex) {
            log.error("Error when casting to type " + ex.getMessage());
            return Optional.empty();
        }
    }

    public Optional<Long> getAsLong() throws TypeCastException {
        if (isLong(data)) {
            return Optional.ofNullable((Long) data);
        } else if (isInteger(data)) {
            return Optional.ofNullable(getAsInteger().get().longValue());
        } else if (isDouble(data)) {
            return Optional.ofNullable(getAsDouble().get().longValue());
        } else {
            log.error("Object is not a Long");
            return Optional.empty();
        }
    }

    public Optional<String> getAsString() throws TypeCastException {
        if (isString(data)) {
            return Optional.ofNullable((String) data);
        } else if (!isString(data)) {
            return Optional.ofNullable(String.valueOf(data));
        } else {
            log.error("Object is not a String");
            return Optional.empty();
        }
    }

    public Optional<Integer> getAsInteger() throws TypeCastException {
        if (isInteger(data)) {
            return Optional.ofNullable((Integer) data);
        } else if (isString(data)) {
            return Optional.ofNullable(Integer.parseInt((String) data));
        } else if (isDouble(data)) {
            Double value = (Double) data;
            return Optional.ofNullable(value.intValue());
        } else if (isLong(data)) {
            Long value = (Long) data;
            return Optional.ofNullable(value.intValue());
        } else {
            log.error("Object cannot be parsed to Integer");
            return Optional.empty();
        }
    }

    public Optional<Double> getAsDouble() throws TypeCastException {
        if (isDouble(data)) {
            return Optional.ofNullable((Double) data);
        } else if (isLong(data)) {
            return Optional.ofNullable(getAsLong().get().doubleValue());
        } else if (isInteger(data)) {
            return Optional.ofNullable(getAsInteger().get().doubleValue());
        } else {
            log.error("Object is not a Double");
            return Optional.empty();
        }
    }

    private static Boolean isString(Object value) {
        return value instanceof String;
    }

    private static Boolean isInteger(Object value) {
        return value instanceof Integer;
    }

    private static Boolean isDouble(Object value) {
        return value instanceof Double;
    }

    private static Boolean isLong(Object value) {
        return value instanceof Long;
    }

}
