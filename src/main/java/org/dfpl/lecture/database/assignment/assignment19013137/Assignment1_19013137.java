package org.dfpl.lecture.database.assignment.assignment19013137;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.sql.*;

public class Assignment1_19013137 {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader( new FileReader("src/main/java/org/dfpl/lecture/database/assignment/assignment19013137/assignment.csv", Charset. forName("euc-kr")));

        String id = "root";
        String pwd = "dnjsgud@12";

        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", id, pwd);

        // DDL
        // Each SQL can be executed with a Statement instance
        Statement stmt = connection.createStatement();
        // DDL
        stmt.executeUpdate("CREATE OR REPLACE DATABASE dbp");
        stmt.executeUpdate("USE dbp");
        stmt.executeUpdate("CREATE TABLE table_name (facilityName VARCHAR(20), address VARCHAR(100))");
        stmt.executeUpdate("ALTER TABLE table_name CONVERT TO CHARSET UTF8");
        String sql = "insert into table_name (facilityName, address) values (%s,%s)";

        while(true){
            String line = r.readLine();
            if(line == null) break;

            String[] arr = line.split(",");
            String facilityName = arr[0];
            String address = arr[1];

            stmt.executeUpdate(String.format(sql,"'" + facilityName + "'", "'" + address + "'"));
        }
        ResultSet rs = stmt.executeQuery("select * from table_name");
        while(rs.next()){
            String facilityName = rs.getString("facilityName");
            String address = rs.getString("address");

            System.out.println(facilityName + " , " + address);
        }

        connection.close();
    }
}
