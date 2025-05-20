package ru.zagrebin.laba6.dao;

import ru.zagrebin.laba6.model.Tenant;

import java.util.List;
import java.util.Optional;

public interface TenantDao {
    void add(Tenant tenant);
    void update(Tenant tenant);
    void delete(int id);
    Optional<Tenant> getById(int id);
    List<Tenant> getAll();
}