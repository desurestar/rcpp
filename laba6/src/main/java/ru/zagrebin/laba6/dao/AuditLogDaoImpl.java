package ru.zagrebin.laba6.dao;

import ru.zagrebin.laba6.model.AuditLog;
import ru.zagrebin.laba6.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuditLogDaoImpl implements AuditLogDao {

    private final String ADD = "insert into audit_log (table_name, operation_type, record_id, old_data, new_data, changed_by) values (?, ?, ?, ?::jsonb, ?::jsonb, ?)";
    private final String GET_BY_ID = "select * from audit_log where audit_id = ?";
    private final String GET_ALL = "select * from audit_log";

    @Override
    public void add(AuditLog log) {
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(ADD)) {
            stmt.setString(1, log.getTableName());
            stmt.setString(2, log.getOperationType());
            stmt.setString(3, log.getRecordId());
            stmt.setString(4, log.getOldData());
            stmt.setString(5, log.getNewData());
            stmt.setString(6, log.getChangedBy());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<AuditLog> getById(int id) {
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
    public List<AuditLog> getAll() {
        List<AuditLog> logs = new ArrayList<>();
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_ALL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                logs.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logs;
    }

    private AuditLog mapRow(ResultSet rs) throws SQLException {
        return new AuditLog(
                rs.getInt("audit_id"),
                rs.getString("table_name"),
                rs.getString("operation_type"),
                rs.getString("record_id"),
                rs.getString("old_data"),
                rs.getString("new_data"),
                rs.getTimestamp("changed_at"),
                rs.getString("changed_by")
        );
    }
}