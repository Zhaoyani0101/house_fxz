package com.zhaoyani.service;

import com.zhaoyani.entity.RolePermission;


public interface RolePermissionService extends BaseService<RolePermission> {

    void insertRolePermission(Long roleId,String[] permissionIds);
}
