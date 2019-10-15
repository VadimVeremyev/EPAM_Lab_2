package by.bsuir.committee.controller.command.impl;

import by.bsuir.committee.controller.command.Command;
import by.bsuir.committee.entity.committee;
import by.bsuir.committee.service.ServiceFactory;
import by.bsuir.committee.service.userService;

public class Edit implements Command{

	@Override
	public String execute(String request, committee committee) {
		int enrolleeID = 0;
		String response = "";
		
		String[] data = new String[3];
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		userService usertService = (userService) serviceFactory.getUserService();
		
		data = request.split(" ");

		if(data.length == 2) {
			
			enrolleeID = Integer.parseInt(data[1]);
			
			if(!usertService.edit(enrolleeID, committee))
				response = "No such enrollee.";
		}
		else {
			response  = "Incorrect number of parameters.";
		}

		return response;
	}

}
