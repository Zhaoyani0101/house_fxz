package com.zhaoyani.dao;

import com.zhaoyani.entity.HouseUser;

import java.util.List;

public interface HouseUserDao extends BaseDao<HouseUser> {

    List<HouseUser> findUserByHouseId(Long houseId);

}
