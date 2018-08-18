package com.sanjiv.exercise.petadoption.commandline;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.sanjiv.exercise.petadoption.model.Pet;
import com.sanjiv.exercise.petadoption.repository.PetRepository;
import com.sanjiv.exercise.petadoption.repository.SearchCriteria;
import com.sanjiv.exercise.petadoption.repository.SearchCriteriaBuilder;

/**
 * @author Sanjiv on 8/17/18.
 */
@Component
public class PetAdoptionRunner implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(PetAdoptionRunner.class);

    private final SearchCriteriaBuilder searchCriteriaBuilder;
    private final PetRepository petRepository;

    @Autowired
    public PetAdoptionRunner(SearchCriteriaBuilder searchCriteriaBuilder, PetRepository petRepository) {
        this.searchCriteriaBuilder = searchCriteriaBuilder;
        this.petRepository = petRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logArgInfo(args);
        buildPetRepository();
        SearchCriteria searchCriteria = searchCriteriaBuilder.buildSearchCriteria(args);


        List<Pet> petsBySearchCriteria = petRepository.getPetsBySearchCriteria(searchCriteria);
        logger.info(String.valueOf(petsBySearchCriteria));
    }

    private void buildPetRepository() {
        Pet pet1 = new Pet(1, "snoopy", "dog", "male", "97205");

        Pet pet2 = new Pet(2, "Wilow", "cat", "female", "97205");
        Pet pet3 = new Pet(3, "Yoda", "dog", "neutered", "90210");
        Pet pet4 = new Pet(4, "Lassie", "dog", "spayed", "90210");
        Pet pet5 = new Pet(5, "Fluffy", "rabbit", "female", "97215");
        Pet pet6 = new Pet(6, "Rhea", "cat", "spayed", "97215");
        Pet pet7 = new Pet(7, "Lese", "rabbit", "female", "97215");
        Pet pet8 = new Pet(8, "Rascal", "dog", "male", "97205");

        petRepository.addPet(pet1);
        petRepository.addPet(pet2);
        petRepository.addPet(pet3);
        petRepository.addPet(pet4);
        petRepository.addPet(pet5);
        petRepository.addPet(pet6);
        petRepository.addPet(pet7);
        petRepository.addPet(pet8);
    }

    private void logArgInfo(ApplicationArguments args) {
        logger.info("PetAdoption app started with option names : {}", args.getOptionNames());

        logger.info("Application started with command-line arguments: {}", Arrays.toString(args.getSourceArgs()));
        logger.info("NonOptionArgs: {}", args.getNonOptionArgs());
        logger.info("OptionNames: {}", args.getOptionNames());

        for (String name : args.getOptionNames()){
            logger.info("arg-" + name + "=" + args.getOptionValues(name));
        }
    }
}
