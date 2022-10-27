package org.dfpl.lecture;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class P16 {
    public static void main(String[] args) throws Exception {
        String id = "root";
        String pwd = "dnjsgud@12";

        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307",id,pwd);

        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

        Element root = doc.createElement("account");

        doc.appendChild(root);

        root.setAttribute("xmlns:xsd","http://www.w3.org/2001/XMLSchema");
        root.setAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

        Element accountNumber = doc.createElement("accountNumber");
        accountNumber.setAttribute("xsi:type","xsd:string");
        accountNumber.setTextContent("A-101");

        Element branchName = doc.createElement("branchName");
        branchName.setAttribute("xsi:type","xsd:string");
        branchName.setTextContent("Downtown");

        Element balance = doc.createElement("balance");
        balance.setAttribute("xsi:type","xsd:integer");
        balance.setTextContent("500");

        root.appendChild(accountNumber);
        root.appendChild(branchName);
        root.appendChild(balance);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);

        transformer.transform(source,result);
        System.out.println(writer.toString());



    }
}
