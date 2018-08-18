package com.sanjiv.exercise.petadoption.repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.sanjiv.exercise.petadoption.model.Pet;
import com.sanjiv.exercise.petadoption.model.PetSearchType;

/**
 * @author Sanjiv on 8/17/18.
 */
public class PetRepository {

    private final SearchTypeProcessor searchTypeProcessor;

    private Map<Integer, Pet> indexToPetMap = new HashMap<>();


    @Autowired
    public PetRepository(SearchTypeProcessor searchTypeProcessor) {
        this.searchTypeProcessor = searchTypeProcessor;
    }

    public Map<Integer, Pet> getIndexToPetMap() {
        return indexToPetMap;
    }

    public Map<String, PetSearchType> getPetSearchTypeValueMap() {
        return petSearchTypeValueMap;
    }

    public Map<String, Set<Integer>> getSearchTypeToIdMap() {
        return searchTypeToIdMap;
    }

    private Map<String, PetSearchType> petSearchTypeValueMap = new HashMap<>();
    private Map<String, Set<Integer>> searchTypeToIdMap = new HashMap<>();

    public void addPet(Pet pet) {
        indexToPetMap.put(pet.getId(), pet);

        indexPetBySearchTypes(pet);

    }


    private void indexPetBySearchTypes(Pet pet) {
        List<String> searchTypeValues = searchTypeProcessor.getPetSearchTypeValues(pet);

        for(String petSearchType : searchTypeValues) {
            if (!searchTypeToIdMap.containsKey(petSearchType)) {
                searchTypeToIdMap.put(petSearchType, new HashSet<Integer>());
            }

            Set<Integer> petIds = searchTypeToIdMap.get(petSearchType);
            petIds.add(pet.getId());

            System.out.println("petIds: "+ petIds);
        }

        searchTypeProcessor.buildSearchCriteriaLookupMap(pet);
    }

}
