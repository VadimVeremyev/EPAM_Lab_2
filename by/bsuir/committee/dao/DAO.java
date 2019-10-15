package by.bsuir.committee.dao;

import java.util.List;

public interface DAO<T> {

    void delete(T obj);

    void add(T obj);

    T get(String id);  
    
    List<T> getAll(); 
    
    void addAll(List<T> list);
}
