package by.bsuir.committee.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
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
import by.bsuir.committee.parser.ParserFactory;
import by.bsuir.committee.parser.UserXMLParser;

public class userService implements Service<Enrollee>{

    private static userService ourInstance = new userService();
    private DocumentBuilder documentBuilder;
    static Connection conn;
    
	static {
	    try { 
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	    }
	    catch(ClassNotFoundException ex) {
	    	System.err.println("Driver not found: " + ex.getMessage());
	    }
	};
	    
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
	
	public boolean connect(String login, String password) {
		//String dbUrl = "jdbc:mysql://localhost:8000/COMMITTEE"
		String dbUrl = "jdbc:mysql://localhost:8000/COMMITTEE?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		try {
			if(conn == null)
				conn = DriverManager.getConnection(dbUrl, login, password);
			
			else
				System.out.println("You already connected.");
		} catch (SQLException e) {
			System.err.println("Connection error: " + e.getMessage());
			return false;
		}
		return true;
	}
	
	public boolean createTable() {
		try {
			if(conn != null)
				conn.createStatement()
				.execute("CREATE TABLE enrollees(\n" +
				     " id integer primary key auto_increment,\n" +
				     " enrollee_id integer not null unique,\n" +
				     " firstName varchar(30) not null,\n" +
				     " middleName varchar(30) not null,\n" +
				     " lastName varchar(30) not null,\n" +
				     " facultyName varchar(30) not null\n" +
				     ")");
			else {
				System.out.println("Connect to DB");
				return false;
			}
		} catch (SQLException e) {
			System.err.println("CreateTable error: " + e.getMessage());
			return false;
		}
		
		return true;
	}
	
	public boolean insert() {		
		PreparedStatement stmt;
		try {
			stmt = conn
				    .prepareStatement("INSERT INTO enrollees( enrollee_id, firstName, middleName, lastName, facultyName)\n" +
				                  "VALUES(?, ?, ?, ?, ?)");
		} catch (SQLException e) {
				System.err.println("PrepareStatement error: " + e.getMessage());
				return false;
			}
		
		ParserFactory parserFactory = ParserFactory.getInstance();
		UserXMLParser fileParser = (UserXMLParser)parserFactory.getUserParser();
		
		List<Enrollee> enrollees = fileParser.getData();
		
		try {
			for (Enrollee enrollee : enrollees) {		
			    List<String> columns = Arrays
			    .asList( String.valueOf(enrollee.getId()) , enrollee.getFirstName(), enrollee.getMiddleName(), enrollee.getLastName(), enrollee.getFacultyName());
			    for (int n = 0 ; n < columns.size() ; n++) {
			    stmt.setString(n+1, columns.get(n));
			    }
			    stmt.execute();
			}
		} catch (SQLException e) {
			System.err.println("Add enrollee error: " + e.getMessage());
			return false;
		}
		
		return true;
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
