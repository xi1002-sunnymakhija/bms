package com.ybl.genie.elastic.client.builder.updaterequest;

import org.elasticsearch.index.query.AbstractQueryBuilder;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.ScriptType;

public interface UpdateQueryBuilder {
    UpdateByQueryRequest buildUpdateQuery(String conflicts, AbstractQueryBuilder qb, int size, int batch, String script, String lang, ScriptType type, String index);
}
