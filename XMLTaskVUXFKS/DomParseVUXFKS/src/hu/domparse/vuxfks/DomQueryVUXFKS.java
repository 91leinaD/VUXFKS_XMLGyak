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

        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

        System.out.println("\nAz éppen dolgozó eladók adatai:");
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
				        	 
			            	 System.out.println("Eladó azonositója: " + EladoID);
			            	 System.out.println("Felhasználónév: " + fhsznev);
			            	 System.out.println("Jelszó: " + jelszo);
			            	 System.out.println("Adminjog: " + admin);
				         }
		             
		             }
		         }
        	}   
        }
        
        System.out.println("\nAz elsõ vetitésre szóló jegyek részletei:");
        NodeList jegylist = doc.getElementsByTagName("jegy");
        
        for (int i = 0; i < jegylist.getLength(); i++) {

            Node nNode = jegylist.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                String JegyID = elem.getAttribute("JegyID");

                Node n1 = elem.getElementsByTagName("vetites").item(0);
                String vetites = n1.getTextContent();

                Node n2 = elem.getElementsByTagName("sor").item(0);
                String sor = n2.getTextContent();
                
                Node n3 = elem.getElementsByTagName("oszlop").item(0);
                String oszlop = n3.getTextContent();
                
                if (vetites.toString().contains("01")) {
                	
                	System.out.println("Jegy azonositója: " + JegyID);
	            	System.out.println("sor: " + sor);
	            	System.out.println("oszlop: " + oszlop);
                	
                
                }
		         
        	}   
        }
        
        System.out.println("\nAz eladott jegyek adatai, a jegyet eladó felhasználóneve és a mozi neve ahol történt az eladás:");
        NodeList mozilist = doc.getElementsByTagName("mozi");
        NodeList vasarlaslist = doc.getElementsByTagName("vasarlas");
        
        for (int i = 0; i < vasarlaslist.getLength(); i++) {

            Node nNode = vasarlaslist.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                String VasarlasID = elem.getAttribute("VasarlasID");
                
                String Jegy_IDREF = elem.getAttribute("Jegy_IDREF");
                
                String Elado_IDREF = elem.getAttribute("Elado_IDREF");

                Node n1 = elem.getElementsByTagName("vidopont").item(0);
                String vidopont = n1.getTextContent();
                
                
         
		         for (int j = 0; j < eladolist.getLength(); j++) {
		
		             Node nNode2 = eladolist.item(j);
		
		             if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
		                 Element elem2 = (Element) nNode2;
		                 
		                 String EladoID = elem2.getAttribute("EladoID");
		                 
		                 Node n2 = elem2.getElementsByTagName("fhsznev").item(0);
		                 String fhsznev = n2.getTextContent();
		                 
		                 if(EladoID.equals(Elado_IDREF)) {
		                	 
		                	 for (int k = 0; k < dolgoziklist.getLength(); k++) {
			                	 
			                	 Node nNode3 = dolgoziklist.item(k);
			                	 
			                	 
			             		
					             if (nNode3.getNodeType() == Node.ELEMENT_NODE) {
					            	 
					            	 Element elem3 = (Element) nNode3;
					            	 
					            	 String Mozi_IDREF = elem3.getAttribute("Mozi_IDREF");
					                 
					            	 String DElado_IDREF = elem3.getAttribute("Elado_IDREF");
					            	 
					            	 if(Elado_IDREF.equals(DElado_IDREF)) {
							        	 
					            		 
					            		 for (int l = 0; l < mozilist.getLength(); l++) {
					            			 
					            			 Node nNode4 = mozilist.item(l);
							             		
								             if (nNode4.getNodeType() == Node.ELEMENT_NODE) {
								            	 
								            	 Element elem4 = (Element) nNode4;
								            	 
								            	 String MoziID = elem4.getAttribute("MoziID");
								            	 
								            	 Node n3 = elem4.getElementsByTagName("mozinev").item(0);
								                 String mozinev = n3.getTextContent(); 
								                 
								                 if(Mozi_IDREF.equals(MoziID)) {
								               
									            	 System.out.println("Vásárlási azonositó: " + VasarlasID);
									            	 System.out.println("Jegy azonositója: " + Jegy_IDREF);
									            	 System.out.println("Vásárlás idõpontja: " + vidopont);
									            	 System.out.println("Eladó felhasználóneve: " + fhsznev);
									            	 System.out.println("Mozi neve: " + mozinev);
								                 }
								             }
					            		 }
		 
							         }

					             }
			              
			                 } 
		                 }

		             }
		         }
        	}   
        }

    }
}