package com.zhaoyani.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * ClassName: BaseDao
 * Package:com.zhaoyani.dao
 * Description:
 *
 * @Author ZYN
 * @Create 2024-02-28 17:53
 */
public interface BaseDao<T> {

    List<T> findPage(Map filters);

    void insert(T t);

    T getById(Serializable id);

    void update(T t);

    void delete(Serializable id);
}
