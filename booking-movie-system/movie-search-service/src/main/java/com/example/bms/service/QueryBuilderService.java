package com.example.bms.service;

import com.example.bms.resource.request.DynamicFilter;
import org.elasticsearch.index.query.BoolQueryBuilder;

import java.util.List;

public interface QueryBuilderService {
    public BoolQueryBuilder buildBooleanQueryForSearchParameters(List<DynamicFilter> dynamicFilters);}