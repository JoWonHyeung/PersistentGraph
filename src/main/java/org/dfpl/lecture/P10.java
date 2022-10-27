package org.dfpl.lecture;

import java.sql.*;

public class P10 {
    public static void main(String[] args) throws Exception {
        String id = "root";
        String pwd = "dnjsgud@12";

        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", id, pwd);

        Statement stmt = connection.createStatement();

        stmt.executeUpdate("CREATE OR REPLACE DATABASE dbp;");
        stmt.executeUpdate("USE dbp;");

        ResultSet rs = stmt.executeQuery("DESCRIBE account;");
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        for(int i =1;i<= columnCount;i++){
            System.out.print(rsmd.getColumnLabel(i) + "\t\t");
        }
        System.out.println();

        while(rs.next()){
            for(int i = 1;i <= columnCount;i++){
                System.out.print(rs.getString(i) + "\t\t");
            }
            System.out.println();
        }
    }
}
