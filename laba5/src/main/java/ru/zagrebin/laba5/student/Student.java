package ru.zagrebin.laba5.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String group;
    private String department;
    private String birthDate;
    private String city;
}
