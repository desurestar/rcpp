package ru.zagrebin.laba6.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {
    private int equipmentId;
    private String name;
    private String status;
    private Integer tenantId;
    private Integer associatedPlaceId;
}