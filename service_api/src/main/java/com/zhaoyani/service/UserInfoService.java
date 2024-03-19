package com.zhaoyani.service;

import com.zhaoyani.entity.UserInfo;

public interface UserInfoService extends BaseService<UserInfo> {

    UserInfo findUserInfoByPhone( String phone);
}
