package com.ybl.genie.elastic.client;

import com.ybl.genie.elastic.client.builder.NodeFailureListener;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.sniff.SniffOnFailureListener;
import org.elasticsearch.client.sniff.Sniffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Configuration
public class ElasticSearchConfiguration extends AbstractFactoryBean<RestHighLevelClient> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchConfiguration.class);

    private RestHighLevelClient restHighLevelClient;

    @Value("${elasticHost}")
    private String elasticHost;

    @Value("${elasticPort}")
    private int elasticPort;

    @Value("${connectionTimeout}")
    private int connectionTimeout;

    @Value("${socketTimeout}")
    private int socketTimeout;

    @Value("${retryTimeout}")
    private int retryTimeout;

    @Value("${userName}")
    private String user;


    @Value("${password}")
    private String password;


    @Override
    public void destroy() throws IOException {
        if (restHighLevelClient != null) {
            restHighLevelClient.close();
        }
    }

    @Override
    public Class<RestHighLevelClient> getObjectType() {
        return RestHighLevelClient.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public RestHighLevelClient createInstance() {
        return buildClient();
    }

    private RestHighLevelClient buildClient() {
        NodeFailureListener sniffOnFailureListener = new NodeFailureListener();
        RestClientBuilder restClientBuilder = restClientBuilder();
        restClientBuilder.setFailureListener(sniffOnFailureListener);
        if (!StringUtils.isEmpty(user) && !StringUtils.isEmpty(password)) {
            applyAuthentication(restClientBuilder, user, password);
        }
        restHighLevelClient = new RestHighLevelClient(restClientBuilder);
        Sniffer sniffer = Sniffer.builder(restHighLevelClient.getLowLevelClient()).build();
        sniffOnFailureListener.setSniffer(sniffer);
        return restHighLevelClient;
    }

    public void applyAuthentication(RestClientBuilder restClientBuilder, String user, String password) {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(user, password));
        restClientBuilder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            }
        });
    }

    private RestClientBuilder restClientBuilder() {
        LOGGER.info("Creating rest client with host:{}, port:{}", elasticHost, elasticPort);
        String[] elasticHostParts = elasticHost.split(",");
        HttpHost[] hostsArr = new HttpHost[elasticHostParts.length];
        for (int i = 0; i < elasticHostParts.length; i++) {
            hostsArr[i] = new HttpHost(elasticHostParts[i], elasticPort);
        }
        return RestClient.builder(hostsArr)
                .setRequestConfigCallback(
                        requestConfigBuilder -> requestConfigBuilder
                                .setConnectTimeout(connectionTimeout)
                                .setSocketTimeout(socketTimeout))
                .setMaxRetryTimeoutMillis(retryTimeout);
    }


}
