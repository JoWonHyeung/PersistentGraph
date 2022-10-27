package org.dfpl.lecture;

import java.sql.*;

public class P1 {
    public static void main(String[] args) throws Exception{
        String id = "root";
        String pwd = "dnjsgud@12";

        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", id, pwd);

        Statement stmt = connection.createStatement();

        stmt.executeUpdate("CREATE OR REPLACE DATABASE dbp3;");
    }
}
