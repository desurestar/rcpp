package ru.zagrebin.laba6.dao;

import ru.zagrebin.laba6.model.SalesPlace;

import java.util.List;
import java.util.Optional;

public interface SalesPlaceDao {
    void add(SalesPlace place);
    void update(SalesPlace place);
    void delete(int id);
    Optional<SalesPlace> getById(int id);
    List<SalesPlace> getAll();
}