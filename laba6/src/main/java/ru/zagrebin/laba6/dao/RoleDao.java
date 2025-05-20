package ru.zagrebin.laba6.dao;

import ru.zagrebin.laba6.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleDao {
    void add(Role role);
    void update(Role role);
    void delete(int id);
    Optional<Role> getById(int id);
    List<Role> getAll();
}
