package ru.zagrebin.laba6.dao;

import ru.zagrebin.laba6.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {
    void add(Employee employee);
    void update(Employee employee);
    void delete(int id);
    Optional<Employee> getById(int id);
    List<Employee> getAll();
}
