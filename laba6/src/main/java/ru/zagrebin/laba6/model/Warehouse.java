package ru.zagrebin.laba6.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {
    private int warehouseId;
    private int warehouseNumber;
    private String status;
    private Integer tenantId;
    private Integer associatedPlaceId;
}