package com.ybl.genie.elastic.client.builder.source;

import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Component("searchSourceBuilder")
public class SearchSourceBuilderImpl implements SourceBuilder {

    @Value("${elasticMaxResponseCount}")
    private int elasticMaxResponseCount;

    @Override
    public SearchSourceBuilder getSourceBuilder(QueryBuilder qb) {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(qb);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        sourceBuilder.size(elasticMaxResponseCount);
        return sourceBuilder;
    }
}
