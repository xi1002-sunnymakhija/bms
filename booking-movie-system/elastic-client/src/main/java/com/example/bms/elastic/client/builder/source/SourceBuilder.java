package com.ybl.genie.elastic.client.builder.source;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Component;

@Component
public interface SourceBuilder {
    SearchSourceBuilder getSourceBuilder(QueryBuilder qb);
}
