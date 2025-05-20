package ru.zagrebin.laba6.dao;

import ru.zagrebin.laba6.model.Tenant;
import ru.zagrebin.laba6.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TenantDaoImpl implements TenantDao {

    private final String ADD = "insert into tenants (fullname, userid, passportseries, passportnumber, email) values (?, ?, ?, ?, ?)";
    private final String UPDATE = "update tenants set fullname = ?, userid = ?, passportseries = ?, passportnumber = ?, email = ? where tenantid = ?";
    private final String DELETE = "delete from tenants where tenantid = ?";
    private final String GET_BY_ID = "select * from tenants where tenantid = ?";
    private final String GET_ALL = "select * from tenants";

    @Override
    public void add(Tenant tenant) {
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(ADD)) {
            stmt.setString(1, tenant.getFullName());
            if (tenant.getUserId() != null) stmt.setInt(2, tenant.getUserId());
            else stmt.setNull(2, Types.INTEGER);
            stmt.setString(3, tenant.getPassportSeries());
            stmt.setString(4, tenant.getPassportNumber());
            stmt.setString(5, tenant.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Tenant tenant) {
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE)) {
            stmt.setString(1, tenant.getFullName());
            if (tenant.getUserId() != null) stmt.setInt(2, tenant.getUserId());
            else stmt.setNull(2, Types.INTEGER);
            stmt.setString(3, tenant.getPassportSeries());
            stmt.setString(4, tenant.getPassportNumber());
            stmt.setString(5, tenant.getEmail());
            stmt.setInt(6, tenant.getTenantId());
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
    public Optional<Tenant> getById(int id) {
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
    public List<Tenant> getAll() {
        List<Tenant> tenants = new ArrayList<>();
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_ALL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                tenants.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tenants;
    }

    private Tenant mapRow(ResultSet rs) throws SQLException {
        return new Tenant(
                rs.getInt("tenantid"),
                rs.getString("fullname"),
                rs.getObject("userid") != null ? rs.getInt("userid") : null,
                rs.getString("passportseries"),
                rs.getString("passportnumber"),
                rs.getString("email")
        );
    }
}