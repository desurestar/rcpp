package ru.zagrebin.laba6.dao;

import ru.zagrebin.laba6.model.Employee;
import ru.zagrebin.laba6.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDaoImpl implements EmployeeDao {
    private final String ADD = "insert into employees (fullname, position, passportseries, passportnumber, email, photo) values(?,?,?,?,?,?)";
    private final String UPDATE = "update emplouees set fullname = ?, position = ?, passportseries = ?, passportnumber = ?, email = ?, photo = ? where id = ?";
    private final String DELETE = "delete from employees where id = ?";
    private final String GET_BY_ID = "select * from employees where id = ?";
    private final String GET_ALL = "select * from employees";

    @Override
    public void add(Employee employee) {
        try (Connection con = DatabaseUtil.getConnection(); PreparedStatement stmt = con.prepareStatement(ADD)) {
            stmt.setString(1, employee.getFullName());
            stmt.setString(2, employee.getPosition());
            stmt.setString(3, employee.getPassportSeries());
            stmt.setString(4, employee.getPassportNumber());
            stmt.setString(5, employee.getEmail());
            stmt.setBytes(6, employee.getPhoto());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Employee employee) {
        try (Connection con = DatabaseUtil.getConnection();
        PreparedStatement stmt = con.prepareStatement(UPDATE)) {
            stmt.setString(1, employee.getFullName());
            stmt.setString(2, employee.getPosition());
            stmt.setString(3, employee.getPassportSeries());
            stmt.setString(4, employee.getPassportNumber());
            stmt.setString(5, employee.getEmail());
            stmt.setBytes(6, employee.getPhoto());
            stmt.setInt(7, employee.getEmployeeId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection con = DatabaseUtil.getConnection();
        PreparedStatement stmt = con.prepareStatement(DELETE)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Employee> getById(int id) {
        try (Connection con = DatabaseUtil.getConnection();
        PreparedStatement stmt = con.prepareStatement(GET_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapRow(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        try (Connection con = DatabaseUtil.getConnection();
        PreparedStatement stmt = con.prepareStatement(GET_ALL);
        ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                employees.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private Employee mapRow(ResultSet rs) throws SQLException {
        return new Employee(
                rs.getInt("employeeid"),
                rs.getString("fullname"),
                rs.getString("position"),
                rs.getString("passportseries"),
                rs.getString("passportnumber"),
                rs.getString("email"),
                rs.getBytes("photo")
        );
    }
}
