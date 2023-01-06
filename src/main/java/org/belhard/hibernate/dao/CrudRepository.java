package org.belhard.hibernate.dao;

import java.util.List;

public interface CrudRepository <T, K> {
    T find (K key);
    List<T> findAll();
    void save (T entity);
    boolean delete (K key);
}
