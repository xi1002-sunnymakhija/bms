package com.ybl.genie.elastic.client.builder.updaterequest;

import org.elasticsearch.index.query.AbstractQueryBuilder;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UpdateRequestQueryBuilder implements UpdateQueryBuilder {
    @Override
    public UpdateByQueryRequest buildUpdateQuery(String conflicts, AbstractQueryBuilder qb, int size, int batch, String script, String lang, ScriptType type, String index) {
        UpdateByQueryRequest request = new UpdateByQueryRequest(index);
        request.setConflicts(conflicts);
        request.setQuery(qb);
        request.setSize(size);
        request.setBatchSize(batch);
        request.setScript(
                new Script(
                        type, lang,
                        script,
                        Collections.emptyMap()));
        return request;
    }
}
