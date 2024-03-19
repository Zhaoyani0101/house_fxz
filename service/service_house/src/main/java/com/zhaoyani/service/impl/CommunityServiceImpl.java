package com.zhaoyani.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaoyani.dao.BaseDao;
import com.zhaoyani.dao.CommunityDao;
import com.zhaoyani.dao.DictDao;
import com.zhaoyani.entity.Community;
import com.zhaoyani.entity.Dict;
import com.zhaoyani.service.CommunityService;
import com.zhaoyani.util.CastUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@DubboService
public class CommunityServiceImpl extends BaseServiceImpl<Community> implements CommunityService {

    @Autowired
   private CommunityDao communityDao;
    @Autowired
    private DictDao dictDao;

    @Override
    public BaseDao<Community> getBaseDao() {
        return communityDao;
    }

    @Override
    public PageInfo<Community> findPage(Map<String, Object> filters) {
        //当前页数
        int pageNum = CastUtil.castInt(filters.get("pageNum"), 1);
        //每页显示的记录条数
        int pageSize = CastUtil.castInt(filters.get("pageSize"), 5);
        //查询数据之前，进行分页信息的设置
        PageHelper.startPage(pageNum, pageSize);
        List<Community> list = this.getBaseDao().findPage(filters);
        for (Community community : list) {
            //1.根据小区的区域id找到小区的区域名字
            Dict areaDict = dictDao.getById(community.getAreaId());
            community.setAreaName(areaDict.getName());
            //2.根据小区的板块id找到小区的板块名字
            Dict plateDict = dictDao.getById(community.getPlateId());
            community.setPlateName(plateDict.getName());

        }

        return new PageInfo<Community>(list, 3);
    }

    @Override
    public Community getById(Serializable id) {
        Community community = communityDao.getById(id);
        Dict areaDict = dictDao.getById(community.getAreaId());
        community.setAreaName(areaDict.getName());
        Dict plateDict = dictDao.getById(community.getPlateId());
        community.setPlateName(plateDict.getName());
        return community;
    }

    @Override
    public List<Community> findAll() {
        return communityDao.findAll();
    }
}
