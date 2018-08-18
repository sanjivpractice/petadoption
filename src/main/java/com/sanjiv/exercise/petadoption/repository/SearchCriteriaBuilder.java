package com.sanjiv.exercise.petadoption.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

/**
 * @author Sanjiv on 8/17/18.
 */
@Component
public class SearchCriteriaBuilder {

    public SearchCriteria buildSearchCriteria(ApplicationArguments args) {
        List<String> nonOptionArgs = args.getNonOptionArgs();
        int nonOpArgsSize = nonOptionArgs.size();
        List<SearchCriterion> searchCriterions = new ArrayList<>();
        SearchCriteria searchCriteria = new SearchCriteria(searchCriterions);

        for (int ii = 0; ii < nonOpArgsSize; ii++) {
            SearchCriterion sc = null;
            String nonOpArg = nonOptionArgs.get(ii);

            if (ii == 0) {
                sc = new SearchCriterion(SearchLogicalOp.AND, nonOpArg);
            } else if ((ii + 1) <= (nonOpArgsSize-1)) {
                sc = new SearchCriterion(SearchLogicalOp.valueOf(nonOpArg), nonOptionArgs.get(ii+1));
                ii++;
            }

            searchCriterions.add(sc);
        }

        return searchCriteria;
    }
}
