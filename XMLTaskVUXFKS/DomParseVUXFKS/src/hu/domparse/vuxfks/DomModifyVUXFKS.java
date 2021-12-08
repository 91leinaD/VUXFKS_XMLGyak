package hu.domparse.vuxfks;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import javax.xml.transform.OutputKeys;

import javax.xml.transform.TransformerException;


public class DomModifyVUXFKS {

    public static void main(String[] args) {

    	try {
            File inputFile = new File("XMLVUXFKS.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(inputFile);
            Node root = doc.getFirstChild();

          // Modositjuk az összes eladó jelszavát "ujjelszo0"-ra annak aki nem rendelkezik admin joggal
            NodeList eladoList = doc.getElementsByTagName("elado");
            for (int i = 0; i < eladoList.getLength(); i++) {

                Node nNode1 = eladoList.item(i);

                if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) nNode1;

                    Node n2 = elem.getElementsByTagName("admin").item(0);
                    String admin = n2.getTextContent();

                    if (admin.equals("0")) {
                    	
                    	NodeList childNodes = nNode1.getChildNodes();
						for (int j = 0; j < childNodes.getLength(); j++) {
							Node childNode = childNodes.item(j);
							if (childNode.getNodeName().equals("jelszo")) {
								childNode.setTextContent("ujjelszo0");
								
								 
							}

						}
                       
                    }
                }
            }
            
            NodeList dolgoznak = root.getChildNodes();
            for (int i = 0; i < dolgoznak.getLength(); i++) {
                
            	Node n1 = dolgoznak.item(i);

                if (n1.getNodeName().equals("dolgoznak"))
                    root.removeChild(n1);
            }
            
            
            
            modify(doc);
            
    	} catch (Exception e) {
			e.printStackTrace();
		}
            
            
    }
    
    private static void modify(Document doc) throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		System.out.println("-Modified File-");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amunt", "2");

		DOMSource source = new DOMSource(doc);

		StreamResult console = new StreamResult(System.out);

		transformer.transform(source, console);
		
	}
}