package org.dfpl.lecture;

import java.sql.*;

public class P6 {
    public static void main(String[] args) throws Exception {
        String id = "root";
        String pwd = "dnjsgud@12";

        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", id, pwd);

        Statement stmt = connection.createStatement();

        stmt.executeUpdate("DROP DATABASE IF EXISTS dbp");

        ResultSet rs = stmt.executeQuery("SHOW WARNINGS;");
        ResultSetMetaData rsmd = rs.getMetaData();

        int columnCount = rsmd.getColumnCount();

        for(int i =1;i<= columnCount;i++){
            System.out.print(rsmd.getColumnName(i) + "\t\t");
        }

        System.out.println();
        while(rs.next()){
            for(int i = 1;i <= columnCount;i++){
                System.out.print(rs.getString(i) + "\t\t");
            }
        }
        System.out.println();
    }
}
