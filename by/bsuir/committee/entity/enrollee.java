package by.bsuir.committee.entity;

import by.bsuir.committee.entity.address;

public class enrollee implements Comparable<enrollee>{
	
	private String firstName;
	private String middleName;
	private String lastName;
	private String facultyName;
	private int id = 0;
	
	private address address;
	
	public enrollee()
	{
		
	}
	
	public enrollee(String[] Data, address address)
	{
		if (Data != null && Data.length < 4)
			throw new IllegalArgumentException("Wrong number of parameters");
		
		
		setFirstName(Data[0]);
		setMiddleName(Data[1]);
		setLastName(Data[2]);
		setFacultyName(Data[3]);
		setAddress(address);
		setId(hashCode());
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public address getAddress() {
		return address;
	}
	public void setAddress(address address) {
		this.address = address;
	}

	public String getFacultyName() {
		return facultyName;
	}
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public int compareTo(enrollee enrollee) { 
		if(this.firstName.compareTo(enrollee.firstName) > 0) {
			return 1;
		} else if (this.firstName.compareTo(enrollee.firstName) < 0) {
			return -1;
		} else if (this.middleName.compareTo(enrollee.middleName) > 0) { 
			return 1; 	// same firstName, larger middleName
		} else if (this.middleName.compareTo(enrollee.middleName) < 0) {
			return -1;	// same firstName, smaller middleName
		} else if (this.lastName.compareTo(enrollee.lastName) > 0) {
			return 1;	// same firstName and middleName, larger lastName
		} else if (this.lastName.compareTo(enrollee.lastName) < 0) {
			return -1;	// same firstName and middleName, smaller lastName
		} else {
			return 0; // same firstName, middleName and lastName
		} 
	}

	@Override 
	public int hashCode() { 
		final int prime = 31; 
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((middleName == null) ? 0 : middleName.hashCode()); 
		
		return result;	
	}
	
	@Override 
	public boolean equals(Object obj) { 
		if (this == obj) { 
			return true; 
		} 
		if (obj == null) {
			return false; 
		} 
		if (getClass() != obj.getClass()) { 
			return false; 
		}
		enrollee other = (enrollee) obj;
		if (firstName == null) {
			if (other.firstName != null) { 
				return false; 
			} 
		} else if (!firstName.equals(other.firstName)) { 
			return false; 
		} 
		
		if (middleName == null) {
			if (other.middleName != null) { 
				return false; 
			} 
		} else if (!middleName.equals(other.middleName)) { 
			return false; 
		} 		
		if (lastName == null) {
			if (other.lastName != null) { 
				return false; 
			} 
		} else if (!lastName.equals(other.lastName)) { 
			return false; 
		} 
		
		return true; 
	}
	
	@Override 
	public String toString() { 
		return String.format("%s %s %s %s %s %s",id, firstName, middleName, lastName, facultyName, address); 
	}
	
}