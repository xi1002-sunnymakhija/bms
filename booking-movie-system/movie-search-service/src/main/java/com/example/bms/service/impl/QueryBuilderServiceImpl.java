package com.example.bms.service.impl;

import com.example.bms.constant.FieldQueryMapping;
import com.example.bms.resource.request.DynamicFilter;
import com.example.bms.service.QueryBuilderService;
import com.example.bms.service.strategy.FilterStatergyFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryBuilderServiceImpl implements QueryBuilderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryBuilderServiceImpl.class);

    private static final String INVALID_FILTER_NAME_MESSAGE = "Requested filter is not available in the context";

    private FieldQueryMapping fieldQueryMapping;

    @Autowired
    private FilterStatergyFactory filterFactoryStatergy;


    public QueryBuilderServiceImpl(@Value("${field-query-mapping}") String fieldQueryMapping) throws Exception {
        this.fieldQueryMapping = new FieldQueryMapping(new JSONObject(fieldQueryMapping));
    }


    public BoolQueryBuilder buildBooleanQueryForSearchParameters(List<DynamicFilter> dynamicFilters) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        try {
            LOGGER.info("Initiated request to build query for filter : {}", dynamicFilters);
            dynamicFilters.forEach(dynamicFilter -> {
                filterFactoryStatergy.apply(dynamicFilter, fieldQueryMapping, boolQueryBuilder);
                LOGGER.debug("Successfully built query for dynamicFilters : {}", boolQueryBuilder);
            });
        } catch (Exception ex) {
            LOGGER.error("Exception in building query : {}", ex);
        }
        return boolQueryBuilder;
    }
}
