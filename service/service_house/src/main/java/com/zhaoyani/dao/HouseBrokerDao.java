package com.zhaoyani.dao;

import com.zhaoyani.entity.Admin;
import com.zhaoyani.entity.HouseBroker;

import java.util.List;

public interface HouseBrokerDao extends BaseDao<HouseBroker> {

    List<HouseBroker> findBrokerByHouseId(Long houseId);

    List<Long> findBrokerIdByHouseId(Long houseId);


}
