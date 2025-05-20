package ru.zagrebin.laba6.dao;

import ru.zagrebin.laba6.model.Equipment;

import java.util.List;
import java.util.Optional;

public interface EquipmentDao {
    void add(Equipment equipment);
    void update(Equipment equipment);
    void delete(int id);
    Optional<Equipment> getById(int id);
    List<Equipment> getAll();
}
