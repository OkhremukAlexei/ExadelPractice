package com.bsu.DAO;

import java.util.List;
import java.util.Map;

public interface Dao<T> {
    T get(long id);
    List<T> getAll();
    boolean save(T t);
    boolean update(long id, Map<String, String> params);
    boolean delete(T t);
}
