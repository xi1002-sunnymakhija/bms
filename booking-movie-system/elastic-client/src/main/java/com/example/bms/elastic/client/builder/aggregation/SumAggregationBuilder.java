package com.ybl.genie.elastic.client.builder.aggregation;

import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.springframework.stereotype.Component;


@Component
public class SumAggregationBuilder {

    public org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder getSumAggregationr(String aggregatedField, String elasticField) {
        return AggregationBuilders.
                sum(elasticField).
                field(aggregatedField);
    }


}
