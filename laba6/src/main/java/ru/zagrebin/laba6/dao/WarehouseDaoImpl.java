package ru.zagrebin.laba6.dao;

import ru.zagrebin.laba6.model.Warehouse;
import ru.zagrebin.laba6.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WarehouseDaoImpl implements WarehouseDao {

    private final String ADD = "insert into warehouse (warehousenumber, status, tenantid, associatedplaceid) values (?, ?, ?, ?)";
    private final String UPDATE = "update warehouse set warehousenumber = ?, status = ?, tenantid = ?, associatedplaceid = ? where warehouseid = ?";
    private final String DELETE = "delete from warehouse where warehouseid = ?";
    private final String GET_BY_ID = "select * from warehouse where warehouseid = ?";
    private final String GET_ALL = "select * from warehouse";

    @Override
    public void add(Warehouse warehouse) {
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(ADD)) {
            stmt.setInt(1, warehouse.getWarehouseNumber());
            stmt.setString(2, warehouse.getStatus());
            if (warehouse.getTenantId() != null) stmt.setInt(3, warehouse.getTenantId());
            else stmt.setNull(3, Types.INTEGER);
            if (warehouse.getAssociatedPlaceId() != null) stmt.setInt(4, warehouse.getAssociatedPlaceId());
            else stmt.setNull(4, Types.INTEGER);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Warehouse warehouse) {
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE)) {
            stmt.setInt(1, warehouse.getWarehouseNumber());
            stmt.setString(2, warehouse.getStatus());
            if (warehouse.getTenantId() != null) stmt.setInt(3, warehouse.getTenantId());
            else stmt.setNull(3, Types.INTEGER);
            if (warehouse.getAssociatedPlaceId() != null) stmt.setInt(4, warehouse.getAssociatedPlaceId());
            else stmt.setNull(4, Types.INTEGER);
            stmt.setInt(5, warehouse.getWarehouseId());
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
    public Optional<Warehouse> getById(int id) {
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
    public List<Warehouse> getAll() {
        List<Warehouse> warehouses = new ArrayList<>();
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_ALL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                warehouses.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return warehouses;
    }

    private Warehouse mapRow(ResultSet rs) throws SQLException {
        return new Warehouse(
                rs.getInt("warehouseid"),
                rs.getInt("warehousenumber"),
                rs.getString("status"),
                rs.getObject("tenantid") != null ? rs.getInt("tenantid") : null,
                rs.getObject("associatedplaceid") != null ? rs.getInt("associatedplaceid") : null
        );
    }
}
