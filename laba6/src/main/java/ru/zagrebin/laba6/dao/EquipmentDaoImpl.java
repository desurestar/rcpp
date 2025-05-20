package ru.zagrebin.laba6.dao;

import ru.zagrebin.laba6.model.Equipment;
import ru.zagrebin.laba6.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EquipmentDaoImpl implements EquipmentDao {

    private final String ADD = "insert into equipment (name, status, tenantid, associatedplaceid) values (?, ?, ?, ?)";
    private final String UPDATE = "update equipment set name = ?, status = ?, tenantid = ?, associatedplaceid = ? where equipmentid = ?";
    private final String DELETE = "delete from equipment where equipmentid = ?";
    private final String GET_BY_ID = "select * from equipment where equipmentid = ?";
    private final String GET_ALL = "select * from equipment";

    @Override
    public void add(Equipment equipment) {
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(ADD)) {
            stmt.setString(1, equipment.getName());
            stmt.setString(2, equipment.getStatus());
            if (equipment.getTenantId() != null) stmt.setInt(3, equipment.getTenantId());
            else stmt.setNull(3, Types.INTEGER);
            if (equipment.getAssociatedPlaceId() != null) stmt.setInt(4, equipment.getAssociatedPlaceId());
            else stmt.setNull(4, Types.INTEGER);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Equipment equipment) {
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE)) {
            stmt.setString(1, equipment.getName());
            stmt.setString(2, equipment.getStatus());
            if (equipment.getTenantId() != null) stmt.setInt(3, equipment.getTenantId());
            else stmt.setNull(3, Types.INTEGER);
            if (equipment.getAssociatedPlaceId() != null) stmt.setInt(4, equipment.getAssociatedPlaceId());
            else stmt.setNull(4, Types.INTEGER);
            stmt.setInt(5, equipment.getEquipmentId());
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
    public Optional<Equipment> getById(int id) {
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
    public List<Equipment> getAll() {
        List<Equipment> equipmentList = new ArrayList<>();
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_ALL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                equipmentList.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipmentList;
    }

    private Equipment mapRow(ResultSet rs) throws SQLException {
        return new Equipment(
                rs.getInt("equipmentid"),
                rs.getString("name"),
                rs.getString("status"),
                rs.getObject("tenantid") != null ? rs.getInt("tenantid") : null,
                rs.getObject("associatedplaceid") != null ? rs.getInt("associatedplaceid") : null
        );
    }
}
