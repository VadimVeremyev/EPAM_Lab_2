 package by.bsuir.committee.controller.command.impl;

import by.bsuir.committee.controller.command.Command;
import by.bsuir.committee.entity.Committee;
import by.bsuir.committee.service.ServiceFactory;
import by.bsuir.committee.service.userService;
import static by.bsuir.committee.Constants.*;

public class Add implements Command {

	@Override
	public String execute(String request, Committee committee) {
		String response;
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		userService userService = (userService) serviceFactory.getUserService();
		
		if(userService.add(committee)) {
			response = ENROLLE_ADDED;
		}
		else {
			response = ENROLLE_NOT_ADDED;
		}

		return response;
	}

}
