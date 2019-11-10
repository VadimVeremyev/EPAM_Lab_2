package by.bsuir.committee.controller.command;

import by.bsuir.committee.entity.Committee;

public interface Command {
	public String execute(String request, Committee Committee);
}
