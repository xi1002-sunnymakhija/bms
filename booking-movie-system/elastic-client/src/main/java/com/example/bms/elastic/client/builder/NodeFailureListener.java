package com.example.bms.elastic.client.builder;

import com.ybl.genie.elastic.client.ElasticSearchConfiguration;
import org.apache.http.HttpHost;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.sniff.SniffOnFailureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NodeFailureListener extends SniffOnFailureListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(NodeFailureListener.class);

    public NodeFailureListener() {
        super();
    }

    @Override
    public void onFailure(Node node) {
        LOGGER.error("Node failed: host: {}, name: {}, port: {}", node.getHost(), node.getName(), node.getHost().getPort());
        super.onFailure(node);
    }


}
