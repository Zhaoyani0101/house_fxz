package com.zhaoyani.service;

import com.github.pagehelper.PageInfo;
import com.zhaoyani.entity.House;
import com.zhaoyani.vo.HouseQueryVo;
import com.zhaoyani.vo.HouseVo;

import java.util.List;

public interface HouseService extends BaseService<House> {

    void publish(Long houseId,Integer status);

    PageInfo<HouseVo> findListByQueryVo(Integer pageNum, Integer pageSize, HouseQueryVo houseQueryVo);

}
