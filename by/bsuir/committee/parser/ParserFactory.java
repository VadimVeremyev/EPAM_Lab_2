package by.bsuir.committee.parser;

import by.bsuir.committee.entity.enrollee;

public class ParserFactory {
    private static ParserFactory ourInstance = new ParserFactory();

    public static ParserFactory getInstance() {

        return ourInstance;
    }

    private final FileParser<enrollee> userParser = UserFileParser.getInstance();

    public FileParser<enrollee> getUserParser() {

        return userParser;
    }

    private ParserFactory() {
    }
}
