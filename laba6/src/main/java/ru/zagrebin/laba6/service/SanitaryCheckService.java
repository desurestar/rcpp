package ru.zagrebin.laba6.service;

import ru.zagrebin.laba6.dao.SanitaryCheckDao;
import ru.zagrebin.laba6.model.SanitaryCheck;

import java.util.List;
import java.util.Optional;

public class SanitaryCheckService {
    private final SanitaryCheckDao checkDao;

    public SanitaryCheckService(SanitaryCheckDao checkDao) {
        this.checkDao = checkDao;
    }

    public void addCheck(SanitaryCheck check) {
        checkDao.add(check);
    }

    public void updateCheck(SanitaryCheck check) {
        checkDao.update(check);
    }

    public void deleteCheck(int id) {
        checkDao.delete(id);
    }

    public Optional<SanitaryCheck> getCheckById(int id) {
        return checkDao.getById(id);
    }

    public List<SanitaryCheck> getAllChecks() {
        return checkDao.getAll();
    }
}