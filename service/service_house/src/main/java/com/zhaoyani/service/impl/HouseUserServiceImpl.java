package com.zhaoyani.service.impl;

import com.zhaoyani.dao.BaseDao;
import com.zhaoyani.dao.HouseUserDao;
import com.zhaoyani.entity.HouseUser;
import com.zhaoyani.service.HouseUserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class HouseUserServiceImpl extends BaseServiceImpl<HouseUser> implements HouseUserService {

    @Autowired
    private HouseUserDao houseUserDao;

    @Override
    public BaseDao<HouseUser> getBaseDao() {
        return houseUserDao;
    }

    @Override
    public List<HouseUser> findUserByHouseId(Long houseId) {
        return houseUserDao.findUserByHouseId(houseId);
    }
}
