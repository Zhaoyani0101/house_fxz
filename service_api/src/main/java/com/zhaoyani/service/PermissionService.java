package com.zhaoyani.service;

import com.zhaoyani.entity.Permission;

import java.util.List;
import java.util.Map;


public interface PermissionService extends BaseService<Permission> {

    List<Map<String, Object>> findZnodes(Long roleId);

    List<Permission> findPermissionByAdminId(Long adminId);

    List<Permission> findAll();

    List<String> findCodeByAdminId(Long adminId);
}
