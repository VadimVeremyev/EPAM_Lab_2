package by.bsuir.committee.entity;

import java.util.LinkedList;
import java.util.List;

import by.bsuir.committee.entity.Enrollee;
import by.bsuir.committee.entity.Faculty;
import by.bsuir.committee.parser.ParserFactory;
import by.bsuir.committee.parser.UserXMLParser;

public class Committee {

	private List<Faculty> faculties;  

	public Committee() {
   	}
	
	public Committee(String fileName) {
		faculties = new LinkedList<Faculty>();	
		
		faculties.add(new Faculty("POIT"));
		faculties.add(new Faculty("ITP"));
		
		ParserFactory parserFactory = ParserFactory.getInstance();
		UserXMLParser fileParser = (UserXMLParser)parserFactory.getUserParser();
		
		List<Enrollee> enrolleeList = fileParser.getData(fileName);
		
		for (Faculty faculty : faculties) {
			for(Enrollee Enrollee : enrolleeList) {
				if(faculty.getName().equals(Enrollee.getFacultyName())) {
					faculty.addToList(Enrollee);
				}
			}
		}	
		
   	}
	
	public boolean addEntollee(Enrollee enrollee) {		

		boolean result = false;
		if(enrollee != null)
		{
			for (Faculty faculty : faculties) {
				if(faculty.getName().equals(enrollee.getFacultyName())) {
					result = faculty.addToList(enrollee);
				}
			}	
		}
		
		return result;
	}
   	
   	public boolean removeEnrollee(int id) {
   		Enrollee tempEnrollee = null;
   		boolean result = false;
   	
   		if(id != 0)
   		{
   			for (Faculty faculty : faculties) {
				if((tempEnrollee = faculty.getEnrollee(id)) != null ) {
					result = faculty.removeFromList(tempEnrollee);
				}
			}	
   		}
   		
   		return result;
   	}
   	
   	
   	public boolean sortList(String facultyName) {
   		boolean result = false;
   		
   		for (Faculty faculty : faculties) {
			if(faculty.getName().equals(facultyName) || facultyName.equals("all")) {
				faculty.sortList();
				result = true;
			}
		}
   		return result;
   	}
   	
   	public boolean showList(String facultyName) {
   		boolean result = false;
   		
		for (Faculty faculty : faculties) {
			if(faculty.getName().equals(facultyName) || facultyName.equals("all")){
				faculty.showList();
				result = true;
			}
		}
   		return result;
   		
   	}
   	
   	public Enrollee getEnrollee(int id) {
   		Enrollee tempEnrollee = null;
   		Enrollee result = null;
   		
   		if(id != 0)
   		{
   			for (Faculty faculty : faculties) {
				if((tempEnrollee = faculty.getEnrollee(id)) != null ) {
					result = tempEnrollee;
				}
			}	
   		}
   		
   		return result;
   	}
   	
   	public List<Enrollee> getList() {
   		
   		List<Enrollee> enrolleeList = new LinkedList<Enrollee>();
   		
   		for (Faculty faculty : faculties ) {
   			enrolleeList.addAll(faculty.getList());
   		}
   		
   		return enrolleeList;
   	}

   	
   	
}
