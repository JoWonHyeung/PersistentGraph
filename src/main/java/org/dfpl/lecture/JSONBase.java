package org.dfpl.lecture;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

public class JSONBase {
    public static void main(String[] args) throws Exception {
        BufferedReader r =
                new BufferedReader(
                        new FileReader(
                                "C:\\Users\\Jo\\DatabaseProgramming\\src\\main\\java\\org\\dfpl\\lecture\\data\\depositor.json"));
        String string = "";

        while(true){
            String str = r.readLine();
            if(str == null)
                break;
            string += str;
            System.out.println(str);
        }

        // JSON Array의 문자열이 준비됨
        System.out.println(string);
        JSONArray array = new JSONArray(string);
        System.out.println(array);

        for(int i = 0 ; i < array.length() ; i++){

            JSONObject value = array.getJSONObject(i);

            for(String key : value.keySet()){
                System.out.print(key + " -> " + value.get(key) + "\t\t");
            }
            System.out.println();
        }

        // parsing file "JSONExample.json"
//        Object ob = new JSONParser().parse(new FileReader("C:\\Users\\Jo\\DatabaseProgramming\\src\\main\\java\\org\\dfpl\\lecture\\depositor.json"));
//
//        // typecasting ob to JSONObject
//        org.json.simple.JSONArray js = (org.json.simple.JSONArray) ob;
//
//        System.out.println(js.get(0));
    }
}
