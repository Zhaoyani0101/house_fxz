package com.zhaoyani.service;

import com.zhaoyani.entity.Dict;
import com.zhaoyani.service.impl.BaseServiceImpl;

import java.util.List;
import java.util.Map;

public interface DictService extends BaseService<Dict> {

    List<Map<String,Object>> findZnodes(Long id);

    List<Dict> findDictByParentId(Long parentId);

    List<Dict> findDictByCode(String code);




}
