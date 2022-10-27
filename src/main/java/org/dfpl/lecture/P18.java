package org.dfpl.lecture;

import java.sql.*;

public class P18 {
    public static void main(String[] args) throws Exception {
        String id = "root";
        String pwd = "dnjsgud@12";

        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", id, pwd);

        Statement stmt = connection.createStatement();

        stmt.executeUpdate("CREATE OR REPLACE DATABASE dbp2;");
        stmt.executeUpdate("USE dbp2;");


        stmt.executeUpdate("CREATE OR REPLACE TABLE grade (" +
                "id INTEGER," +
                "name VARCHAR(30)," +
                "attendance DOUBLE," +
                "midterm DOUBLE," +
                "assignment DOUBLE," +
                "final DOUBLE);");

        stmt.executeUpdate("INSERT INTO grade VALUES (1,'JO',30.2,40.2,50.2,30.2);");

        stmt.executeUpdate("ALTER TABLE grade ADD COLUMN total DOUBLE;");

        stmt.executeUpdate("UPDATE grade SET total= attendance + midterm + assignment + final;");

        ResultSet rs = stmt.executeQuery("select * from grade;");
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            System.out.print(rsmd.getColumnName(i) + "\t\t");
        }
        System.out.println();
        while(rs.next()){
            for(int i = 1;i <= columnCount;i++){
                System.out.print(rs.getString(i) + "\t\t");
            }
            System.out.println();
        }

        connection.close();
    }
}
