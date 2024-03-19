package com.zhaoyani.service;

import com.zhaoyani.entity.HouseImage;

import java.util.List;

public interface HouseImageService extends BaseService<HouseImage> {

    List<HouseImage> findImageByHouseId(Long houseId,Integer type);
}
