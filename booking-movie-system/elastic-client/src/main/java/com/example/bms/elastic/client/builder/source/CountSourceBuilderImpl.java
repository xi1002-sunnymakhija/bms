package com.ybl.genie.elastic.client.builder.source;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Component;


@Component("countSourceBuilder")
public class CountSourceBuilderImpl implements SourceBuilder {
    @Override
    public SearchSourceBuilder getSourceBuilder(QueryBuilder qb) {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(qb);
        return sourceBuilder;
    }
}
