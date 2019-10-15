package by.bsuir.committee.parser;

import java.util.List;

public interface FileParser<T> {
    List<T> getData(String path);
}
