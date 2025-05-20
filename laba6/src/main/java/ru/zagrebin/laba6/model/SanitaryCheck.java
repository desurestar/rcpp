package ru.zagrebin.laba6.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanitaryCheck {
    private int checkId;
    private int itemId;
    private java.sql.Date checkDate;
    private String result;
    private String inspectorName;
}