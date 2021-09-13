package com.example.bms.service.strategy;


import com.example.bms.constant.FieldQueryMapping;
import com.example.bms.resource.request.DynamicFilter;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilterStatergyFactory {

    @Autowired
    BeanFactory beanFactory;

    public void apply(DynamicFilter dynamicFilter, FieldQueryMapping fieldQueryMapping, BoolQueryBuilder boolQueryBuilder) {
        QueryBuilderStrategy filterStatgergy = beanFactory.getBean(fieldQueryMapping.getMappings().get(dynamicFilter.getFilterType()).getQueryBuilder(), QueryBuilderStrategy.class);
        filterStatgergy.applyQuery(boolQueryBuilder, fieldQueryMapping.getMappings().get(dynamicFilter.getFilterType()).getElasticFieldName(), dynamicFilter.getFilterValues(), dynamicFilter.getOrCondition());
    }
}
