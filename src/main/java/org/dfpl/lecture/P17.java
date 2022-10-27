package org.dfpl.lecture;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class P17 {
    public static void main(String[] args) throws Exception {
        String id = "root";
        String pwd = "dnjsgud@12";

        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307",id,pwd);
        Statement stmt = connection.createStatement();

        stmt.executeUpdate("CREATE OR REPLACE DATABASE dbp");
        stmt.executeUpdate("USE dbp");
        stmt.executeUpdate("CREATE OR REPLACE TABLE loan (account_name VARCHAR(30),branch_name VARCHAR(30),balance INTEGER);");

        BufferedReader r = new BufferedReader(new FileReader("C:\\Users\\Jo\\DatabaseProgramming\\src\\main\\java\\org\\dfpl\\lecture\\data\\account.xml"));

        String lines = "";

        while (true) {
            String line = r.readLine();
            if (line == null) break;
            lines += line;
        }

        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(lines)));

        NodeList accountList = doc.getElementsByTagName("account");

        for (int i = 0; i < accountList.getLength(); i++) {
            Element elem = (Element) accountList.item(i);

            String accountNumber = ((Element)(elem.getElementsByTagName("account_number").item(0))).getTextContent();
            String branchName = ((Element)(elem.getElementsByTagName("branch_name").item(0))).getTextContent();
            String balance = ((Element)(elem.getElementsByTagName("balance").item(0))).getTextContent();

            stmt.executeUpdate("INSERT INTO loan VALUES ('" + accountNumber + "','" + branchName + "'," + balance + ");");
        }
        r.close();
    }
}
