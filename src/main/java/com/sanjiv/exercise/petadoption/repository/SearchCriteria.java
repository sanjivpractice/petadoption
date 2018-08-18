package com.sanjiv.exercise.petadoption.repository;

import java.util.List;

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
public class SearchCriteria {
    List<SearchCriterion> searchCriteria;
}

