package ru.zagrebin.laba6.dao;

import ru.zagrebin.laba6.model.SanitaryCheck;
import ru.zagrebin.laba6.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SanitaryCheckDaoImpl implements SanitaryCheckDao {

    private final String ADD = "insert into sanitarycheck (itemid, checkdate, result, inspectorname) values (?, ?, ?, ?)";
    private final String UPDATE = "update sanitarycheck set itemid = ?, checkdate = ?, result = ?, inspectorname = ? where checkid = ?";
    private final String DELETE = "delete from sanitarycheck where checkid = ?";
    private final String GET_BY_ID = "select * from sanitarycheck where checkid = ?";
    private final String GET_ALL = "select * from sanitarycheck";

    @Override
    public void add(SanitaryCheck check) {
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(ADD)) {
            stmt.setInt(1, check.getItemId());
            stmt.setDate(2, check.getCheckDate());
            stmt.setString(3, check.getResult());
            stmt.setString(4, check.getInspectorName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(SanitaryCheck check) {
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE)) {
            stmt.setInt(1, check.getItemId());
            stmt.setDate(2, check.getCheckDate());
            stmt.setString(3, check.getResult());
            stmt.setString(4, check.getInspectorName());
            stmt.setInt(5, check.getCheckId());
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
    public Optional<SanitaryCheck> getById(int id) {
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
    public List<SanitaryCheck> getAll() {
        List<SanitaryCheck> checks = new ArrayList<>();
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_ALL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                checks.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return checks;
    }

    private SanitaryCheck mapRow(ResultSet rs) throws SQLException {
        return new SanitaryCheck(
                rs.getInt("checkid"),
                rs.getInt("itemid"),
                rs.getDate("checkdate"),
                rs.getString("result"),
                rs.getString("inspectorname")
        );
    }
}
