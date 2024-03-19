package com.zhaoyani.service.impl;

import com.zhaoyani.dao.AdminDao;
import com.zhaoyani.dao.BaseDao;
import com.zhaoyani.dao.HouseBrokerDao;
import com.zhaoyani.entity.Admin;
import com.zhaoyani.entity.HouseBroker;
import com.zhaoyani.service.HouseBrokerService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class HouseBrokerServiceImpl extends BaseServiceImpl<HouseBroker>  implements HouseBrokerService {

    @Autowired
    private HouseBrokerDao houseBrokerDao;

    @Autowired
    private AdminDao adminDao;

    @Override
    public BaseDao<HouseBroker> getBaseDao() {
        return houseBrokerDao;
    }

    @Override
    public List<HouseBroker> findBrokerByHouseId(Long houseId) {
        return houseBrokerDao.findBrokerByHouseId(houseId);
    }


    @Override
    public List<Admin> findHouseOtherBroker(Long houseId) {
        List<Long> brokerIds = houseBrokerDao.findBrokerIdByHouseId(houseId);
        List<Admin> houseOtherAdmin = adminDao.findHouseOtherAdmin(brokerIds);
        return houseOtherAdmin;
    }


}
