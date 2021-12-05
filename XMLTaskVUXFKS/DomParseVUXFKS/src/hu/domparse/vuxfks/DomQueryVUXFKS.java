package hu.domparse.vuxfks;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomQueryVUXFKS {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, ParseException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        
        File xmlFile = new File("XMLVUXFKS.xml");
        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        System.out.println("Root elem: " + doc.getDocumentElement().getNodeName());

        System.out.println("\nAz �ppen dolgoz� elad�k adatai:");
        NodeList eladolist = doc.getElementsByTagName("elado");
        NodeList dolgoziklist = doc.getElementsByTagName("dolgozik");
        
        for (int i = 0; i < eladolist.getLength(); i++) {

            Node nNode = eladolist.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                String EladoID = elem.getAttribute("EladoID");

                Node n1 = elem.getElementsByTagName("fhsznev").item(0);
                String fhsznev = n1.getTextContent();

                Node n2 = elem.getElementsByTagName("jelszo").item(0);
                String jelszo = n2.getTextContent();
                
                Node n3 = elem.getElementsByTagName("admin").item(0);
                String admin = n3.getTextContent();
                
                
         
		         for (int j = 0; j < dolgoziklist.getLength(); j++) {
		
		             Node nNode2 = dolgoziklist.item(j);
		
		             if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
		                 Element elem2 = (Element) nNode2;
		                 
		                 String Elado_IDREF = elem2.getAttribute("Elado_IDREF");
		                 
		                 Node n4 = elem2.getElementsByTagName("elerheto").item(0);
		                 String elerheto = n4.getTextContent();
		  
			             if(EladoID.equals(Elado_IDREF) &&  elerheto.toString().contains("1")) {
				        	 
			            	 System.out.println("Elad� azonosit�ja: " + EladoID);
			            	 System.out.println("Felhaszn�l�n�v: " + fhsznev);
			            	 System.out.println("Jelsz�: " + jelszo);
			            	 System.out.println("Adminjog: " + admin);
				         }
		             
		             }
		         }
        	}   
        }
        
    }
}