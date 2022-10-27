package org.dfpl.lecture;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONPractice {
    public static void main(String[] args) {
        JSONObject emptyObj = new JSONObject();
        emptyObj.put("name","hi");
        emptyObj.put("name3","hi1");
        emptyObj.put("name2","hi2");

        emptyObj.remove("name2");
        System.out.println(emptyObj);

        JSONArray obj = new JSONArray();
        obj.put("1");
    }
}
