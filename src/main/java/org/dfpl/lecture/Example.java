package org.dfpl.lecture;


import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.nio.Buffer;
import java.sql.*;
import java.util.ArrayList;

public class Example {
    public static void main(String[] args) throws Exception {
        String id = "root";
        String pwd = "dnjsgud@12";

        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", id, pwd);

        Statement stmt = connection.createStatement();
        stmt.executeUpdate("USE db");

        ResultSet rs = stmt.executeQuery("DESCRIBE loan;");
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
    }
}
