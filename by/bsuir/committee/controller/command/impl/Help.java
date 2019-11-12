package by.bsuir.committee.controller.command.impl;

import by.bsuir.committee.controller.command.Command;
import by.bsuir.committee.entity.Committee;

public class Help implements Command{

	@Override
	public String execute(String request, Committee committee) {
		return "Add - add new enrollee. Command format - add.\n"
				+ "Edit - edit enrollee. Command format - edit ID.\n"
				+ "Exit - close programm.\n"
				+ "Load - load committee from file. Command format - load filePath.\n"
				+ "Remove - remove enrollee. Command format - remove ID.\n"
				+ "Save - save committee in file. Command format - save fileName.\n"
				+ "Show - show enrollee list. Command format - show all|POIT|ITP.\n"
				+ "Sort - sort enrollee list. Command format - sort all|POIT|ITP.\n";
	}

}

