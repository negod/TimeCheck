/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck.generic.search;

import lombok.Data;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
@Data
public class OrderBy {

    public enum Order {
        ASC, DESC
    }

    public String orderField;

}
