package ru.geekbrains.orm.mapper;

import ru.geekbrains.orm.User;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

public class UserMapper implements Mapper<User> {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/users";
    private static final String USERNAME = "dba";
    private static final String PASSWORD = "dbaPass";
    private static final UserMapper INSTANCE = new UserMapper();
    private final PreparedStatement selectUser;
    private final PreparedStatement insertUser;
    private final PreparedStatement updateUser;
    private final PreparedStatement deleteUser;
    private final Map<Long, User> identityMap;

    private UserMapper() {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, getProperties());
            selectUser = connection.prepareStatement("select id, username, password from users where id = ?");
            insertUser = connection.prepareStatement("INSERT INTO users VALUES (?, ?, ?)");
            updateUser = connection.prepareStatement("update users set username = ?, password = ? where id = ?");
            deleteUser = connection.prepareStatement("delete from users where id = ?");
            identityMap = new HashMap<>();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("user", USERNAME);
        properties.setProperty("password", PASSWORD);
        return properties;
    }

    public static UserMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<User> findById(long id) {
        User user = identityMap.get(id);
        if (user != null) {
            return Optional.of(user);
        }
        try {
            selectUser.setLong(1, id);
            ResultSet rs = selectUser.executeQuery();
            if (rs.next()) {
                user = new User(rs.getLong(1), rs.getString(2), rs.getString(3));
                identityMap.put(id, user);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.empty();
    }

    @Override
    public void update(User user) {
        if (user.getId() == null) return;
        Optional<User> existingUser = findById(user.getId());
        if (existingUser.isPresent()) {
            try {
                updateUser.setString(1, user.getLogin());
                updateUser.setString(2, user.getPassword());
                updateUser.setLong(3, user.getId());
                updateUser.execute();
                identityMap.put(user.getId(), new User(user.getId(), user.getLogin(), user.getPassword()));
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    @Override
    public void insert(User user) {
        if (user.getId() == null) return;
        Optional<User> existingUser = findById(user.getId());
        if (existingUser.isEmpty()) {
            try {
                insertUser.setLong(1, user.getId());
                insertUser.setString(2, user.getLogin());
                insertUser.setString(3, user.getPassword());
                insertUser.execute();
                identityMap.put(user.getId(), new User(user.getId(), user.getLogin(), user.getPassword()));
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    @Override
    public void delete(User user) {
        if (user.getId() == null) return;
        Optional<User> existingUser = findById(user.getId());
        if (existingUser.isPresent()) {
            try {
                deleteUser.setLong(1, user.getId());
                deleteUser.execute();
                identityMap.remove(user.getId());
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    @Override
    public Class<?> getDefaultObjectClass() {
        return User.class;
    }
}
