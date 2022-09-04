package ru.geekbrains.orm.repository;

import java.util.Optional;

public interface Repository<T> {
    Optional<T> findById(long id);

    void insert(T object);

    void update(T object);

    void delete(T object);

    void commitTransaction();

    void beginTransaction();

    void rollbackTransaction();
}
