package by.bsuir.committee.controller.command.impl;

import by.bsuir.committee.controller.command.Command;
import by.bsuir.committee.entity.Committee;
import by.bsuir.committee.service.ServiceFactory;
import by.bsuir.committee.service.userService;
import static by.bsuir.committee.Constants.*;

public class Save implements Command{

	@Override
	public String execute(String request, Committee committee) {
		
		String fileName = null;
		String response = null;
		
		String[] data = new String[3];
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		userService usertService = (userService) serviceFactory.getUserService();
		
		data = request.split(" ");
		
		if(data.length == 2) {
			fileName = data[1];
			usertService.save(fileName);
			response = "Committee saved in file \"" + fileName + ".";
		}
		else {
			response  = INCORRECT_PARAMS;
		}

		return response;
	}

}
