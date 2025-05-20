package ru.zagrebin.laba6.service;

import ru.zagrebin.laba6.dao.SalesPlaceDao;
import ru.zagrebin.laba6.model.SalesPlace;

import java.util.List;
import java.util.Optional;

public class SalesPlaceService {
    private final SalesPlaceDao placeDao;

    public SalesPlaceService(SalesPlaceDao placeDao) {
        this.placeDao = placeDao;
    }

    public void addPlace(SalesPlace place) {
        placeDao.add(place);
    }

    public void updatePlace(SalesPlace place) {
        placeDao.update(place);
    }

    public void deletePlace(int id) {
        placeDao.delete(id);
    }

    public Optional<SalesPlace> getPlaceById(int id) {
        return placeDao.getById(id);
    }

    public List<SalesPlace> getAllPlaces() {
        return placeDao.getAll();
    }
}
