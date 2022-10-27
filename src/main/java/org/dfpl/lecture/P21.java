package org.dfpl.lecture;

import org.json.JSONObject;

import java.sql.*;

public class P21 {
    public static void main(String[] args) throws SQLException {
        String id = "root";
        String pwd = "dnjsgud@12";

        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307",id,pwd);

        Statement stmt = connection.createStatement();

        stmt.executeUpdate("USE db;");
        stmt.executeUpdate("CREATE OR REPLACE TABLE j (json JSON);");

        stmt.executeUpdate("INSERT INTO j VALUES ('" +  new JSONObject().put("name","jack").put("ID",123456).put("temperature",36.5) + "');");
        stmt.executeUpdate("INSERT INTO j VALUES ('" +  new JSONObject().put("name","jack").put("ID",123456).put("temperature",36.4) + "');");
        stmt.executeUpdate("INSERT INTO j VALUES ('" +  new JSONObject().put("name","jack").put("ID",123456) + "');");

        ResultSet rs = stmt.executeQuery("SELECT JSON_CONTAINS(json,36.5,'$.temperature')FROM j;");

        while(rs.next()){
            System.out.println(rs.getString(1));
        }

        connection.close();
    }
}
