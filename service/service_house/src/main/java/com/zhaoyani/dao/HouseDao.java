package com.zhaoyani.dao;

import com.github.pagehelper.PageInfo;
import com.zhaoyani.entity.House;
import com.zhaoyani.vo.HouseQueryVo;
import com.zhaoyani.vo.HouseVo;

import java.util.List;

public interface HouseDao extends BaseDao<House> {

    void publish(Long houseId,Integer status);

    List<HouseVo> findListByQueryVo(HouseQueryVo houseQueryVo);
}
