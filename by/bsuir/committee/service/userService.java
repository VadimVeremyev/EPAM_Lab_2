package by.bsuir.committee.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import by.bsuir.committee.dao.DAOFactory;
import by.bsuir.committee.dao.userDAO;
import by.bsuir.committee.entity.address;
import by.bsuir.committee.entity.committee;
import by.bsuir.committee.entity.enrollee;

public class userService implements Service<enrollee>{

    private static userService ourInstance = new userService();

    public static userService getInstance() {
        return ourInstance;
    }

    private userService() {
    }
	
	@Override
	public boolean add(committee committee) {
		
		DAOFactory daoFactory = DAOFactory.getInstance();
	    userDAO daoUser = (userDAO) daoFactory.getDAOUser();
	  
		enrollee enrollee = null;
		address address = null;
		
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

			address = new address(dataAddr);
		
			enrollee = new enrollee(dataFML, address);					
		}
		catch (Exception e) { 
           System.out.println("Exception thrown: " + e);
		}
	
		daoUser.add(enrollee);
		return committee.AddEntollee(enrollee);
		
	}

	@Override
	public enrollee get(int id, committee committee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean edit(int id,  committee committee) {
		boolean result = false;
			 
		enrollee enrolleeTemp = committee.getEnrollee(id);
		if(enrolleeTemp != null) {
			this.remove(id, committee);
			System.out.println("Make changes to " + enrolleeTemp);
			this.add(committee);
		}
		return result;
	}

	@Override
	public boolean remove(int id, committee committee) {
		DAOFactory daoFactory = DAOFactory.getInstance();
	    userDAO daoUser = (userDAO) daoFactory.getDAOUser();
	    
	    enrollee enrollee = committee.getEnrollee(id);
	    daoUser.delete(enrollee);
		
	    return committee.RemoveEnrollee(id);
	}

	public boolean sort(String facultyName, committee committee) {
		return committee.SortList(facultyName);
	}
	
	public void exit() {
		
	}
	
	public boolean show(String facultyName, committee committee) {
		return committee.ShowList(facultyName);
	}
	
	public void load(String fileName, committee committee) {	    
		DAOFactory daoFactory = DAOFactory.getInstance();
	    userDAO daoUser = (userDAO) daoFactory.getDAOUser();
	  
	    daoUser.addAll(committee.getList());
	}
	
	public void save(String fileName) {
		
		File file = new File(fileName);
		BufferedWriter bufferedWriter = null;
		List<enrollee> enrolleeList;
		
		
		DAOFactory daoFactory = DAOFactory.getInstance();
	    userDAO daoUser = (userDAO) daoFactory.getDAOUser();
	  
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(file));
			enrolleeList = daoUser.getAll();
			for(enrollee enrollee : enrolleeList) {	
				bufferedWriter.write(enrollee.toString());
				bufferedWriter.newLine();
			}

			if (bufferedWriter != null)
				bufferedWriter.close();
			
		} catch (IOException e) {
		    System.out.println("Exception thrown: " + e);
		}
		
	}
}
