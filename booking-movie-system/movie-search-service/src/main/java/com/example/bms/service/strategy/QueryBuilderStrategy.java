package com.example.bms.service.strategy;

import com.example.bms.exception.SearchCriteriaException;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface QueryBuilderStrategy {

    String EXCEPTION_MESSAGE = "Search filter values cannot be null or empty";

    default void isValid(List<String> valueToBeSearched) {
        if (valueToBeSearched.isEmpty()) {
            throw new SearchCriteriaException(EXCEPTION_MESSAGE);
        }
    }

    BoolQueryBuilder applyQuery(BoolQueryBuilder boolQueryBuilder, String elasticFieldName, List<String> valueToBeSearched, List<String> orCondition);
}
