package com.ybl.genie.elastic.client.builder.searchrequest;

import com.ybl.genie.elastic.client.builder.source.AggregationSourceBuilder;
import com.ybl.genie.elastic.client.builder.source.SourceBuilder;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.AbstractQueryBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service("searchRequestBuilder")
public class SearchRequestBuilder {

    @Autowired
    @Qualifier("searchSourceBuilder")
    private SourceBuilder searchSourceBuilder;

    @Autowired
    @Qualifier("aggregationSourceBuilder")
    private AggregationSourceBuilder aggregateSourceBuilder;

    public SearchRequest getSearchRequest(String index, String indexType, AbstractQueryBuilder qb) {
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.types(indexType);
        searchRequest.source(searchSourceBuilder.getSourceBuilder(qb));
        return searchRequest;
    }

    public SearchRequest getSearchRequest(String index, String indexType, AbstractQueryBuilder qb, String minField, String minFieldValue, String maxField, String maxFieldValue) {
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.types(indexType);
        searchRequest.source(aggregateSourceBuilder.getSourceBuilder(qb, minField, minFieldValue, maxField, maxFieldValue));
        return searchRequest;
    }

    public SearchRequest getSearchRequest(String index, String indexType) {
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.types(indexType);
        return searchRequest;
    }
}
