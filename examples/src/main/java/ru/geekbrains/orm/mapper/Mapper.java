package ru.geekbrains.orm.mapper;

import java.util.Optional;

public interface Mapper<T> {

    Optional<T> findById(long id);

    Class<?> getDefaultObjectClass();

    void update(T o);

    void insert(T o);

    void delete(T o);
}
