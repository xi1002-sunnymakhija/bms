package genie.cases.search.strategy;


import genie.cases.search.util.DateUtil;
import io.micrometer.core.instrument.util.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("DateRangeQueryBuilder")
public class DateRangeQueryBuilder implements QueryBuilderStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateRangeQueryBuilder.class);

    @Override
    public BoolQueryBuilder applyQuery(BoolQueryBuilder boolQueryBuilder, String elasticFieldName, List<String> valueToBeSearched, List<String> orCond) {
        String firstDate = DateUtil.toDesiredFormat("dd-MMM-yy", "yyyy-MM-dd HH:mm:ss", (String) valueToBeSearched.toArray()[0]);
        String secondDate = DateUtil.toDesiredFormat("dd-MMM-yy", "yyyy-MM-dd HH:mm:ss", !StringUtils.isEmpty((String) valueToBeSearched.toArray()[1]) ? (String) valueToBeSearched.toArray()[1] : null);
        boolQueryBuilder.must(QueryBuilders.rangeQuery(elasticFieldName).from(firstDate).to(secondDate));
        return boolQueryBuilder;
    }

}
