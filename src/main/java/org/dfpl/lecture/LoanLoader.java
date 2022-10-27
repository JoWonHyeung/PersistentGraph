package org.dfpl.lecture;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

import org.dfpl.lecture.Loan;

public class LoanLoader {
    public static void main(String[] args) throws Exception {
        ArrayList<Loan> list = new ArrayList<>();

        BufferedReader r = new BufferedReader(new FileReader("C:\\Users\\Jo\\DatabaseProgramming\\src\\main\\java\\org\\dfpl\\lecture\\loan.txt"));

        r.lines().forEach(str -> {
            String[] arr = str.split(",");
            list.add(new Loan(arr[0], arr[1], Integer.parseInt(arr[2])));
        });

        String id = "root";
        String pw = "dnjsgud@12";

        Connection connection =
                DriverManager.getConnection("jdbc:mariadb://localhost:3307", id, pw);

        Statement stmt = connection.createStatement();

        stmt.executeUpdate("CREATE OR REPLACE DATABASE midterm;");
        stmt.executeUpdate("USE midterm;");
        stmt.executeUpdate(
                "CREATE TABLE loan (loan_number VARCHAR(50), " +
                        "branch_name VARCHAR(50), amount INT);");

        for (Loan loan : list) {
            String insertInto = "INSERT INTO loan VALUES ('" + loan.getLoanNumber() + "','" + loan.getBranchName() + "'," + loan.getAmount() + ");";
            System.out.println(insertInto);
            // INSERT INTO account ('A-101','Downtown',500);
            stmt.executeUpdate(insertInto);
        }

    }
}
