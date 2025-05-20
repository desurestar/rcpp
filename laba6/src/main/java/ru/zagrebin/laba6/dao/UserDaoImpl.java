package ru.zagrebin.laba6.dao;

import ru.zagrebin.laba6.model.User;
import ru.zagrebin.laba6.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private final String ADD = "insert into users (username, passwordhash, roleid, tenantid) values (?, ?, ?, ?)";
    private final String UPDATE = "update users set username = ?, passwordhash = ?, roleid = ?, tenantid = ? where userid = ?";
    private final String DELETE = "delete from users where userid = ?";
    private final String GET_BY_ID = "select * from users where userid = ?";
    private final String GET_ALL = "select * from users";

    @Override
    public void add(User user) {
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(ADD)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPasswordHash());
            if (user.getRoleId() != null) stmt.setInt(3, user.getRoleId());
            else stmt.setNull(3, Types.INTEGER);
            if (user.getTenantId() != null) stmt.setInt(4, user.getTenantId());
            else stmt.setNull(4, Types.INTEGER);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPasswordHash());
            if (user.getRoleId() != null) stmt.setInt(3, user.getRoleId());
            else stmt.setNull(3, Types.INTEGER);
            if (user.getTenantId() != null) stmt.setInt(4, user.getTenantId());
            else stmt.setNull(4, Types.INTEGER);
            stmt.setInt(5, user.getUserId());
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
    public Optional<User> getById(int id) {
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
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_ALL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                users.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private User mapRow(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("userid"),
                rs.getString("username"),
                rs.getString("passwordhash"),
                rs.getObject("roleid") != null ? rs.getInt("roleid") : null,
                rs.getObject("tenantid") != null ? rs.getInt("tenantid") : null
        );
    }
}