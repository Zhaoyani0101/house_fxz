package com.zhaoyani.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaoyani.dao.BaseDao;
import com.zhaoyani.service.BaseService;
import com.zhaoyani.util.CastUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * ClassName: BaseServiceImpl
 * Package:com.zhaoyani.service.impl
 * Description:
 *
 * @Author ZYN
 * @Create 2024-02-28 17:53
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    public abstract BaseDao<T> getBaseDao();

    @Override
    public PageInfo<T> findPage(Map<String, Object> filters) {
        //当前页数
        int pageNum = CastUtil.castInt(filters.get("pageNum"), 1);
        //每页显示的记录条数
        int pageSize = CastUtil.castInt(filters.get("pageSize"), 5);
        //查询数据之前，进行分页信息的设置
        PageHelper.startPage(pageNum, pageSize);
        List<T> list = this.getBaseDao().findPage(filters);
        return new PageInfo<T>(list, 3);
    }

    @Override
    public void insert(T t) {
        getBaseDao().insert(t);
    }

    @Override
    public T getById(Serializable id) {
        return getBaseDao().getById(id);
    }

    @Override
    public void update(T t) {
        getBaseDao().update(t);
    }

    @Override
    public void delete(Serializable id) {
        getBaseDao().delete(id);
    }
}
