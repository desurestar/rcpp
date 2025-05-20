package ru.zagrebin.laba6.dao;

import ru.zagrebin.laba6.model.AuditLog;

import java.util.List;
import java.util.Optional;

public interface AuditLogDao {
    void add(AuditLog log);
    Optional<AuditLog> getById(int id);
    List<AuditLog> getAll();
}
