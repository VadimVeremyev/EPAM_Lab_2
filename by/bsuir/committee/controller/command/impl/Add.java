 package by.bsuir.committee.controller.command.impl;

import by.bsuir.committee.controller.command.Command;
import by.bsuir.committee.entity.committee;
import by.bsuir.committee.service.ServiceFactory;
import by.bsuir.committee.service.userService;

public class Add implements Command {

	@Override
	public String execute(String request, committee committee) {
		String response;
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		userService userService = (userService) serviceFactory.getUserService();
		
		if(userService.add(committee)) {
			response = "Enrollee added.";
		}
		else {
			response = "Enrollee is not added.";
		}

		return response;
	}

}
