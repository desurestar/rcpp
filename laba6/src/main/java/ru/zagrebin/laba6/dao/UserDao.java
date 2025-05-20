package ru.zagrebin.laba6.dao;

import ru.zagrebin.laba6.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    void add(User user);
    void update(User user);
    void delete(int id);
    Optional<User> getById(int id);
    List<User> getAll();
}