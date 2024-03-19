package com.zhaoyani.service.impl;

import com.zhaoyani.dao.BaseDao;
import com.zhaoyani.dao.UserInfoDao;
import com.zhaoyani.entity.UserInfo;
import com.zhaoyani.service.UserInfoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;


    @Override
    public BaseDao<UserInfo> getBaseDao() {
        return userInfoDao;
    }

    @Override
    public UserInfo findUserInfoByPhone(String phone) {
        return userInfoDao.findUserInfoByPhone(phone);
    }
}
