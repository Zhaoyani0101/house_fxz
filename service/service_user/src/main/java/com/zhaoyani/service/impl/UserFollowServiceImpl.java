package com.zhaoyani.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaoyani.dao.BaseDao;
import com.zhaoyani.dao.DictDao;
import com.zhaoyani.dao.UserFollowDao;
import com.zhaoyani.entity.UserFollow;
import com.zhaoyani.service.UserFollowService;
import com.zhaoyani.vo.UserFollowVo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class UserFollowServiceImpl extends BaseServiceImpl<UserFollow> implements UserFollowService {

   @Autowired
   private UserFollowDao userFollowDao;

   @Autowired
   private DictDao dictDao;

    @Override
    public BaseDao getBaseDao() {
        return userFollowDao;
    }


    @Override
    public void follow(Long userId, Long houseId) {
        UserFollow userFollow = new UserFollow();
        userFollow.setUserId(userId);
        userFollow.setHouseId(houseId);
        userFollowDao.insert(userFollow);
    }

    @Override
    public UserFollow isFollow(Long userId, Long houseId) {
        return userFollowDao.isFollow(userId,houseId);
    }

    @Override
    public PageInfo<UserFollowVo> findFollowByUser(Integer pageNum, Integer pageSize, Long userId) {
        PageHelper.startPage(pageNum,pageSize);
        List<UserFollowVo> userFollowVoList = userFollowDao.findFollowByUser(userId);
        for (UserFollowVo userFollowVo : userFollowVoList) {
            userFollowVo.setHouseTypeName(dictDao.getById(userFollowVo.getHouseTypeId()).getName());
            userFollowVo.setFloorName(dictDao.getById(userFollowVo.getFloorId()).getName());
            userFollowVo.setDirectionName(dictDao.getById(userFollowVo.getDirectionId()).getName());
        }
        return new PageInfo<UserFollowVo>(userFollowVoList,3);
    }
}
