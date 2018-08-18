package com.sanjiv.exercise.petadoption.model;

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
public class Pet {
    private Integer id;
    private String name;
    private String type;
    private String gender;
    private String zipcode;

}
