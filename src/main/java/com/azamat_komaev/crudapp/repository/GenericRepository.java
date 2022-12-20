package com.azamat_komaev.crudapp.repository;

import java.util.List;

public interface GenericRepository<T, ID> {
    T getById(ID id);

    List<T> getAll();

    T save(T t);

    T update(T t);

    /**
     * Delete object by id. Deleted object is not removed from file
     * but changes its status to Status.DELETED.
     * @param id skill id should be deleted
     */
    void deleteById(ID id);
}

