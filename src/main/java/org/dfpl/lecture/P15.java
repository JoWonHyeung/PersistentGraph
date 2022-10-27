package org.dfpl.lecture;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

public class P15 {
    public static void main(String[] args) throws Exception {
        String id = "root";
        String pwd = "dnjsgud@12";

        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", id, pwd);

        Statement stmt = connection.createStatement();

        stmt.executeUpdate("CREATE OR REPLACE DATABASE dbp ;");
        stmt.executeUpdate("USE dbp;");

        stmt.executeUpdate("CREATE TABLE j1 (j JSON);");

        stmt.executeUpdate("INSERT INTO j1 VALUES ('" + new JSONObject().put("name", "jack").put("id", 23131) + "')");
        stmt.executeUpdate("INSERT INTO j1 VALUES ('" + new JSONObject().put("name", "kim").put("id", 213) + "')");
        stmt.executeUpdate("INSERT INTO j1 VALUES ('" + new JSONObject().put("name", "sol").put("description", new JSONObject().put("address", "mesh153@naver.com")).put("id", 213) + "')");

        String str = "";

        ResultSet rs = stmt.executeQuery("SELECT j FROM j1");

        while (rs.next()) {
            String json = rs.getString(1);
            if (json == null) break;
            str += json + ',';
        };

        System.out.println(str);
        JSONArray jsonArray = new JSONArray("[" + str + "]");
        System.out.println(jsonArray);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            for(String key : jsonObject.keySet()){
                System.out.print(key + " : " + jsonObject.get(key) + "\t\t");
            }
            System.out.println();
        }

        connection.close();
    }
}
