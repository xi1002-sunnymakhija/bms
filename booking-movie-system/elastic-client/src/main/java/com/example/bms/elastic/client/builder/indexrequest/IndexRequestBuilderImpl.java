package com.ybl.genie.elastic.client.builder.indexrequest;

import org.elasticsearch.action.index.IndexRequest;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class IndexRequestBuilderImpl implements IndexRequestBuilder {

    public IndexRequest getIndexRequest(String index, String type, XContentBuilder xContentBuilder) throws IOException {
        return new IndexRequest(index, type).source(xContentBuilder);
    }
}
