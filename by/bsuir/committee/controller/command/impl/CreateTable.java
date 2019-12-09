 package by.bsuir.committee.controller.command.impl;

import by.bsuir.committee.controller.command.Command;
import by.bsuir.committee.entity.Committee;
import by.bsuir.committee.service.ServiceFactory;
import by.bsuir.committee.service.userService;

public class CreateTable implements Command {

	@Override
	public String execute(String request, Committee committee) {
	
		String response = null;
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		userService userService = (userService) serviceFactory.getUserService();
		

		if(userService.createTable()) {
			response = "Table created.";
		}
		else {
			response = "Table creation fail.";
		}
		
		return response;
	}

}
