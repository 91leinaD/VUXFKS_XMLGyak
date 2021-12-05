package hu.domparse.vuxfks;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DomReadVUXFKS {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        File xmlFile = new File("XMLVUXFKS.xml");
        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        System.out.println("Root elem: " + doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("mozi");
        for (int i = 0; i < nList.getLength(); i++) {

            Node nNode = nList.item(i);
            System.out.println("\nKiv�lasztott elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                String MoziID = elem.getAttribute("MoziID");

                Node n1 = elem.getElementsByTagName("mozinev").item(0);
                String mozinev = n1.getTextContent();

                Node n2 = elem.getElementsByTagName("irszam").item(0);
                String irszam = n2.getTextContent();

                Node n3 = elem.getElementsByTagName("utca").item(0);
                String utca = n3.getTextContent();

                Node n4 = elem.getElementsByTagName("hazszam").item(0);
                String hazszam = n4.getTextContent();            

                System.out.println("Mozi azonosito: " + MoziID);
                System.out.println("Mozin�v: " + mozinev);
                System.out.println("Ir�nyit�sz�m: " + irszam);
                System.out.println("Utca: " + utca); 
                System.out.println("H�zsz�m: " + hazszam);
            }
        }

        nList = doc.getElementsByTagName("dolgozik");
        for (int i = 0; i < nList.getLength(); i++) {

            Node nNode = nList.item(i);
            System.out.println("\nKiv�lasztott elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                String Mozi_IDREF = elem.getAttribute("Mozi_IDREF");
                String Elado_IDREF = elem.getAttribute("Elado_IDREF");

                Node n1 = elem.getElementsByTagName("elerheto").item(0);
                String elerheto = n1.getTextContent();

                System.out.println("Mozi azonosito: " + Mozi_IDREF);
                System.out.println("Dolgozik: " + elerheto);
                System.out.println("Elad� azonosit�ja: " + Elado_IDREF);
                
            }
        }

        nList = doc.getElementsByTagName("elado");
        for (int i = 0; i < nList.getLength(); i++) {

            Node nNode = nList.item(i);
            System.out.println("\nKiv�lasztott elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                String EladoID = elem.getAttribute("EladoID");

                Node n1 = elem.getElementsByTagName("fhsznev").item(0);
                String fhsznev = n1.getTextContent();
                Node n2 = elem.getElementsByTagName("jelszo").item(0);
                String jelszo = n2.getTextContent();
                Node n3 = elem.getElementsByTagName("admin").item(0);
                String admin = n3.getTextContent();

                System.out.println("Elado azonosit�: " + EladoID);
                System.out.println("Felhaszn�l�n�v: " + fhsznev);
                System.out.println("Jelsz�: " + jelszo);
                System.out.println("Adminjog: " + admin);
            }
        }
        
        nList = doc.getElementsByTagName("jegy");
        for (int i = 0; i < nList.getLength(); i++) {

            Node nNode = nList.item(i);
            System.out.println("\nKiv�lasztott elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                String JegyID = elem.getAttribute("JegyID");
                

                Node n1 = elem.getElementsByTagName("vetites").item(0);
                String vetites = n1.getTextContent();

                Node n2 = elem.getElementsByTagName("sor").item(0);
                String sor = n2.getTextContent();

                Node n3 = elem.getElementsByTagName("oszlop").item(0);
                String oszlop = n3.getTextContent();

                System.out.println("Jegy azonosit�ja: " + JegyID);
                System.out.println("Vetit�s: " + vetites);
                System.out.println("Sor: " + sor);
                System.out.println("Oszlop: " + oszlop);
            }
        }

        nList = doc.getElementsByTagName("vasarlas");
        for (int i = 0; i < nList.getLength(); i++) {

            Node nNode = nList.item(i);
            System.out.println("\nKiv�lasztott elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                String VasarlasID = elem.getAttribute("VasarlasID");
                String Jegy_IDREF = elem.getAttribute("Jegy_IDREF");
                String Elado_IDREF = elem.getAttribute("Elado_IDREF");

                Node n1 = elem.getElementsByTagName("vidopont").item(0);
                String vidopont = node1.getTextContent();

                System.out.println("V�s�rl�si azonosit�: " + VasarlasID);
                System.out.println("V�s�rl�s id�pontja: " + vidopont);
                System.out.println("Jegy azonosit�ja: " + Jegy_IDREF);
                System.out.println("Elad� azonosit�ja: " + Elado_IDREF);
                
            }
        }
    }
}