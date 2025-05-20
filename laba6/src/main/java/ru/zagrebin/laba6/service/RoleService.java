package ru.zagrebin.laba6.service;

import ru.zagrebin.laba6.dao.RoleDao;
import ru.zagrebin.laba6.model.Role;

import java.util.List;
import java.util.Optional;

public class RoleService {
    private final RoleDao roleDao;

    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public void addRole(Role role) {
        roleDao.add(role);
    }

    public void updateRole(Role role) {
        roleDao.update(role);
    }

    public void deleteRole(int id) {
        roleDao.delete(id);
    }

    public Optional<Role> getRoleById(int id) {
        return roleDao.getById(id);
    }

    public List<Role> getAllRoles() {
        return roleDao.getAll();
    }
}
