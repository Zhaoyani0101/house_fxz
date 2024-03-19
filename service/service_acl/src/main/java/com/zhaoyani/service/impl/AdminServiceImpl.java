package com.zhaoyani.service.impl;

import com.zhaoyani.dao.AdminDao;
import com.zhaoyani.dao.BaseDao;
import com.zhaoyani.entity.Admin;
import com.zhaoyani.service.AdminService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: AdminServiceImpl
 * Package:com.zhaoyani.service.impl
 * Description:
 *
 * @Author ZYN
 * @Create 2024-02-29 8:25
 */
@DubboService
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public BaseDao<Admin> getBaseDao() {
        return adminDao;
    }

    @Override
    public void updateHeadUrl(Long adminId, String headUrl) {
        adminDao.updateHeadUrl(adminId,headUrl);
    }

    @Override
    public Admin findAdminByUserName(String username) {
        return adminDao.findAdminByUserName(username);
    }


}
