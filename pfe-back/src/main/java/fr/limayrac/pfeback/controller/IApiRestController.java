package fr.limayrac.pfeback.controller;

import java.util.Collection;

public interface IApiRestController<T, Id> {
    T findById(Id id);
    Collection<T> findAll();
    T create(T entity);
    void delete(Id entity);
    T put(T entity);
    T patch(T entity);

}
