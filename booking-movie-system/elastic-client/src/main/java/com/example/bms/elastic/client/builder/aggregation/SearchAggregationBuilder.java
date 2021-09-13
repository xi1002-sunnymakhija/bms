package com.ybl.genie.elastic.client.builder.aggregation;

import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.max.MaxAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.min.MinAggregationBuilder;
import org.springframework.stereotype.Component;

@Component("searchAggregationBuilder")
public class SearchAggregationBuilder {
    public MaxAggregationBuilder getMaxAggregationBuilder(String maxFieldName, String maxFieldValue) {
        return AggregationBuilders.
                max(maxFieldValue).
                field(maxFieldName);
    }

    public MinAggregationBuilder getMinAggregationBuilder(String minFieldName, String minFieldValue) {
        return AggregationBuilders.
                min(minFieldValue).
                field(minFieldName);
    }
}
