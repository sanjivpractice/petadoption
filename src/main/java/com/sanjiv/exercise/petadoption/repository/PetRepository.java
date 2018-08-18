package com.sanjiv.exercise.petadoption.repository;

import static com.sanjiv.exercise.petadoption.model.PetSearchType.PET_GENDER;
import static com.sanjiv.exercise.petadoption.model.PetSearchType.PET_TYPE;
import static com.sanjiv.exercise.petadoption.model.PetSearchType.ZIPCODE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sanjiv.exercise.petadoption.model.Pet;
import com.sanjiv.exercise.petadoption.model.PetSearchType;

/**
 * @author Sanjiv on 8/17/18.
 */
public class PetRepository {


    private Map<Integer, Pet> indexToPetMap = new HashMap<>();

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

        List<String> searchTypeValues = new ArrayList<>();

        for (PetSearchType petSearchType : PetSearchType.values()) {
            switch (petSearchType) {
                case ZIPCODE:
                    searchTypeValues.add(ZIPCODE.name() + pet.getZipcode());
                    break;
                case PET_TYPE:
                    searchTypeValues.add(PET_TYPE.name() + pet.getType());
                    break;
                case PET_GENDER:
                    searchTypeValues.add(PET_GENDER.name() + pet.getGender());
                    break;
            }
        }

        for(String petSearchType : searchTypeValues) {
            if (!searchTypeToIdMap.containsKey(petSearchType)) {
                searchTypeToIdMap.put(petSearchType, new HashSet<Integer>());
            }

            Set<Integer> petIds = searchTypeToIdMap.get(petSearchType);
            petIds.add(pet.getId());

            System.out.println("petIds: "+ petIds);
        }

        buildSearchCriteriaLookupMap(pet);
    }

    private void buildSearchCriteriaLookupMap(Pet pet) {

        for(PetSearchType petSearchType : PetSearchType.values()) {
            switch (petSearchType) {
                case ZIPCODE:
                    petSearchTypeValueMap.put(pet.getZipcode(), ZIPCODE);
                    break;
                case PET_TYPE:
                    petSearchTypeValueMap.put(pet.getType(), PET_TYPE);
                    break;
                case PET_GENDER:
                    petSearchTypeValueMap.put(pet.getGender(), PET_GENDER);
                    break;
            }
        }
    }
}
