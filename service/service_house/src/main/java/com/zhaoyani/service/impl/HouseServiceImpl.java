package com.zhaoyani.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaoyani.dao.BaseDao;
import com.zhaoyani.dao.DictDao;
import com.zhaoyani.dao.HouseDao;
import com.zhaoyani.entity.House;
import com.zhaoyani.service.HouseService;
import com.zhaoyani.vo.HouseQueryVo;
import com.zhaoyani.vo.HouseVo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

@DubboService
public class HouseServiceImpl extends BaseServiceImpl<House> implements HouseService {

    @Autowired
    private HouseDao houseDao;

    @Autowired
    private DictDao dictDao;

    @Override
    public BaseDao<House> getBaseDao() {
        return houseDao;
    }

    @Override
    public void publish(Long houseId, Integer status) {
        houseDao.publish(houseId,status);
    }

    @Override
    public PageInfo<HouseVo> findListByQueryVo(Integer pageNum, Integer pageSize, HouseQueryVo houseQueryVo) {
        PageHelper.startPage(pageNum,pageSize);
        List<HouseVo> listByQueryVo = houseDao.findListByQueryVo(houseQueryVo);
        for (HouseVo houseVo : listByQueryVo) {
            houseVo.setHouseTypeName(dictDao.getById(houseVo.getHouseTypeId()).getName());
            houseVo.setFloorName(dictDao.getById(houseVo.getFloorId()).getName());
            houseVo.setDirectionName(dictDao.getById(houseVo.getDirectionId()).getName());
        }

        return new PageInfo<>(listByQueryVo,3);
    }

    @Override
    public House getById(Serializable id) {
        House house = houseDao.getById(id);
        house.setHouseTypeName(dictDao.getById(house.getHouseTypeId()).getName());
        house.setFloorName(dictDao.getById(house.getFloorId()).getName());
        house.setBuildStructureName(dictDao.getById(house.getBuildStructureId()).getName());
        house.setDecorationName(dictDao.getById(house.getDecorationId()).getName());
        house.setDirectionName(dictDao.getById(house.getDirectionId()).getName());
        house.setHouseUseName(dictDao.getById(house.getHouseUseId()).getName());
        return house;
    }
}
