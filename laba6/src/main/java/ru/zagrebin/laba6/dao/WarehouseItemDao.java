package ru.zagrebin.laba6.dao;

import ru.zagrebin.laba6.model.WarehouseItem;

import java.util.List;
import java.util.Optional;

public interface WarehouseItemDao {
    void add(WarehouseItem item);
    void update(WarehouseItem item);
    void delete(int id);
    Optional<WarehouseItem> getById(int id);
    List<WarehouseItem> getAll();
}