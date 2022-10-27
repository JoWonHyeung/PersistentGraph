package org.dfpl.lecture;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class P2 {
    public static void main(String[] args) throws Exception {
        String id = "root";
        String pwd = "dnjsgud@12";

        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", id, pwd);
        Statement stmt = connection.createStatement();

        stmt.executeUpdate("DROP DATABASE IF EXISTS dbp3");
    }
}
