package by.bsuir.committee.controller.command;

import by.bsuir.committee.entity.committee;

public interface Command {
	public String execute(String request, committee committee);
}
