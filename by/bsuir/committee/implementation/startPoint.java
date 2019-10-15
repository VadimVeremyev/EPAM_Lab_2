package by.bsuir.committee.implementation;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

import by.bsuir.committee.controller.Controller;
import by.bsuir.committee.entity.committee;


public class startPoint {
	
	private static final String ENTER_COMMAND = "Enter command: ";

	public static void main(String[] args)
	{
		Controller controller = new Controller();
		String request = null;
		
		committee committee = null;
		
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter fileName for load committee: ");
        try {
        	request = r.readLine();
        	committee = new committee(request);
        	System.out.println(controller.executeTask("load " + request, committee));
        	
        	
        	while(!request.equals("exit"))
        	{
        		System.out.print(ENTER_COMMAND);
				
				request = r.readLine();
	
				System.out.println(controller.executeTask(request, committee));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
