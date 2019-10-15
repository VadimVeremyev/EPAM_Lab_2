package by.bsuir.committee.service;

import by.bsuir.committee.entity.committee;

public interface Service<T> {

	boolean add(committee committee);

    T get(int id, committee committee);

    boolean edit(int id, committee committee);

    boolean remove(int id, committee committee);

}
