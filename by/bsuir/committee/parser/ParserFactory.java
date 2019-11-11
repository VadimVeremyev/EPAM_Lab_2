package by.bsuir.committee.parser;

import by.bsuir.committee.entity.Enrollee;

public class ParserFactory {
    private static ParserFactory ourInstance = new ParserFactory();

    public static ParserFactory getInstance() {

        return ourInstance;
    }

    private final XMLParser<Enrollee> userParser = UserXMLParser.getInstance();

    public XMLParser<Enrollee> getUserParser() {

        return userParser;
    }

    private ParserFactory() {
    }
}
