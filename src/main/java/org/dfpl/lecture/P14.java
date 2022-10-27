package org.dfpl.lecture;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

public class P14 {
    public static void main(String[] args) throws Exception {
        String id = "root";
        String pwd = "dnjsgud@12";

        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", id, pwd);

        Statement stmt = connection.createStatement();

        stmt.executeUpdate("CREATE OR REPLACE DATABASE dbp ;");
        stmt.executeUpdate("USE dbp;");

        stmt.executeUpdate("CREATE OR REPLACE TABLE bif (stringValue VARCHAR(30), integerValue VARCHAR(30));");

        stmt.executeUpdate("INSERT INTO bif VALUES ('jack',30);");
        stmt.executeUpdate("INSERT INTO bif VALUES ('james',35);");
        stmt.executeUpdate("INSERT INTO bif VALUES ('jake',43);");

        ResultSet rs = stmt.executeQuery("SELECT JSON_OBJECTAGG(stringValue,integerValue) FROM bif;");
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            System.out.print(rsmd.getColumnLabel(i));
        }
        System.out.println();

        rs.next();


        JSONArray jsonArray = new JSONArray("[" + rs.getString(1) + "]");
        System.out.println(jsonArray);

        for(int i = 0;i < jsonArray.length();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            for(String key : jsonObject.keySet()){
                System.out.println(key + " : " + jsonObject.get(key));
            }
        }
        //System.out.println(jsonArray);
    }
}
