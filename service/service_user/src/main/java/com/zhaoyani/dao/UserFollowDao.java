package com.zhaoyani.dao;

import com.zhaoyani.entity.UserFollow;
import com.zhaoyani.vo.UserFollowVo;

import java.util.List;

public interface UserFollowDao extends BaseDao<UserFollow> {

    UserFollow isFollow(Long userId,Long houseId);

    List<UserFollowVo> findFollowByUser(Long userId);
}
