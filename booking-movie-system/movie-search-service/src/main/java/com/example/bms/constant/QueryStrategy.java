package com.example.bms.constant;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public class QueryStrategy {

    private String elasticFieldName;
    private String queryBuilder;

    public QueryStrategy(JSONObject jsonObject) throws JSONException {
        this.elasticFieldName = String.valueOf(jsonObject.get("elasticFieldName"));
        this.queryBuilder = String.valueOf(jsonObject.get("queryBuilder"));
    }

    public String getElasticFieldName() {
        return elasticFieldName;
    }

    public void setElasticFieldName(String elasticFieldName) {
        this.elasticFieldName = elasticFieldName;
    }

    public String getQueryBuilder() {
        return queryBuilder;
    }

    public void setQueryBuilder(String queryBuilder) {
        this.queryBuilder = queryBuilder;
    }
}
