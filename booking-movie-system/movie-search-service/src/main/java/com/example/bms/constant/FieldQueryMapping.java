package com.example.bms.constant;



import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FieldQueryMapping {
    private static Map<String, QueryStrategy> mappings;

    private JSONObject jsonObject;

    public FieldQueryMapping(JSONObject jsonObject) throws JSONException {
        this.jsonObject = jsonObject;
        setMappings(this.jsonObject);
    }

    public Map<String, QueryStrategy> getMappings() {
        return mappings;
    }

    public static void setMappings(JSONObject jsonObject) throws JSONException {
        FieldQueryMapping.mappings = new HashMap<>();
        for (Iterator it = jsonObject.keys(); it.hasNext();
        ) {
            Object param = it.next();
            FieldQueryMapping.mappings.put(
                    String.valueOf(param),
                    new QueryStrategy(jsonObject.getJSONObject(String.valueOf(param)))
            );
        }
    }
}
