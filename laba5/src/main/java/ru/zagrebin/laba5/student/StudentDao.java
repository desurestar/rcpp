package ru.zagrebin.laba5.student;

import ru.zagrebin.laba5.interfaces.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentDao implements Dao<Student, Long> {
    private static final String FIND_ALL = "select * from students";
    private static final String FIND_BY_LASTNAME = "select * from students where last_name = ?";
    private static final String FIND_BY_GROUP = "select * from students where group = ?";
    private static final String SAVE = "insert into students(first_name, last_name, middle_name, group_name, department, birth_day, city) values(?,?,?,?,?,?,?)";
    private static final String UPDATE = "update students set first_name = ?, last_name = ?, middle_name = ?, group_name = ?, department = ?, birth_day = ?, city = ? where id = ?";
    private static final String DELETE = "delete from students where id = ?";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/laba5_RCPP", "dimon2", "190485");
    }

    @Override
    public Student findById(Long id) {
        return findAll().stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Optional<Student> findOptionalById(Long id) {
        return findAll().stream().filter(s -> s.getId() == id).findFirst();
    }

    @Override
    public Collection<Student> findAll() {
        List<Student> list = new ArrayList<Student>();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(FIND_ALL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(map(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Student save(Student student) {
        try (Connection con = getConnection();
        PreparedStatement stmt = con.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setString(3, student.getMiddleName());
            stmt.setString(4, student.getGroup());
            stmt.setString(5, student.getDepartment());
            stmt.setDate(6, Date.valueOf(student.getBirthDate()));
            stmt.setString(7, student.getCity());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) student.setId(rs.getLong(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public Student update(Student student) {
        try (Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(UPDATE, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setString(3, student.getMiddleName());
            stmt.setString(4, student.getGroup());
            stmt.setString(5, student.getDepartment());
            stmt.setDate(6, Date.valueOf(student.getBirthDate()));
            stmt.setString(7, student.getCity());
            stmt.setLong(8, student.getId());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) student.setId(rs.getLong(1));
            } catch (SQLException e) {
                e.printStackTrace();
            }

        return student;
    }

    @Override
    public void delete(Student student) {
        deleteById(student.getId());
    }

    @Override
    public void deleteById(Long id) {
        try (Connection con = getConnection();
        PreparedStatement stmt = con.prepareStatement(DELETE)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Student> findByLastName(String lastName) {
        return findAll().stream()
                .filter(s -> s.getLastName().equals(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Student> findByGroup(String group) {
        return findAll().stream()
                .filter(s -> s.getGroup().equals(group))
                .collect(Collectors.toList());
    }

    private Student map(ResultSet rs) throws SQLException {
        return new Student(
                rs.getLong("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("middle_name"),
                rs.getString("group_name"),
                rs.getString("department"),
                rs.getString("birth_day"),
                rs.getString("city")
        );
    }
}
