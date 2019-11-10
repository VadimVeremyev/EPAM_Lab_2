package by.bsuir.committee.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import by.bsuir.committee.dao.DAOFactory;
import by.bsuir.committee.dao.UserDAO;
import by.bsuir.committee.entity.Address;
import by.bsuir.committee.entity.Committee;
import by.bsuir.committee.entity.Enrollee;

public class userService implements Service<Enrollee>{

    private static userService ourInstance = new userService();

    public static userService getInstance() {
        return ourInstance;
    }

    private userService() {
    }
	
	@Override
	public boolean add(Committee committee) {
		
		DAOFactory daoFactory = DAOFactory.getInstance();
	    UserDAO daoUser = (UserDAO) daoFactory.getDAOUser();
	  
		Enrollee enrollees = null;
		Address address = null;
		
		BufferedReader r = null;
		
		String[] dataFML;
		String[] dataAddr;

		String dataString = "";
		try {
			r = new BufferedReader(new InputStreamReader(System.in));
		
			System.out.print("Enter first name, middle name, last name and faculty(POIT or ITP) separated by a \" \" ");
			dataString = r.readLine();	
			dataFML = dataString.split(" ");
		
			System.out.print("Enter a city, street and house separated by a \" \" ");
			dataString = r.readLine();
			dataAddr = dataString.split(" ");

			address = new Address(dataAddr);
		
			enrollees = new Enrollee(dataFML, address);					
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
		
		File file = new File(fileName);
		BufferedWriter bufferedWriter = null;
		List<Enrollee> enrolleeList;
		
		
		DAOFactory daoFactory = DAOFactory.getInstance();
	    UserDAO daoUser = (UserDAO) daoFactory.getDAOUser();
	  
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(file));
			enrolleeList = daoUser.getAll();
			for(Enrollee Enrollee : enrolleeList) {	
				bufferedWriter.write(Enrollee.toString());
				bufferedWriter.newLine();
			}

			if (bufferedWriter != null)
				bufferedWriter.close();
			
		} catch (IOException e) {
		    System.out.println("Exception thrown: " + e);
		}
		
	}
}
