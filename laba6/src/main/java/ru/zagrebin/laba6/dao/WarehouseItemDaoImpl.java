package ru.zagrebin.laba6.dao;

import ru.zagrebin.laba6.model.WarehouseItem;
import ru.zagrebin.laba6.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WarehouseItemDaoImpl implements WarehouseItemDao {

    private final String ADD = "insert into warehouseitems (name, warehouseid, quantity, itemtype, expirydate, license) values (?, ?, ?, ?, ?, ?)";
    private final String UPDATE = "update warehouseitems set name = ?, warehouseid = ?, quantity = ?, itemtype = ?, expirydate = ?, license = ? where itemid = ?";
    private final String DELETE = "delete from warehouseitems where itemid = ?";
    private final String GET_BY_ID = "select * from warehouseitems where itemid = ?";
    private final String GET_ALL = "select * from warehouseitems";

    @Override
    public void add(WarehouseItem item) {
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(ADD)) {
            stmt.setString(1, item.getName());
            stmt.setInt(2, item.getWarehouseId());
            stmt.setInt(3, item.getQuantity());
            stmt.setString(4, item.getItemType());
            stmt.setDate(5, item.getExpiryDate());
            stmt.setBytes(6, item.getLicense());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(WarehouseItem item) {
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE)) {
            stmt.setString(1, item.getName());
            stmt.setInt(2, item.getWarehouseId());
            stmt.setInt(3, item.getQuantity());
            stmt.setString(4, item.getItemType());
            stmt.setDate(5, item.getExpiryDate());
            stmt.setBytes(6, item.getLicense());
            stmt.setInt(7, item.getItemId());
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
    public Optional<WarehouseItem> getById(int id) {
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
    public List<WarehouseItem> getAll() {
        List<WarehouseItem> items = new ArrayList<>();
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_ALL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                items.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    private WarehouseItem mapRow(ResultSet rs) throws SQLException {
        return new WarehouseItem(
                rs.getInt("itemid"),
                rs.getString("name"),
                rs.getInt("warehouseid"),
                rs.getInt("quantity"),
                rs.getString("itemtype"),
                rs.getDate("expirydate"),
                rs.getBytes("license")
        );
    }
}
