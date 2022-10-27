package org.dfpl.lecture;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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

//손코딩 해야된다!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public class XMLoadingPractice {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("C:\\Users\\Jo\\DatabaseProgramming\\src\\main\\java\\org\\dfpl\\lecture\\data\\account.xml"));

        String id = "root";
        String pw = "dnjsgud@12";

        String data = r.lines().reduce("", (e1, e2) -> e1 + e2);
        r.close();

        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", id, pw);

        // Each SQL can be executed with a Statement instance
        Statement stmt = connection.createStatement();
        // DDL
        stmt.executeUpdate("CREATE OR REPLACE DATABASE midterm");
        stmt.executeUpdate("USE midterm");
        stmt.executeUpdate("CREATE TABLE account (account_number VARCHAR(50), branch_name VARCHAR(50), balance INT)");

        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(data)));

        NodeList list = doc.getElementsByTagName("account");
        String sql = "insert into account (account_number, branch_name, balance) values (%s,%s,%s)";

        for (int i = 0; i < list.getLength(); i++) {
            Element element = (Element) list.item(i);

            String accountNumber = (element.getElementsByTagName("account_number").item(0)).getTextContent();
            String branchName = (element.getElementsByTagName("branch_name").item(0)).getTextContent();
            int balance = Integer.parseInt((element.getElementsByTagName("balance").item(0)).getTextContent());

            stmt.executeUpdate(String.format(sql, "'" + accountNumber + "'", "'" + branchName + "'", "'" + balance + "'"));
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);
        System.out.println(writer.toString());
    }
}
