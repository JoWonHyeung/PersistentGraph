package org.dfpl.lecture;

import java.sql.*;

public class p13 {
    public static void main(String[] args) throws Exception {
        String id = "root";
        String pwd = "dnjsgud@12";

        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", id, pwd);

        Statement stmt = connection.createStatement();

        stmt.executeUpdate("CREATE OR REPLACE DATABASE dbp");
        stmt.executeUpdate("USE dbp");

        stmt.executeUpdate("CREATE OR REPLACE TABLE bif (id INTEGER)");

        stmt.executeUpdate("ALTER TABLE bif ADD COLUMN stringValue VARCHAR(30)");
        stmt.executeUpdate("INSERT INTO bif VALUES (30,'ABCDEF');");

        ResultSet rs = stmt.executeQuery("DESCRIBE bif");
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

        System.out.println("------------------------------");

        rs = stmt.executeQuery("SELECT SUBSTRING(stringValue, 2, 1) FROM bif;");
        rsmd = rs.getMetaData();
        columnCount = rsmd.getColumnCount();

        while(rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rs.getString(i) + "\t\t");
            }
            System.out.println();
        }

    }
}
