package by.bsuir.committee.entity;
import static by.bsuir.committee.Constants.*;

public class Address {
	private String city;
	private String street;
	private Integer homeNumber;
	 
	
	public Address() {
		
	}
	
	public Address(String[] Data)
	{
		if (Data.length < 3)
			throw new IllegalArgumentException(INCORRECT_PARAMS);
		
		setCity(Data[0]);
		setStreet(Data[1]);
		try {
			setHomeNumber(Integer.parseInt(Data[2]));
		}
		catch (Exception e) { 
            System.out.println("Illegal home number: " + e);
		}
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public Integer getHomeNumber() {
		return homeNumber;
	}
	public void setHomeNumber(Integer homeNumber) {
		this.homeNumber = homeNumber;
	}
	
	@Override 
	public String toString() { 
		return String.format("%s %s %s", city, street, homeNumber.toString()); 
	}
	
}
