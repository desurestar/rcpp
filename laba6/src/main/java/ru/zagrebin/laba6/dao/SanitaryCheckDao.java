package ru.zagrebin.laba6.dao;

import ru.zagrebin.laba6.model.SanitaryCheck;

import java.util.List;
import java.util.Optional;

public interface SanitaryCheckDao {
    void add(SanitaryCheck check);
    void update(SanitaryCheck check);
    void delete(int id);
    Optional<SanitaryCheck> getById(int id);
    List<SanitaryCheck> getAll();
}
