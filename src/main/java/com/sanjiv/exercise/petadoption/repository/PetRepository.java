package com.sanjiv.exercise.petadoption.repository;

import static com.sanjiv.exercise.petadoption.repository.SearchLogicalOp.AND;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanjiv.exercise.petadoption.model.Pet;
import com.sanjiv.exercise.petadoption.model.PetSearchType;

/**
 * @author Sanjiv on 8/17/18.
 */

@Service
public class PetRepository {

    private final SearchTypeProcessor searchTypeProcessor;
    private Map<Integer, Pet> indexToPetMap = new HashMap<>();
    private Map<String, Set<Integer>> searchTypeToIdMap = new HashMap<>();
    //private List<PetSearchType> petSearchTypes;

    @Autowired
    public PetRepository(SearchTypeProcessor searchTypeProcessor) {
        this.searchTypeProcessor = searchTypeProcessor;
        //  this.petSearchTypes = Arrays.stream(PetSearchType.values()).collect(Collectors.toList());
    }

    public void addPet(Pet pet) {
        indexToPetMap.put(pet.getId(), pet);

        indexPetBySearchTypes(pet);
    }

    public List<Pet> getPetsBySearchCriteria(SearchCriteria criteria) {
        Set<Integer> resultingPetIds = null;

        for(SearchCriterion criterion : criteria.getSearchCriteria()) {

            Set<Integer> petIds = searchPetsByCriterion(criterion);

            resultingPetIds = applyLogicalOp(resultingPetIds, petIds, criterion);
        }

        List<Pet> resultPets = resultingPetIds.stream()
                                              .filter(petId -> (petId != null) && indexToPetMap.containsKey(petId))
                                              .map(petId -> indexToPetMap.get(petId))
                                              .collect(Collectors.toList());

        return resultPets;
    }

    private Set<Integer> applyLogicalOp(Set<Integer> resultingPetIds, Set<Integer> currentPetIds, SearchCriterion criterion) {
        if (resultingPetIds == null) {
            resultingPetIds = new HashSet<>(currentPetIds);
        } else {
            if (criterion.getOp() == AND) {
                resultingPetIds.retainAll(currentPetIds);
            } else {
                resultingPetIds.addAll(currentPetIds);
            }
        }

        return resultingPetIds;
    }

    private Set<Integer> searchPetsByCriterion(SearchCriterion criterion) {
        PetSearchType petSearchType = searchTypeProcessor.lookupPetSearchType(criterion.getSearchValue());
        Set<Integer> petIds = searchTypeToIdMap.get(petSearchType + criterion.getSearchValue());

        return (petIds == null) ? new HashSet<>() : petIds;
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
