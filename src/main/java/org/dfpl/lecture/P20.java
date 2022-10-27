package org.dfpl.lecture;

import java.sql.*;

public class P20 {
    public static void main(String[] args) throws Exception {
        String id = "root";
        String pwd = "dnjsgud@12";

        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", id, pwd);

        Statement stmt = connection.createStatement();

        stmt.executeUpdate("CREATE OR REPLACE DATABASE grade_database;");
        stmt.executeUpdate("USE grade_database;");

        stmt.executeUpdate("CREATE OR REPLACE TABLE grade (attendance INT, midterm DOUBLE,assignment DOUBLE,final DOUBLE);");
        stmt.executeUpdate("INSERT INTO grade VALUES (30, 40, 30, 20)");

        stmt.executeUpdate("ALTER TABLE grade ADD COLUMN total DOUBLE;");
        stmt.executeUpdate("UPDATE grade SET total = attendance + midterm + assignment + final;");

        ResultSet rs = stmt.executeQuery("SELECT * FROM grade;");
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        for(int i = 1;i <= columnCount;i++){
            System.out.print(rsmd.getColumnName(i) + "\t\t");
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
