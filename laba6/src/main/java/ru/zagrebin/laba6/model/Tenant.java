package ru.zagrebin.laba6.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tenant {
    private int tenantId;
    private String fullName;
    private Integer userId;
    private String passportSeries;
    private String passportNumber;
    private String email;
}
