package ru.geekbrains.orm;

import ru.geekbrains.orm.mapper.Mapper;
import ru.geekbrains.orm.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;

public class MapperRegistry {
    private static final List<Mapper<?>> mappers = new ArrayList<>();

    private MapperRegistry() {
    }

    static {
        mappers.add(UserMapper.getInstance());
    }

    public static Mapper<?> getMapper(Class<?> objectClass) {
        for (Mapper<?> mapper : mappers) {
            if (mapper.getDefaultObjectClass().equals(objectClass)) return mapper;
        }
        return null;
    }
}
