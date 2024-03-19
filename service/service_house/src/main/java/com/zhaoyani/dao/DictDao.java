package com.zhaoyani.dao;

import com.zhaoyani.entity.Dict;

import java.util.List;

public interface DictDao extends BaseDao<Dict> {

    List<Dict> findAll();

    List<Dict> findDictByParentId(Long id);

    Long findIdByCode(String code);

}

