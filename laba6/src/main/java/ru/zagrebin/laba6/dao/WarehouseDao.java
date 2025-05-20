package ru.zagrebin.laba6.dao;

import ru.zagrebin.laba6.model.Warehouse;

import java.util.List;
import java.util.Optional;

public interface WarehouseDao {
    void add(Warehouse warehouse);
    void update(Warehouse warehouse);
    void delete(int id);
    Optional<Warehouse> getById(int id);
    List<Warehouse> getAll();
}
