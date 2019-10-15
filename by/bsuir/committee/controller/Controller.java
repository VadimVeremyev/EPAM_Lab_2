package by.bsuir.committee.controller;
import by.bsuir.committee.controller.command.Command;
import by.bsuir.committee.entity.committee;


public final class Controller {
	private final CommandProvider provider = new CommandProvider();
	
	private final String paramDelimeter = " ";
	
	public String executeTask(String request, committee committee){
		String commandName;
		Command executionCommand;
		
		int delimeterIndex = request.indexOf(paramDelimeter);
	
		if(request.indexOf(paramDelimeter) != -1)
			commandName = request.substring(0, delimeterIndex);
		else
			commandName = request;
		
		executionCommand = provider.getCommand(commandName);
		String response;
		response = executionCommand.execute(request, committee);
		
		return response;
	}

}
