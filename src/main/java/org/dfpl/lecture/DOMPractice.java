package org.dfpl.lecture;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

public class DOMPractice {
    public static void main(String[] args) throws Exception{
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

        Element root = doc.createElement("account");
        doc.appendChild(root);

        root.setAttribute("xmlns:xsd","http://www.w3.org/2001/XMLSchema");
        root.setAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSChema-instance");

        Element account_number = doc.createElement("account_number");
        account_number.setAttribute("xsi:type","xsd:string");
        account_number.setTextContent("A-101");

        Element branch_name = doc.createElement("branch_name");
        branch_name.setAttribute("xsi:type","xsd:string");
        branch_name.setTextContent("Downtown");

        Element balance = doc.createElement("balance");
        balance.setAttribute("xsi:type","xsd:integer");
        balance.setTextContent("500");

        root.appendChild(account_number);
        root.appendChild(branch_name);
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
