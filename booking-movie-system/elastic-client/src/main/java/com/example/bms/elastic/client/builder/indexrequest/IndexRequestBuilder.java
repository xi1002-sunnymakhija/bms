package com.ybl.genie.elastic.client.builder.indexrequest;


import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import java.io.IOException;


public interface IndexRequestBuilder {

    IndexRequest getIndexRequest(String index, String type, XContentBuilder xContentBuilder) throws IOException;
}
