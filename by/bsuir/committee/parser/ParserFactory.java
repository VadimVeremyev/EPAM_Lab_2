package by.bsuir.committee.parser;

import by.bsuir.committee.entity.Enrollee;

public class ParserFactory {
    private static ParserFactory ourInstance = new ParserFactory();

    public static ParserFactory getInstance() {

        return ourInstance;
    }

    private final FileParser<Enrollee> userParser = UserFileParser.getInstance();

    public FileParser<Enrollee> getUserParser() {

        return userParser;
    }

    private ParserFactory() {
    }
}
