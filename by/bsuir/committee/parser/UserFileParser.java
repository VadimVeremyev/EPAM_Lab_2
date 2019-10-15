package by.bsuir.committee.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import by.bsuir.committee.entity.address;
import by.bsuir.committee.entity.enrollee;


public class UserFileParser implements FileParser<enrollee> {

	private static UserFileParser ourInstance;


    static {
    	ourInstance = new UserFileParser();
    }
    
    private UserFileParser() {

    }
    
    public static UserFileParser getInstance() {

        return ourInstance;
    }

	
	@Override
	public List<enrollee> getData(String path) {
		List<enrollee> enrolleeList = new LinkedList<enrollee>();
		
		File file = new File(path);
		BufferedReader bufferedReader = null;
		String enrolleeNote = "";
		
		
		String[] dataEnrollee;
		String[] dataFML = new String[4];
		String[] dataAddr = new String[3];
		address addr;
		enrollee enrollee;
		
		try {
			if(!file.exists()) {
				file.createNewFile();
				System.out.println("File \"" + path  + "\" created.");
			}
			
			bufferedReader = new BufferedReader(new FileReader(file));
		
			while(bufferedReader.ready()) {
				enrolleeNote = bufferedReader.readLine();
				
				dataEnrollee = enrolleeNote.split(" ");
				
				if (dataEnrollee.length != 8)
					throw new IllegalArgumentException("Wrong number of parameters in file.");
				
				
				dataFML[0] = dataEnrollee[1];
				dataFML[1] = dataEnrollee[2];
				dataFML[2] = dataEnrollee[3];
				dataFML[3] = dataEnrollee[4];
				
				dataAddr[0] = dataEnrollee[5]; 
				dataAddr[1] = dataEnrollee[6];
				dataAddr[2] = dataEnrollee[7];
				
				addr = new address(dataAddr);
				
				enrollee = new enrollee(dataFML, addr);	
				enrolleeList.add(enrollee);
				
				
			}	
	
		}
		catch (IOException e) {
			System.out.println("Exception thrown: " + e);
		}

		try {
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return enrolleeList;
	}

}
