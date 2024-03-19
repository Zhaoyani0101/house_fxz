package com.zhaoyani.dao;

import com.zhaoyani.entity.HouseImage;

import java.util.List;

public interface HouseImageDao extends BaseDao<HouseImage> {

    List<HouseImage> findImageByHouseId(Long houseId, Integer type);
}
