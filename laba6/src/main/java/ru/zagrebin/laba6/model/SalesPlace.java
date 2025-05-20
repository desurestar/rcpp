package ru.zagrebin.laba6.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesPlace {
    private int placeId;
    private String placeNumber;
    private String status;
    private Integer tenantId;
    private java.sql.Date rentalEndDate;
}
