package ru.zagrebin.laba6.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private int employeeId;
    private String fullName;
    private String position;
    private String passportSeries;
    private String passportNumber;
    private String email;
    private byte[] photo;
}
