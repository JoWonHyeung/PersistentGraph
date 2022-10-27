package org.dfpl.lecture;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//1. 짝수 홀수
//2. 1~10까지 합 구하기
//3. 최대
//4. 평균

public class UDP {
    public static void main(String[] args) throws Exception {
        //1. 홀짝판단
//        stmt.executeUpdate("USE dbp;");
//        stmt.executeUpdate("CREATE OR REPLACE TABLE ten(value INT);");
//
//        for (int i = 1; i <= 10; i++) {
//            stmt.executeUpdate("INSERT INTO ten VALUES(" + i + ");");
//        }
//
//        stmt.executeUpdate("CREATE OR REPLACE FUNCTION odd_func(x INT) RETURNS VARCHAR(30)" +
//                "BEGIN" +
//                "   DECLARE res VARCHAR(30);" +
//                "   IF x % 2 = 0 THEN " +
//                "       SET res = 'TRUE';" +
//                "   ELSE " +
//                "       SET res = 'FALSE';" +
//                "   END IF;"+
//                "RETURN res;" +
//                "END;"
//        );

        //2. 1~10까지 합구하기
//        stmt.executeUpdate("USE dbp;");
//        stmt.executeUpdate("CREATE OR REPLACE TABLE ten_values(value INT);");
//
//        for (int i = 1; i <= 10; i++) {
//            stmt.executeUpdate("INSERT INTO ten_values VALUES (" + i + ");");
//        }
//
//        stmt.executeUpdate("CREATE OR REPLACE FUNCTION ten_sum() RETURNS INT" +
//                " BEGIN" +
//                " DECLARE sum INT;" +
//                " SET sum = 0;" +
//                " FOR i IN (SELECT value FROM ten_values)" +
//                " DO" +
//                " SET sum = sum + i.value;" +
//                " END FOR;" +
//                "RETURN SUM;" +
//                "END;");
//
//        ResultSet rs = stmt.executeQuery("SELECT ten_sum();");
//
//        rs.next();
//        System.out.println(rs.getString(1));

        //3. 최대
//        stmt.executeUpdate("USE dbp;");
//        stmt.executeUpdate("CREATE OR REPLACE TABLE ten_values(value INT);");
//
//        for (int i = 1; i <= 10; i++) {
//            stmt.executeUpdate("INSERT INTO ten_values VALUES (" + (int)(Math.random() * 100) + ");");
//        }
//
//        stmt.executeUpdate("CREATE OR REPLACE FUNCTION MY_MAX() RETURNS INT" +
//                " BEGIN" +
//                " DECLARE max INT;" +
//                " SET max = -9999;" +
//                " FOR i IN (SELECT value FROM ten_values)" +
//                " DO" +
//                "   IF max < i.value THEN" +
//                "       SET max = i.value;" +
//                "   END IF;" +
//                " END FOR;" +
//                " RETURN max;" +
//                "END;");

        //4. 평균
//        stmt.executeUpdate("USE dbp;");
//        stmt.executeUpdate("CREATE OR REPLACE TABLE ten_values(value INT);");
//
//        for (int i = 1; i <= 9; i++) {
//            stmt.executeUpdate("INSERT INTO ten_values VALUES (" + (int)(Math.random() * 100) + ");");
//        }
//
//        stmt.executeUpdate("CREATE OR REPLACE FUNCTION MY_AVG() RETURNS DOUBLE" +
//                " BEGIN" +
//                " DECLARE sum INT;" +
//                " SET sum = 0;"+
//                " FOR i IN (SELECT value FROM ten_values)" +
//                " DO" +
//                " SET sum = sum + i.value;" +
//                " END FOR;" +
//                "RETURN sum / (SELECT COUNT(*) FROM ten_values);" +
//                "END");
    }
}
