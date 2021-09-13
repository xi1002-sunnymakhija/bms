package com.ybl.genie.elastic.client.builder.searchrequest;

import com.ybl.genie.elastic.client.builder.source.SourceBuilder;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.index.query.AbstractQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component("countRequestBuilder")
public class CountRequestBuilder {

    @Autowired
    @Qualifier("countSourceBuilder")
    private SourceBuilder countSourceBuilder;

    public CountRequest getSearchRequest(String index, String indexType, AbstractQueryBuilder qb) {
        CountRequest countRequest = new CountRequest(index);
        countRequest.types(indexType);
        countRequest.source(countSourceBuilder.getSourceBuilder(qb));
        return countRequest;
    }
}
