import org.json.JSONObject;

import java.sql.*;

public class p19 {
    public static void main(String[] args) throws Exception {
        String id = "root";
        String pwd = "dnjsgud@12";

        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", id, pwd);

        Statement stmt = connection.createStatement();

        stmt.executeUpdate("CREATE OR REPLACE DATABASE dbp2;");

        stmt.executeUpdate("USE dbp2;");
        stmt.executeUpdate("CREATE OR REPLACE TABLE json(json_col JSON);");

        stmt.executeUpdate("INSERT INTO json VALUES ('" +
                new JSONObject().put("name", "jack").put("id", 64444).put("temperature", 36.5) + "');");

        stmt.executeUpdate("INSERT INTO json VALUES ('" +
                new JSONObject().put("name", "jo").put("id", 34444).put("temperature", 34.4) + "');");

        ResultSet rs = stmt.executeQuery("select * from json;");
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            System.out.print(rsmd.getColumnName(i) + "\t\t");
        }
        System.out.println();

        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rs.getString(i) + "\t\t");
            }
            System.out.println();
        }

    }
}
