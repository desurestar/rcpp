package ru.zagrebin.laba5.interfaces;

import java.util.Collection;
import java.util.Optional;

public interface Dao<T, ID> {
    T findById(ID id);
    Optional<T> findOptionalById(ID id);
    Collection<T> findAll();
    T save(T t);
    T update(T t);
    void delete(T t);
    void deleteById(ID id);
    Collection<T> findByLastName(String lastName);
    Collection<T> findByGroup(String group);
}
