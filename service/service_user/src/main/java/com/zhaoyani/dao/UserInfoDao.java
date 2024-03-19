package com.zhaoyani.dao;

import com.zhaoyani.entity.UserInfo;

public interface UserInfoDao extends BaseDao<UserInfo> {

    UserInfo findUserInfoByPhone( String phone);

}
