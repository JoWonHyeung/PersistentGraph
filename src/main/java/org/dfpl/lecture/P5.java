package org.dfpl.lecture;

import java.sql.*;

public class P5 {
    public static void main(String[] args) throws Exception {
        String id = "root";
        String pw = "dnjsgud@12";

        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", id, pw);

        // Each SQL can be executed with a Statement instance
        Statement stmt = connection.createStatement();
        // DDL
        ResultSet rs = stmt.executeQuery("SHOW DATABASES;");

        ResultSetMetaData rsmd = rs.getMetaData();

        int columnCount = rsmd.getColumnCount();

        for(int i = 1;i <= columnCount;i++){
            System.out.print(rsmd.getColumnName(i));
        }


        System.out.println();

        while(rs.next()){
            for(int i = 1; i <= columnCount; i++){
                Object obj = rs.getObject(i);
                System.out.println(obj);
            }
        }









    }
}
