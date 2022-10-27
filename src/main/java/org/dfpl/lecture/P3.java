package org.dfpl.lecture;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class P3 {
    public static void main(String[] args) throws Exception {
        String id = "root";
        String pwd = "dnjsgud@12";

        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", id, pwd);

        Statement stmt = connection.createStatement();

        stmt.executeUpdate("CREATE OR REPLACE DATABASE db");
        stmt.executeUpdate("USE db");

        stmt.executeUpdate("CREATE OR REPLACE TABLE loan (number VARCHAR(30),branch VARCHAR(30),amount INTEGER);");

        BufferedReader r = new BufferedReader(new FileReader("C:\\Users\\Jo\\DatabaseProgramming\\src\\main\\java\\org\\dfpl\\lecture\\data\\loan.txt"));

        while (true) {
            String line = r.readLine();
            if (line == null) break;
            String[] arr = line.split(",");

            String number = arr[0];
            String branch = arr[1];
            int amount = Integer.parseInt(arr[2]);
            System.out.println(number + " , " + branch + " , " + Integer.toString(amount));
            stmt.executeUpdate("INSERT loan VALUES ('" + number + "'" + ",'" + branch + "'," + amount +")");
        }

        ResultSet rs = stmt.executeQuery("SELECT * FROM loan;");
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        System.out.println();

        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                if(rsmd.getColumnTypeName(i).equals("INTEGER")){
                    System.out.print(rs.getInt(i) + "\t\t");
                }
                else {
                    System.out.print(rs.getString(i) + "\t\t");
                }
            }
            System.out.println();
        }



    }
}
