package org.dfpl.lecture.database.assignment.assignment19013137;

import java.sql.*;

public class Assignment2_19013137 {
    public static void main(String[] args) throws Exception {
        String id = "root";
        String pwd = "dnjsgud@12";

        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", id, pwd);

        Statement stmt = connection.createStatement();
        stmt.executeUpdate("USE dbp");

        ResultSet rs = stmt.executeQuery("describe table_name");
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        for(int i = 1;i<=columnCount;i++){
            System.out.print(rsmd.getColumnName(i) + "\t\t");
        }
        System.out.println();

        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rs.getString(i) + "\t\t");
            }
            System.out.println();
        }
        System.out.println("-------------------------------");
        System.out.println("Query : select count(*) from table_name");
        ResultSet rs2 = stmt.executeQuery("select count(*) from table_name");

        rs2.next();
        int count = rs2.getInt(1);
        System.out.println("count : " + count);
    }
}
