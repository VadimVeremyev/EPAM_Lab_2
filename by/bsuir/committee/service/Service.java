package by.bsuir.committee.service;

import by.bsuir.committee.entity.Committee;

public interface Service<T> {

	boolean add(Committee committee);

    T get(int id, Committee committee);

    boolean edit(int id, Committee committee);

    boolean remove(int id, Committee committee);

}
