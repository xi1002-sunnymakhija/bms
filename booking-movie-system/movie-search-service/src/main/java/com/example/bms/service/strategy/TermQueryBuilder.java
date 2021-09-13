package com.example.bms.service.strategy;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("TermQueryBuilder")
public class TermQueryBuilder implements QueryBuilderStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(TermQueryBuilder.class);

    @Override
    public BoolQueryBuilder applyQuery(BoolQueryBuilder boolQueryBuilder, String elasticFieldName, List<String> valueToBeSearched, List<String> orCond) {
        isValid(valueToBeSearched);
        LOGGER.info("Initiated request to build query for ADIDs : {}", valueToBeSearched);
        return boolQueryBuilder.must(QueryBuilders.termQuery(elasticFieldName, valueToBeSearched.iterator().next()));
    }
}
