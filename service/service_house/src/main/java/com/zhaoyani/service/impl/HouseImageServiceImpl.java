package com.zhaoyani.service.impl;

import com.zhaoyani.dao.BaseDao;
import com.zhaoyani.dao.HouseImageDao;
import com.zhaoyani.entity.HouseImage;
import com.zhaoyani.service.HouseImageService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class HouseImageServiceImpl extends BaseServiceImpl<HouseImage> implements HouseImageService {

    @Autowired
    private HouseImageDao houseImageDao;

    @Override
    public BaseDao<HouseImage> getBaseDao() {
        return houseImageDao;
    }

    @Override
    public List<HouseImage> findImageByHouseId(Long houseId, Integer type) {
        return houseImageDao.findImageByHouseId(houseId,type);

    }
}
