package org.dfpl.lecture;

import org.json.JSONObject;

import java.sql.*;

public class P22 {
    public static void main(String[] args) throws Exception {
        String id = "root";
        String pwd = "dnjsgud@12";

        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", id, pwd);

        Statement stmt = connection.createStatement();

        JSONObject jsonObject = new JSONObject();
        System.out.println(jsonObject.put("name","jo").put("age",13));

    }
}
