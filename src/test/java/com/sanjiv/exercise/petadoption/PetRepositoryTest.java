package com.sanjiv.exercise.petadoption;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sanjiv.exercise.petadoption.repository.PetRepository;

/**
 * @author Sanjiv on 8/17/18.
 */
public class PetRepositoryTest {

    private static Logger logger = LoggerFactory.getLogger(PetRepositoryTest.class);


    @Test
    public void tesAddPets() {
        PetRepository petRepository = PetFactory.buildPetRepository();

        assertEquals(8, petRepository.getIndexToPetMap().size());

    }
}
