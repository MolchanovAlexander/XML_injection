package org.example;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document document =factory.newDocumentBuilder().newDocument();
        Element element = document.createElement("div");
        Attr attr = document.createAttribute("id");
        attr.setValue("dniwe");
        element.setAttribute("class", "dno1");
        element.setAttributeNode(attr);
        document.appendChild(element);
        System.out.println(document);

        TransformerFactory tf = TransformerFactory.newInstance();
        tf.setFeature(javax.xml.XMLConstants.FEATURE_SECURE_PROCESSING, true); // one of these if enough
        tf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, ""); // Disable external DTDs

        // Create the Transformer
        Transformer transformer = tf.newTransformer();
        DOMSource source = new DOMSource((Node) document);
        // Process the malicious XML
        //StreamSource source = new StreamSource(new StringReader(document.getDocumentURI()));
        StreamResult result = new StreamResult(System.out);
        transformer.transform(source, result);
    }
}