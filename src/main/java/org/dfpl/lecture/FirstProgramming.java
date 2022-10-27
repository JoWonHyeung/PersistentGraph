package org.dfpl.lecture;

import java.sql.*;

public class FirstProgramming {
    public static void main(String[] args) throws SQLException {
        String id = "root";
        String pw = "dnjsgud@12";

        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", id, pw);

        // Each SQL can be executed with a Statement instance
        Statement stmt = connection.createStatement();
        // DDL
        stmt.executeUpdate("CREATE OR REPLACE DATABASE dbp");
        stmt.executeUpdate("USE dbp");
        stmt.executeUpdate("CREATE TABLE loan (loan_number VARCHAR(10), branch_name VARCHAR(10), amount VARCHAR(10))");
        // DML
        stmt.executeUpdate("INSERT INTO loan VALUES ('L-11', 'Round Hill', '900');");
        stmt.executeUpdate("INSERT INTO loan VALUES ('L-14', 'Downtown', '1500');");
        stmt.executeUpdate("INSERT INTO loan VALUES ('L-15', 'Perryridge', '1500');");
        stmt.executeUpdate("INSERT INTO loan VALUES ('L-16', 'Perryridge', '1300');");
        stmt.executeUpdate("INSERT INTO loan VALUES ('L-17', 'Downtown', '1000');");
        stmt.executeUpdate("INSERT INTO loan VALUES ('L-23', 'Redwood', '2000');");
        stmt.executeUpdate("INSERT INTO loan VALUES ('L-93', 'Mianus', '500');");
        // DQL
        ResultSet rs = stmt.executeQuery("SELECT * FROM loan;");
        while (rs.next()) {
            String loan_number = rs.getString("loan_number");
            String branch_name = rs.getString("branch_name");
            String amount = rs.getString("amount");
            System.out.println(loan_number + "\t" + branch_name + "\t" + amount);
        }
        connection.close();


    }
}
