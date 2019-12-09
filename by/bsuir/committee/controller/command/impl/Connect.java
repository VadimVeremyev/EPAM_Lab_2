 package by.bsuir.committee.controller.command.impl;

import by.bsuir.committee.controller.command.Command;
import by.bsuir.committee.entity.Committee;
import by.bsuir.committee.service.ServiceFactory;
import by.bsuir.committee.service.userService;

public class Connect implements Command {

	@Override
	public String execute(String request, Committee committee) {
	
		String response = null;
		String login, password;
		
		String[] data = new String[3];
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		userService userService = (userService) serviceFactory.getUserService();
		
		data = request.split(" ");

		if(data.length == 3) {
			login = data[1];
			password = data[2];
			
			if(userService.connect(login, password)) {
				response = "Connected.";
			}
			else {
				response = "Connection fail.";
			}
		}


		return response;
	}

}