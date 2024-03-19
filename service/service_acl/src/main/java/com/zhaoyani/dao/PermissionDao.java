package com.zhaoyani.dao;

import com.zhaoyani.entity.Permission;

import java.io.Serializable;
import java.util.List;

public interface PermissionDao extends BaseDao<Permission> {

    List<Permission> findAll();

    List<Permission> findPermissionByAdminId(Long adminId);

    List<Permission> findPermissionByParentId(Serializable parentId);

    List<String> findAllCode();

    List<String> findCodeByAdminId(Long adminId);
}
