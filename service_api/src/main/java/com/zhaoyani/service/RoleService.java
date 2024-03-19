package com.zhaoyani.service;

import com.zhaoyani.entity.Role;

import java.util.List;
import java.util.Map;

public interface RoleService extends BaseService<Role> {

    List<Role> findAll();

    List<Role> findRoles(Map<String, Object> filters);

    Map<String,List<Role>> findRoleByAdminId(Long adminId);

}
