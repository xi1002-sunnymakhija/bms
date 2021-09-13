package com.example.bms.resource.request;

import java.util.List;
import java.util.StringJoiner;

public class DynamicFilter {

    private String filterType;
    private List<String> filterValues;
    private List<String> orCondition;

    public List<String> getOrCondition() {
        return orCondition;
    }

    public void setOrCondition(List<String> orCondition) {
        this.orCondition = orCondition;
    }


    public DynamicFilter() {
    }

    public DynamicFilter(String filterType, List<String> filterValues) {
        this.filterType = filterType;
        this.filterValues = filterValues;
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public List<String> getFilterValues() {
        return filterValues;
    }

    public void setFilterValues(List<String> filterValues) {
        this.filterValues = filterValues;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DynamicFilter.class.getSimpleName() + "[", "]")
                .add("filterType='" + filterType + "'")
                .add("filterValues=" + filterValues)
                .add("orCondition=" + orCondition)
                .toString();
    }
}

