package com.example.bms.service.strategy;

import org.apache.logging.log4j.util.Strings;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("MultimatchQueryBuilder")
public class MultimatchQueryBuilder implements QueryBuilderStrategy {
    private static final Logger LOGGER = LoggerFactory.getLogger(MultimatchQueryBuilder.class);

    @Override
    public BoolQueryBuilder applyQuery(BoolQueryBuilder boolQueryBuilder, String elasticFieldName, List<String> valueToBeSearched, List<String> orCond) {
        isValid(valueToBeSearched);
        if (Strings.isEmpty(elasticFieldName)) {
            return boolQueryBuilder;
        }
        String[] elasticFieldNames = elasticFieldName.split(",");
        LOGGER.info("Initiated request to build query for ADIDs : {}", valueToBeSearched);
        return boolQueryBuilder.must(new MultiMatchQueryBuilder(valueToBeSearched.toArray()[0], elasticFieldNames));
    }
}
