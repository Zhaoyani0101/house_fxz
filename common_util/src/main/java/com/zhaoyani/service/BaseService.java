package com.zhaoyani.service;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.Map;

/**
 * ClassName: BaseService
 * Package:com.zhaoyani.service
 * Description:
 *
 * @Author ZYN
 * @Create 2024-02-28 17:49
 */
public interface BaseService<T> {

    PageInfo<T> findPage(Map<String,Object> filters);

    void insert(T t);

    T getById(Serializable id);

    void update(T t);

    void delete(Serializable id);

}
