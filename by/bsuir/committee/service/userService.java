package by.bsuir.committee.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.beans.XMLEncoder;
import java.io.FileOutputStream;


import by.bsuir.committee.dao.DAOFactory;
import by.bsuir.committee.dao.UserDAO;
import by.bsuir.committee.entity.Committee;
import by.bsuir.committee.entity.Enrollee;

public class userService implements Service<Enrollee>{

    private static userService ourInstance = new userService();
    private DocumentBuilder documentBuilder;
    
    public static userService getInstance() {
        return ourInstance;
    }

    private userService() {
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
        	System.out.println(e.getMessage());
        }
    }
	
	@Override
	public boolean add(Committee committee) {
		
		DAOFactory daoFactory = DAOFactory.getInstance();
	    UserDAO daoUser = (UserDAO) daoFactory.getDAOUser();
	  
		Enrollee enrollees = null;
		
		BufferedReader r = null;
		
		String[] dataFML;

		String dataString = "";
		try {
			r = new BufferedReader(new InputStreamReader(System.in));
		
			System.out.print("Enter first name, middle name, last name and faculty(POIT or ITP) separated by a \" \" ");
			dataString = r.readLine();	
			dataFML = dataString.split(" ");
		
			enrollees = new Enrollee(dataFML);					
		}
		catch (Exception e) { 
           System.out.println("Exception thrown: " + e);
		}
	
		daoUser.add(enrollees);
		return committee.addEntollee(enrollees);
		
	}

	@Override
	public Enrollee get(int id, Committee committee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean edit(int id,  Committee committee) {
		boolean result = false;
			 
		Enrollee enrolleeTemp = committee.getEnrollee(id);
		if(enrolleeTemp != null) {
			this.remove(id, committee);
			System.out.println("Make changes to " + enrolleeTemp);
			this.add(committee);
		}
		return result;
	}

	@Override
	public boolean remove(int id, Committee committee) {
		DAOFactory daoFactory = DAOFactory.getInstance();
	    UserDAO daoUser = (UserDAO) daoFactory.getDAOUser();
	    
	    Enrollee enrollees = committee.getEnrollee(id);
	    daoUser.delete(enrollees);
		
	    return committee.removeEnrollee(id);
	}

	public boolean sort(String facultyName, Committee committee) {
		return committee.sortList(facultyName);
	}
	
	public void exit() {
		
	}
	
	public boolean show(String facultyName, Committee committee) {
		return committee.showList(facultyName);
	}
	
	public void load(String fileName, Committee committee) {	    
		DAOFactory daoFactory = DAOFactory.getInstance();
	    UserDAO daoUser = (UserDAO) daoFactory.getDAOUser();
	  
	    daoUser.addAll(committee.getList());
	}
	
	public void save(String fileName) {
		//TODO: Rework SAVE method - Create 

		Document document;
		try {
			
			document = documentBuilder.parse(fileName);
      
			Node root = document.getDocumentElement();
	       
			List<Enrollee> enrolleeList;
						
			DAOFactory daoFactory = DAOFactory.getInstance();
		    UserDAO daoUser = (UserDAO) daoFactory.getDAOUser();
		  

			FileOutputStream fos = new FileOutputStream(new File(fileName));
				
			XMLEncoder encoder = new XMLEncoder(fos);
				
			enrolleeList = daoUser.getAll();
				
			for(Enrollee enrollee : enrolleeList) {	
				Element enrolleeTag = document.createElement("enrollee");
			
				Element id = document.createElement("id");
				id.setTextContent(Integer.toString(enrollee.getId()));
				
				Element firstName = document.createElement("firstName");
				firstName.setTextContent(enrollee.getFirstName());
		
				Element middleName = document.createElement("middleName");
				middleName.setTextContent(enrollee.getMiddleName());
	
				Element lastName = document.createElement("lastName");
				lastName.setTextContent(enrollee.getLastName());
				        
				Element facultyName = document.createElement("facultyName");
				facultyName.setTextContent(enrollee.getFacultyName());			        
				   

				enrolleeTag.appendChild(id);
				enrolleeTag.appendChild(firstName);
				enrolleeTag.appendChild(middleName);
				enrolleeTag.appendChild(lastName);
		        enrolleeTag.appendChild(facultyName);
	
		        root.appendChild(enrolleeTag);
				        
		        writeDocument(document, fileName);
			}
			if(encoder != null)
				encoder.close();
			if(fos != null)
				fos.close();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}

    private static void writeDocument(Document document, String xmlPath) {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream(xmlPath);
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
