package ru.zagrebin.laba6.dao;

import ru.zagrebin.laba6.model.SalesPlace;
import ru.zagrebin.laba6.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SalesPlaceDaoImpl implements SalesPlaceDao {

    private final String ADD = "insert into salesplaces (placenumber, status, tenantid, rentalenddate) values (?, ?, ?, ?)";
    private final String UPDATE = "update salesplaces set placenumber = ?, status = ?, tenantid = ?, rentalenddate = ? where placeid = ?";
    private final String DELETE = "delete from salesplaces where placeid = ?";
    private final String GET_BY_ID = "select * from salesplaces where placeid = ?";
    private final String GET_ALL = "select * from salesplaces";

    @Override
    public void add(SalesPlace place) {
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(ADD)) {
            stmt.setString(1, place.getPlaceNumber());
            stmt.setString(2, place.getStatus());
            if (place.getTenantId() != null) stmt.setInt(3, place.getTenantId());
            else stmt.setNull(3, Types.INTEGER);
            stmt.setDate(4, place.getRentalEndDate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(SalesPlace place) {
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE)) {
            stmt.setString(1, place.getPlaceNumber());
            stmt.setString(2, place.getStatus());
            if (place.getTenantId() != null) stmt.setInt(3, place.getTenantId());
            else stmt.setNull(3, Types.INTEGER);
            stmt.setDate(4, place.getRentalEndDate());
            stmt.setInt(5, place.getPlaceId());
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
    public Optional<SalesPlace> getById(int id) {
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
    public List<SalesPlace> getAll() {
        List<SalesPlace> places = new ArrayList<>();
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_ALL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                places.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return places;
    }

    private SalesPlace mapRow(ResultSet rs) throws SQLException {
        return new SalesPlace(
                rs.getInt("placeid"),
                rs.getString("placenumber"),
                rs.getString("status"),
                rs.getObject("tenantid") != null ? rs.getInt("tenantid") : null,
                rs.getDate("rentalenddate")
        );
    }
}
