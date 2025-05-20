package ru.zagrebin.laba6.service;

import ru.zagrebin.laba6.dao.AuditLogDao;
import ru.zagrebin.laba6.model.AuditLog;

import java.util.List;
import java.util.Optional;

public class AuditLogService {
    private final AuditLogDao auditLogDao;

    public AuditLogService(AuditLogDao auditLogDao) {
        this.auditLogDao = auditLogDao;
    }

    public void addLog(AuditLog log) {
        auditLogDao.add(log);
    }

    public Optional<AuditLog> getLogById(int id) {
        return auditLogDao.getById(id);
    }

    public List<AuditLog> getAllLogs() {
        return auditLogDao.getAll();
    }
}
