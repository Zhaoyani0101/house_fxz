package com.zhaoyani.service;

import com.zhaoyani.entity.Admin;
import com.zhaoyani.entity.HouseBroker;

import java.util.List;

public interface HouseBrokerService extends BaseService<HouseBroker> {

    List<HouseBroker> findBrokerByHouseId(Long houseId);

    List<Admin> findHouseOtherBroker(Long houseId);

}
