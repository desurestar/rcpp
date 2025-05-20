package ru.zagrebin.laba6.service;

import ru.zagrebin.laba6.dao.WarehouseDao;
import ru.zagrebin.laba6.model.Warehouse;

import java.util.List;
import java.util.Optional;

public class WarehouseService {
    private final WarehouseDao warehouseDao;

    public WarehouseService(WarehouseDao warehouseDao) {
        this.warehouseDao = warehouseDao;
    }

    public void addWarehouse(Warehouse warehouse) {
        warehouseDao.add(warehouse);
    }

    public void updateWarehouse(Warehouse warehouse) {
        warehouseDao.update(warehouse);
    }

    public void deleteWarehouse(int id) {
        warehouseDao.delete(id);
    }

    public Optional<Warehouse> getWarehouseById(int id) {
        return warehouseDao.getById(id);
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseDao.getAll();
    }
}
