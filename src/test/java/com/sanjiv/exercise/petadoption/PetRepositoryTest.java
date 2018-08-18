package com.sanjiv.exercise.petadoption;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sanjiv.exercise.petadoption.repository.PetRepository;
import com.sanjiv.exercise.petadoption.repository.SearchTypeProcessor;

/**
 * @author Sanjiv on 8/17/18.
 */
public class PetRepositoryTest {

    private static Logger logger = LoggerFactory.getLogger(PetRepositoryTest.class);
    private SearchTypeProcessor searchTypeProcessor;

    @Before
    public void setup() {

        searchTypeProcessor = new SearchTypeProcessor();
    }

    @Test
    public void tesAddPets() {
        PetRepository petRepository = PetFactory.buildPetRepository(searchTypeProcessor);

        assertEquals(8, petRepository.getIndexToPetMap().size());

    }
}
