package com.zhaoyani.service.impl;

import com.zhaoyani.dao.BaseDao;
import com.zhaoyani.dao.DictDao;
import com.zhaoyani.entity.Dict;
import com.zhaoyani.service.DictService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DubboService
public class DictServiceImpl extends BaseServiceImpl<Dict> implements DictService {

    @Autowired
    private DictDao dictDao;

    @Override
    public BaseDao<Dict> getBaseDao() {
        return dictDao;
    }

    @Override
    public List<Map<String, Object>> findZnodes(Long id) {
        List<Dict> list = dictDao.findDictByParentId(id);
        List<Map<String, Object>> zNodes = new ArrayList<>();
        for (Dict dict : list) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",dict.getId());
            map.put("name",dict.getName());
            map.put("isParent",dictDao.findDictByParentId(dict.getId()).size()>0);
            zNodes.add(map);
        }

        return  zNodes;
    }

    @Override
    public List<Dict> findDictByParentId(Long parentId) {
        return dictDao.findDictByParentId(parentId);
    }

    @Override
    public List<Dict> findDictByCode(String code) {
        // 1.根据code找id
        Long id = dictDao.findIdByCode(code);
        // 2.根据id找子级
        return dictDao.findDictByParentId(id);
    }




}
