package org.example.trigquizzer.repo;

import java.util.List;

public interface Repository<E, ID> {

    void insert(E e);
    void delete(ID id);
    List<E> findAll();
}
