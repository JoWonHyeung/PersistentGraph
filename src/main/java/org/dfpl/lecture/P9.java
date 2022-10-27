package org.dfpl.lecture;

import java.sql.*;

public class P9 {
    public static void main(String[] args) throws Exception {
        String id = "root";
        String pwd = "dnjsgud@12";

        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307:", id, pwd);

        Statement stmt = connection.createStatement();

        stmt.executeUpdate("CREATE OR REPLACE DATABASE db;");
        stmt.executeUpdate("USE db;");

        stmt.executeUpdate("CREATE OR REPLACE TABLE customer (id VARCHAR(50), pwd VARCHAR(50), datE VARCHAR(30));");

        ResultSet rs = stmt.executeQuery("DESCRIBE customer;");
        ResultSetMetaData rsmd = rs.getMetaData();

        int columnCount = rsmd.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            System.out.print(rsmd.getColumnLabel(i) + "\t\t");
        }

        System.out.println();

        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rs.getString(i) + "\t\t");
            }
            System.out.println();
        }
        connection.close();
    }
}