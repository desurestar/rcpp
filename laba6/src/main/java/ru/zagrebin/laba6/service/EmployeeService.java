package ru.zagrebin.laba6.service;

import ru.zagrebin.laba6.dao.EmployeeDao;
import ru.zagrebin.laba6.dao.EmployeeDaoImpl;
import ru.zagrebin.laba6.model.Employee;

import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private final EmployeeDao employeeDao = new EmployeeDaoImpl();

    public void addEmployee(Employee employee) {
        employeeDao.add(employee);
    }

    public void updateEmployee(Employee employee) {
        employeeDao.update(employee);
    }

    public void deleteEmployee(int id) {
        employeeDao.delete(id);
    }

    public Optional<Employee> getEmployeeById(int id) {
        return employeeDao.getById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeDao.getAll();
    }
}
