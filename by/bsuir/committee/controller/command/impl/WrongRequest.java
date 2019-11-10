package by.bsuir.committee.controller.command.impl;

import by.bsuir.committee.controller.command.Command;
import by.bsuir.committee.entity.Committee;

public class WrongRequest implements Command {

	@Override
	public String execute(String request, Committee committee) {
		return "No such command.";
	}

}
