package com.sanjiv.exercise.petadoption.repository;

import static com.sanjiv.exercise.petadoption.model.PetSearchType.PET_GENDER;
import static com.sanjiv.exercise.petadoption.model.PetSearchType.PET_TYPE;
import static com.sanjiv.exercise.petadoption.model.PetSearchType.ZIPCODE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sanjiv.exercise.petadoption.model.Pet;
import com.sanjiv.exercise.petadoption.model.PetSearchType;

/**
 * @author Sanjiv on 8/17/18.
 */
@Component
public class SearchTypeProcessor {

    Map<String, PetSearchType> petSearchTypeValueMap = new HashMap<>();

    public List<String> getPetSearchTypeValues(Pet pet) {
        List<String> petSearchTypeList = new ArrayList<>();

        for (PetSearchType petSearchType : PetSearchType.values()) {
            switch (petSearchType) {
                case ZIPCODE:
                    petSearchTypeList.add(ZIPCODE.name() + pet.getZipcode());
                    break;
                case PET_TYPE:
                    petSearchTypeList.add(PET_TYPE.name() + pet.getType());
                    break;
                case PET_GENDER:
                    petSearchTypeList.add(PET_GENDER.name() + pet.getGender());
                    break;
            }
        }

        return petSearchTypeList;
    }


    public void buildSearchCriteriaLookupMap(Pet pet) {

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

    public PetSearchType lookupPetSearchType(String searchTypeValue) {
        return petSearchTypeValueMap.get(searchTypeValue);
    }
}
