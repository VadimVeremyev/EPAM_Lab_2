package by.bsuir.committee.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

import static by.bsuir.committee.Constants.*;
import by.bsuir.committee.entity.Enrollee;


public class UserXMLParser implements XMLParser<Enrollee> {

	private static UserXMLParser ourInstance;
	private DocumentBuilder documentBuilder;

	private static final String XSD_FILEPATH = "/enrollees.xsd";
	  
    static {
    	ourInstance = new UserXMLParser();
    }
    
    private UserXMLParser() {
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
        	System.out.println(e.getMessage());
        }
    }
    
    public static UserXMLParser getInstance() {

        return ourInstance;
    }

	
	@Override
	public List<Enrollee> getData(String xmlPath) {
		List<Enrollee> enrolleeList = new LinkedList<Enrollee>();
		Document document;
		
        try {
            
            //File xmlFile = new File(String.valueOf(path1));
            //File xsdFile = new File(String.valueOf(path2));
            
           // validateXMLByXSD(xmlFile, xsdFile);
            document = documentBuilder.parse(xmlPath);
            Element element = document.getDocumentElement();
            NodeList cardList = element.getElementsByTagName("enrollee");
           
            for (int i = 0; i < cardList.getLength(); i++) {
                Element cardElement = (Element) cardList.item(i);
                Enrollee enrollee = buildEnrollee(cardElement);
                enrolleeList.add(enrollee);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

		return enrolleeList;
	}

	private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }

    private Enrollee buildEnrollee(Element element) {
    	Enrollee enrollee = new Enrollee();
   
    	enrollee.setFirstName(getElementTextContent(element, "firstName"));
    	enrollee.setMiddleName(getElementTextContent(element, "middleName"));
    	enrollee.setLastName(getElementTextContent(element, "lastName"));
    	enrollee.setFacultyName(getElementTextContent(element, "facultyName"));
    	enrollee.setId(enrollee.hashCode());
        return enrollee;
    }


    public boolean validateXMLByXSD(File xml, File xsd)  {
        try {
            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                    .newSchema(xsd)
                    .newValidator()
                    .validate(new StreamSource(xml));
        } catch (Exception e) {
           System.out.println(e);
        }
        return true;
    }

}
