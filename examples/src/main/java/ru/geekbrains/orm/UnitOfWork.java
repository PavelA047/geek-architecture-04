package ru.geekbrains.orm;

import ru.geekbrains.orm.mapper.Mapper;

import java.util.ArrayList;
import java.util.List;

public class UnitOfWork<T> {

    private final List<T> newObjects = new ArrayList<>();
    private final List<T> updateObjects = new ArrayList<>();
    private final List<T> deleteObjects = new ArrayList<>();
    private T defaultObject = null;
    private Mapper<T> mapper = null;

    public void registerNew(T object) {
        this.newObjects.add(object);
        tryToInitializeMapper(object);
    }

    public void registerUpdate(T object) {
        this.updateObjects.add(object);
        tryToInitializeMapper(object);
    }

    public void registerDelete(T object) {
        this.deleteObjects.add(object);
        tryToInitializeMapper(object);
    }

    private void tryToInitializeMapper(T object) {
        if (this.defaultObject == null) {
            this.defaultObject = object;
            mapper = (Mapper<T>) MapperRegistry.getMapper(defaultObject.getClass());
        }
    }

    public void commit() {
        insertNew();
        updateChanged();
        deleteRemoved();
    }

    private void insertNew() {
        if (mapper != null) {
            for (T object : newObjects) {
                mapper.insert(object);
            }
        }
    }

    private void updateChanged() {
        if (mapper != null) {
            for (T object : updateObjects) {
                mapper.update(object);
            }
        }
    }

    private void deleteRemoved() {
        if (mapper != null) {
            for (T object : deleteObjects) {
                mapper.delete(object);
            }
        }
    }
}
