package com.sanjiv.exercise.petadoption.repository;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Sanjiv on 8/17/18.
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class SearchCriterion {

    private SearchLogicalOp op = SearchLogicalOp.AND;  // default
    private String searchValue;

}
