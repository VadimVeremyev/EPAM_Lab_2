package by.bsuir.committee.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import by.bsuir.committee.controller.Controller;
import by.bsuir.committee.entity.Committee;
import static by.bsuir.committee.Constants.*;


public class startPoint {
	
	
	
	public static void main(String[] args)
	{
		Controller controller = new Controller();
		String request = null;
		
		Committee committee = null;
		
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(ENTER_LOADPATH);
        try {
        	request = r.readLine();
        	committee = new Committee(request);
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
