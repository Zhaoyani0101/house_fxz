package com.zhaoyani.service;

import com.github.pagehelper.PageInfo;
import com.zhaoyani.entity.UserFollow;
import com.zhaoyani.vo.UserFollowVo;

import java.util.List;

public interface UserFollowService extends BaseService<UserFollow> {

    void follow(Long userId,Long houseId);

    UserFollow isFollow(Long userId,Long houseId);

    PageInfo<UserFollowVo> findFollowByUser(Integer pageNum, Integer pageSize, Long houseId);
}
