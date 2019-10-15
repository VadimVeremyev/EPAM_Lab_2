package by.bsuir.committee.controller.command.impl;

import by.bsuir.committee.controller.command.Command;
import by.bsuir.committee.entity.committee;

public class WrongRequest implements Command {

	@Override
	public String execute(String request, committee committee) {
		return "No such command.";
	}

}
