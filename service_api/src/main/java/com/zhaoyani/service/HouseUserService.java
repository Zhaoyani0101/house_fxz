package com.zhaoyani.service;

import com.zhaoyani.entity.HouseUser;

import java.util.List;

public interface HouseUserService extends BaseService<HouseUser> {

    List<HouseUser> findUserByHouseId(Long houseId);
}
