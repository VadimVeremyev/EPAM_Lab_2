package by.bsuir.committee.entity;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Faculty {
	
	private List<Enrollee> enrollees;  
	private String name;
	
	public Faculty() {
	}
	
	public Faculty(String name) {
		enrollees = new LinkedList<Enrollee>();	
		
		setName(name);	
	}
	
   	public boolean addToList(Enrollee enrollee) {
   		if(enrollee != null)
   			return enrollees.add(enrollee);
   		else
   			return false;
   	}
   	
   	public boolean removeFromList(Enrollee enrollee) {
   		if(enrollee != null)
   			return enrollees.remove(enrollee);
   		else
   			return false;
   	}
   	
   	public int length() {
   		return enrollees.size();
   	}
   
   	
   	public Enrollee getEnrollee(int id) {
   		Enrollee result = null;
   		Enrollee tempEnrollee = null;
   		
   		int i = 0;
   		int listLength = enrollees.size();
   		
   		while (i < listLength && result == null) {
   			tempEnrollee = enrollees.get(i); 
   			if( tempEnrollee.getId() == id) {
   				result = tempEnrollee; 
   			}
   			i++;
   		}	
   		return result;
   	}
   	
   	
   	public void sortList() {
   		Collections.sort(this.enrollees);
   	}
   	
   	
   	public void showList() {
   		System.out.print("Список абитуриентов " + getName() + ": \n");
   		for (int i = 0; i < enrollees.size(); i++) {
			System.out.print(enrollees.get(i)+ "\n");
		}
   		System.out.print("\n");
   		
   	}
   	
   	public List<Enrollee> getList( ) {
   		return enrollees;
   	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
