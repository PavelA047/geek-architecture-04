package ru.geekbrains.orm;

import ru.geekbrains.orm.repository.Repository;
import ru.geekbrains.orm.repository.UserRepository;

public class Main {
    public static void main(String[] args) {
        Repository<User> userRepository = new UserRepository();

        System.out.println(userRepository.findById(2));

        userRepository.beginTransaction();
        userRepository.insert(new User(4L, "jack", "123"));
        userRepository.update(new User(2L, "sam", "123"));
        userRepository.update(new User(1L, "john", "123"));
        userRepository.insert(new User(5L, "alex", "123"));
        userRepository.delete(new User(4L, "jack", "123"));
        userRepository.commitTransaction();
        userRepository.rollbackTransaction();
    }
}
