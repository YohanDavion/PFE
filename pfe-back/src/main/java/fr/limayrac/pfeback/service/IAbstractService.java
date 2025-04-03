package fr.limayrac.pfeback.service;

import java.util.List;

public interface IAbstractService<T> {
    List<T> findAll();
    T findById(Long id);
    T save(T t);
    void delete(T t);
    void delete(Long id);
}