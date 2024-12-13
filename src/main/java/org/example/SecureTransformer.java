package org.example;

import javax.xml.XMLConstants;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

public class SecureTransformer {
    public static void main(String[] args) throws Exception {
        // Malicious XML with an external DTD
        String maliciousXML = """
                <?xml version="1.0"?>
                <!DOCTYPE root [
                  <!ENTITY ext SYSTEM "file:///etc/passwd">
                ]>
                <root>
                  <data>&ext;</data>
                </root>
                """;

        // Secure TransformerFactory configuration
        TransformerFactory tf = TransformerFactory.newInstance();
        tf.setFeature(javax.xml.XMLConstants.FEATURE_SECURE_PROCESSING, true); // one of these if enough
        tf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, ""); // Disable external DTDs

        // Create the Transformer
        Transformer transformer = tf.newTransformer();

        // Process the malicious XML
        StreamSource source = new StreamSource(new StringReader(maliciousXML));
        StreamResult result = new StreamResult(System.out);
        transformer.transform(source, result);
    }
}
