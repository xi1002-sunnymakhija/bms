package com.example.bms.constant;

import java.util.HashMap;
import java.util.Map;

public enum FilterEnum {


    RMFilter("RmFilter", "asset_rm_id,sales_rm_id,service_rm_id"),
    SourcingRMFilter("sourcingRMFilter", "d_sourcing_rm"),
    CustomerId("CustomerIdFilter", "customer_id"),
    RMTypeFilter("RMTypeFilter", "sales_rm_id,sales_rm_name,service_rm_id"),
    SalesRMFilter("salesRMFilter", "d_liability_rm"),
    ServiceRMFilter("serviceRMFilter", "d_service_rm"),
    AssetRMFilter("assetRMFilter", "d_asset_rm"),
    ALLRMFilter("ALLRMFilter", "asset_rm_id,sales_rm_id,service_rm_id,sourcing_rm_id");

    private static Map<String, FilterEnum> valueToTextMapping;

    private String filterName;
    private String queryField;

    FilterEnum(String filterName, String queryField) {
        this.filterName = filterName;
        this.queryField = queryField;
    }


    private void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public String getFilterName() {
        return filterName;
    }

    public String getQueryField() {
        return queryField;
    }

    private void setQueryField(String queryField) {
        this.queryField = queryField;
    }

    public static String getQueryField(String filterName) {
        if (valueToTextMapping == null) {
            initMapping();
        }
        if (valueToTextMapping.get(filterName) != null) {
            return valueToTextMapping.get(filterName).queryField;
        }
        return null;
    }

    private static void initMapping() {
        valueToTextMapping = new HashMap<>();
        for (FilterEnum s : values()) {
            valueToTextMapping.put(s.filterName, s);
        }
    }

}
