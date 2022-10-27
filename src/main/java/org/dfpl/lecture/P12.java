package org.dfpl.lecture;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Set;

public class P12 {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("C:\\Users\\Jo\\DatabaseProgramming\\src\\main\\java\\org\\dfpl\\lecture\\data\\depositor.json"));

        String str = "";

        while (true) {
            String line = r.readLine();
            if (line == null) break;
            str += line;
        }

        JSONArray jsonArray = new JSONArray(str);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject value = jsonArray.getJSONObject(i);
            for(String key : value.keySet()){
                System.out.print(key + ":" + value.getString(key) + ",");
            }
            System.out.println();
        }
    }
}
