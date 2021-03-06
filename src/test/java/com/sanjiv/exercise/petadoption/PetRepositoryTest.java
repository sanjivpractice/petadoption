package com.sanjiv.exercise.petadoption;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sanjiv.exercise.petadoption.model.Pet;
import com.sanjiv.exercise.petadoption.repository.PetRepository;
import com.sanjiv.exercise.petadoption.repository.SearchCriteria;
import com.sanjiv.exercise.petadoption.repository.SearchCriterion;
import com.sanjiv.exercise.petadoption.repository.SearchLogicalOp;
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
    public void tesAddAndRetrievePet() {
        PetRepository petRepository = PetFactory.buildPetRepository(searchTypeProcessor);

        List<Pet> pets = petRepository.getPetsBySearchCriteria(new SearchCriteria(Lists.newArrayList(new SearchCriterion(
                SearchLogicalOp.AND, "97205"))));
        Assert.assertEquals(3, pets.size());
        logger.info("pets: {}",  pets);

        pets = petRepository.getPetsBySearchCriteria(new SearchCriteria(Lists.newArrayList(new SearchCriterion(SearchLogicalOp.AND, "male"))));
        Assert.assertEquals(2, pets.size());

        pets = petRepository.getPetsBySearchCriteria(new SearchCriteria(Lists.newArrayList(new SearchCriterion(SearchLogicalOp.AND, "dog"))));
        Assert.assertEquals(4, pets.size());
    }

    @Test
    public void testSearchAllDogsIn_97205_ZipCode_ShouldReturnTwoDogs() {
        PetRepository petRepository = PetFactory.buildPetRepository(searchTypeProcessor);

        List<Pet> pets = petRepository.getPetsBySearchCriteria(new SearchCriteria(Lists.newArrayList(
                new SearchCriterion(SearchLogicalOp.AND, "97205"),
                new SearchCriterion(SearchLogicalOp.AND, "dog"))));
        Assert.assertEquals(2, pets.size());
        logger.info("pets: {}",  pets);

    }

    @Test
    public void testSearchAllPetsThatAreIn_97205_ZipCode_Or_EitherRabbitShouldReturnFivePets() {
        PetRepository petRepository = PetFactory.buildPetRepository(searchTypeProcessor);

        List<Pet> pets = petRepository.getPetsBySearchCriteria(new SearchCriteria(Lists.newArrayList(
                new SearchCriterion(SearchLogicalOp.AND, "97205"),
                new SearchCriterion(SearchLogicalOp.OR, "rabbit"))));
        Assert.assertEquals(5, pets.size());
        logger.info("pets: {}",  pets);

    }

    @Test
    public void testSearchAllPetsThatAreRabbit_And_In_97205_ZipCode_ShouldReturn_NO_Pets() {
        PetRepository petRepository = PetFactory.buildPetRepository(searchTypeProcessor);

        List<Pet> pets = petRepository.getPetsBySearchCriteria(new SearchCriteria(Lists.newArrayList(
                new SearchCriterion(SearchLogicalOp.OR, "rabbit"),
                new SearchCriterion(SearchLogicalOp.AND, "97205"))));
        Assert.assertEquals(0, pets.size());
        logger.info("pets: {}",  pets);

    }


    @Test
    public void testNonExistentPetShouldResultInNoPetBeingReturned() {
        PetRepository petRepository = PetFactory.buildPetRepository(searchTypeProcessor);

        List<Pet> pets = petRepository.getPetsBySearchCriteria(new SearchCriteria(Lists.newArrayList(
                new SearchCriterion(SearchLogicalOp.AND, "mouse"))));
        Assert.assertEquals(0, pets.size());
        logger.info("pets: {}",  pets);

    }

    @Test
    public void tesNonExistentZipCodeShouldResultInNoPetBeingReturned() {
        PetRepository petRepository = PetFactory.buildPetRepository(searchTypeProcessor);

        List<Pet> pets = petRepository.getPetsBySearchCriteria(new SearchCriteria(Lists.newArrayList(
                new SearchCriterion(SearchLogicalOp.AND, "dog"),
                new SearchCriterion(SearchLogicalOp.AND, "75034"))));
        Assert.assertEquals(0, pets.size());
        logger.info("pets: {}",  pets);

    }

    @Test
    public void testSearchAllPetsThatAreDog_Or_Cat_And_Spayed_ShouldReturn_Two_Pets() {
        PetRepository petRepository = PetFactory.buildPetRepository(searchTypeProcessor);

        List<Pet> pets = petRepository.getPetsBySearchCriteria(new SearchCriteria(Lists.newArrayList(
                new SearchCriterion(SearchLogicalOp.AND, "dog"),
                new SearchCriterion(SearchLogicalOp.OR, "cat"),
                new SearchCriterion(SearchLogicalOp.AND, "spayed"))));
        Assert.assertEquals(2, pets.size());
        logger.info("pets: {}",  pets);

    }
}
