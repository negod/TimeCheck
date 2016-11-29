/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.timecheck.generic.search;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
@Data
public class GenericFilter {

    private Set<String> searchFields = new HashSet<>();
    private String globalSearchWord;
    //private OrderBy order;
    private Pagination pagination;

}
