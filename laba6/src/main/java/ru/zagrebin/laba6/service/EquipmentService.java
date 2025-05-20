package ru.zagrebin.laba6.service;

import ru.zagrebin.laba6.dao.EquipmentDao;
import ru.zagrebin.laba6.model.Equipment;

import java.util.List;
import java.util.Optional;

public class EquipmentService {
    private final EquipmentDao equipmentDao;

    public EquipmentService(EquipmentDao equipmentDao) {
        this.equipmentDao = equipmentDao;
    }

    public void addEquipment(Equipment equipment) {
        equipmentDao.add(equipment);
    }

    public void updateEquipment(Equipment equipment) {
        equipmentDao.update(equipment);
    }

    public void deleteEquipment(int id) {
        equipmentDao.delete(id);
    }

    public Optional<Equipment> getEquipmentById(int id) {
        return equipmentDao.getById(id);
    }

    public List<Equipment> getAllEquipment() {
        return equipmentDao.getAll();
    }
}
