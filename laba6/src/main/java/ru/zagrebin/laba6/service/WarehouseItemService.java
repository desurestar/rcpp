package ru.zagrebin.laba6.service;

import ru.zagrebin.laba6.dao.WarehouseItemDao;
import ru.zagrebin.laba6.model.WarehouseItem;

import java.util.List;
import java.util.Optional;

public class WarehouseItemService {
    private final WarehouseItemDao itemDao;

    public WarehouseItemService(WarehouseItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public void addItem(WarehouseItem item) {
        itemDao.add(item);
    }

    public void updateItem(WarehouseItem item) {
        itemDao.update(item);
    }

    public void deleteItem(int id) {
        itemDao.delete(id);
    }

    public Optional<WarehouseItem> getItemById(int id) {
        return itemDao.getById(id);
    }

    public List<WarehouseItem> getAllItems() {
        return itemDao.getAll();
    }
}
