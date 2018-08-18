package com.sanjiv.exercise.petadoption;

import com.sanjiv.exercise.petadoption.model.Pet;
import com.sanjiv.exercise.petadoption.repository.PetRepository;
import com.sanjiv.exercise.petadoption.repository.SearchTypeProcessor;

/**
 * @author Sanjiv on 8/17/18.
 */
public class PetFactory {

    public static PetRepository buildPetRepository(SearchTypeProcessor searchTypeProcessor) {
        Pet pet1 = new Pet(1, "snoopy", "dog", "male", "97205");

        Pet pet2 = new Pet(2, "Wilow", "cat", "female", "97205");
        Pet pet3 = new Pet(3, "Yoda", "dog", "neutered", "90210");
        Pet pet4 = new Pet(4, "Lassie", "dog", "spayed", "90210");
        Pet pet5 = new Pet(5, "Fluffy", "rabbit", "female", "97215");
        Pet pet6 = new Pet(6, "Rhea", "cat", "spayed", "97215");
        Pet pet7 = new Pet(7, "Lese", "rabbit", "female", "97215");
        Pet pet8 = new Pet(8, "Rascal", "dog", "male", "97205");

        PetRepository petRepository = new PetRepository(searchTypeProcessor);
        petRepository.addPet(pet1);
        petRepository.addPet(pet2);
        petRepository.addPet(pet3);
        petRepository.addPet(pet4);
        petRepository.addPet(pet5);
        petRepository.addPet(pet6);
        petRepository.addPet(pet7);
        petRepository.addPet(pet8);

        return petRepository;
    }
}
