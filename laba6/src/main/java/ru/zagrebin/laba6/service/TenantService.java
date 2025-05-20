package ru.zagrebin.laba6.service;

import ru.zagrebin.laba6.dao.TenantDao;
import ru.zagrebin.laba6.model.Tenant;

import java.util.List;
import java.util.Optional;

public class TenantService {
    private final TenantDao tenantDao;

    public TenantService(TenantDao tenantDao) {
        this.tenantDao = tenantDao;
    }

    public void addTenant(Tenant tenant) {
        tenantDao.add(tenant);
    }

    public void updateTenant(Tenant tenant) {
        tenantDao.update(tenant);
    }

    public void deleteTenant(int id) {
        tenantDao.delete(id);
    }

    public Optional<Tenant> getTenantById(int id) {
        return tenantDao.getById(id);
    }

    public List<Tenant> getAllTenants() {
        return tenantDao.getAll();
    }
}