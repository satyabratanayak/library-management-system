package com.lms.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {
    Optional<T> findById(ID id);
    void save(T entity);
    void deleteById(ID id);
    List<T> findAll();
}
