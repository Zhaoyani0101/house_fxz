package com.zhaoyani.service.impl;

import com.zhaoyani.dao.BaseDao;
import com.zhaoyani.dao.RolePermissionDao;
import com.zhaoyani.entity.RolePermission;
import com.zhaoyani.service.RolePermissionService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@DubboService
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermission> implements RolePermissionService {

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    public BaseDao<RolePermission> getBaseDao() {
        return rolePermissionDao;
    }

    @Override
    @Transactional
    public void insertRolePermission(Long roleId, String[] permissionIds) {
        //1.删除当前用户所拥有的所有菜单信息
        rolePermissionDao.deleteByRoleId(roleId);
        //2.重新新增
        for (String permissionId : permissionIds) {
            if(permissionId.equals(""))
                continue;
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(Long.parseLong(permissionId));

            rolePermissionDao.insert(rolePermission);
        }

    }
}
