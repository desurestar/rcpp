package ru.zagrebin.laba6.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseItem {
    private int itemId;
    private String name;
    private int warehouseId;
    private int quantity;
    private String itemType;
    private java.sql.Date expiryDate;
    private byte[] license;
}