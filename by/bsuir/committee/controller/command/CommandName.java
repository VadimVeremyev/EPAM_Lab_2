package by.bsuir.committee.controller.command;

public enum CommandName {
    LOAD("load"),
    ADD("add"),
    REMOVE("remove"),
    EDIT("edit"),
    SAVE("save"),
    EXIT("exit"),
    SHOW("show"),
    SORT("sort"),
    HELP("help"),
    CONNECT("connect"),
    CREATE_TABLE("createTable"),
    WRONG_REQUEST("wrong_request")
    ;

    private String name;

    CommandName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
