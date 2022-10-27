package org.dfpl.lecture;

import java.sql.*;

public class P7 {
    public static void main(String[] args) throws SQLException {
        String id = "root";
        String pwd = "dnjsgud@12";

        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", id, pwd);

        Statement stmt = connection.createStatement();

        stmt.executeUpdate("CREATE OR REPLACE DATABASE dbp;");
        stmt.executeUpdate("USE dbp;");
        stmt.executeUpdate("CREATE OR REPLACE TABLE customer (customer VARCHAR(50), customer_street VARCHAR(50), customer_city VARCHAR(50), "
                + "latitude DOUBLE, longitude DOUBLE, last_update DATE);");

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
    }
}
