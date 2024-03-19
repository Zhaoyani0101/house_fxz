package com.zhaoyani.dao;

import com.zhaoyani.entity.Permission;
import com.zhaoyani.entity.RolePermission;

import java.util.List;

public interface RolePermissionDao extends BaseDao<RolePermission> {

    List<Long> findPermissionIdsByRoleId(Long roleId);

    void deleteByRoleId(Long roleId);

}
