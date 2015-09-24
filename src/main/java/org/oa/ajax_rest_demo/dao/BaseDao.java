package org.oa.ajax_rest_demo.dao;

import java.util.List;

public interface BaseDao<T> {

    List<T> loadAll();

    T findById(long id);

    T create(T item);

    T update(T item);

    boolean delete(T item);
}
