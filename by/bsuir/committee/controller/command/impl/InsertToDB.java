package by.bsuir.committee.controller.command.impl;

import by.bsuir.committee.controller.command.Command;
import by.bsuir.committee.entity.Committee;
import by.bsuir.committee.service.ServiceFactory;
import by.bsuir.committee.service.userService;

public class InsertToDB implements Command{

	@Override
	public String execute(String request, Committee committee) {
		String response = "";
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		userService userService = (userService) serviceFactory.getUserService();
				
		if(!userService.insert(committee)) {
			response = "Data entered into the database.";
		}
		else {
			response  = "Insert fail.";
		}

		return response;
	}


}
