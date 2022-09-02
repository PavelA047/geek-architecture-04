package ru.geekbrains.orm.repository;

import ru.geekbrains.orm.UnitOfWork;
import ru.geekbrains.orm.User;
import ru.geekbrains.orm.mapper.UserMapper;

import java.util.Optional;

public class UserRepository implements Repository<User> {

    private final UnitOfWork<User> userUnitOfWork = new UnitOfWork<>();

    @Override
    public Optional<User> findById(long id) {
        return UserMapper.getInstance().findById(id);
    }

    @Override
    public void insert(User object) {
        userUnitOfWork.registerNew(object);
    }

    @Override
    public void update(User object) {
        userUnitOfWork.registerUpdate(object);
    }

    @Override
    public void delete(User object) {
        userUnitOfWork.registerDelete(object);
    }

    @Override
    public void commitTransaction() {
        userUnitOfWork.commit();
    }

    @Override
    public void beginTransaction() {
        System.out.println("begin");
    }

    @Override
    public void rollbackTransaction() {
        System.out.println("rollBack");
    }
}
