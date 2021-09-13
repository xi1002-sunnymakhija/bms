package com.ybl.genie.elastic.client.builder.source;

import com.ybl.genie.elastic.client.builder.aggregation.SearchAggregationBuilder;
import org.elasticsearch.index.query.AbstractQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("aggregationSourceBuilder")
public class AggregationSourceBuilder implements com.ybl.genie.elastic.client.builder.source.SourceBuilder {

    @Autowired
    private SearchAggregationBuilder searchAggregationBuilder;

    @Override
    public SearchSourceBuilder getSourceBuilder(QueryBuilder qb) {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(qb);
        return sourceBuilder;
    }

    public SearchSourceBuilder getSourceBuilder(AbstractQueryBuilder qb, String minField, String minFieldValue, String maxField, String maxFieldValue) {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(qb);
        sourceBuilder.aggregation(searchAggregationBuilder.getMaxAggregationBuilder(maxField, maxFieldValue));
        sourceBuilder.aggregation(searchAggregationBuilder.getMinAggregationBuilder(minField, minFieldValue));
        return sourceBuilder;
    }

}
