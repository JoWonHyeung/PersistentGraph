package org.dfpl.lecture;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class P19 {
    public static void main(String[] args) throws Exception {
        String id = "root";
        String pwd = "dnjsgud@12";

        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", id, pwd);

        Statement stmt = connection.createStatement();
        stmt.executeUpdate("USE db");
        stmt.executeUpdate("CREATE OR REPLACE TABLE account(account_number VARCHAR(30),branch_name VARCHAR(30),balance INT);");
        BufferedReader r = new BufferedReader(new FileReader("C:\\Users\\Jo\\DatabaseProgramming\\src\\main\\java\\org\\dfpl\\lecture\\data\\account.xml"));
        String text = "";

        while (true) {
            String line = r.readLine();
            if (line == null) break;
            text += line;
        }

        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(text)));
        NodeList nodeList = doc.getElementsByTagName("account");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Element account = (Element) nodeList.item(i);
            String accountNumber = account.getElementsByTagName("account_number").item(0).getTextContent();
            String branchName = account.getElementsByTagName("branch_name").item(0).getTextContent();
            int balance = Integer.parseInt(account.getElementsByTagName("balance").item(0).getTextContent());
            stmt.executeUpdate("INSERT INTO account VALUES ('" + accountNumber + "','" + branchName + "'," + balance +");");
        }




    }
}
