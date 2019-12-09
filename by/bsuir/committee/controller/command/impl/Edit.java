package by.bsuir.committee.controller.command.impl;

import by.bsuir.committee.controller.command.Command;
import by.bsuir.committee.entity.Committee;
import by.bsuir.committee.service.ServiceFactory;
import by.bsuir.committee.service.userService;
import static by.bsuir.committee.Constants.*;

public class Edit implements Command{

	@Override
	public String execute(String request, Committee committee) {
		int enrolleeID = 0;
		String response = "";
		
		String[] data = new String[3];
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		userService userService = (userService) serviceFactory.getUserService();
		
		data = request.split(" ");

		if(data.length == 2) {
			
			enrolleeID = Integer.parseInt(data[1]);
			
			if(!userService.edit(enrolleeID, committee))
				response = NO_ENROLLE;
		}
		else {
			response  = INCORRECT_PARAMS;
		}

		return response;
	}

}
