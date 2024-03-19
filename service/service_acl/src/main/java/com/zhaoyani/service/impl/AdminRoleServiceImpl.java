package com.zhaoyani.service.impl;

import com.zhaoyani.dao.AdminRoleDao;
import com.zhaoyani.dao.BaseDao;
import com.zhaoyani.entity.AdminRole;
import com.zhaoyani.service.AdminRoleService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@DubboService
public class AdminRoleServiceImpl extends BaseServiceImpl<AdminRole> implements AdminRoleService {

    @Autowired
    private AdminRoleDao adminRoleDao;


    @Override
    public BaseDao<AdminRole> getBaseDao() {
        return adminRoleDao;
    }

    @Override
    @Transactional
    public void insertAdminRole(Long adminId, String[] roleIds) {
        //1.删除原有的角色id
          adminRoleDao.deleteByAdminId(adminId);
        //2.添加新角色
        for (String roleId : roleIds) {
            if(roleId.equals(""))
                continue;
            AdminRole adminRole = new AdminRole();
            adminRole.setAdminId(adminId);
            adminRole.setRoleId(Long.parseLong(roleId));

            adminRoleDao.insert(adminRole);
        }
    }
}
