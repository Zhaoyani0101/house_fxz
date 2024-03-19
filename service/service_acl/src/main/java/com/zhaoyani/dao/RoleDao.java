package com.zhaoyani.dao;

import com.zhaoyani.entity.Role;

import java.util.List;
import java.util.Map;

public interface RoleDao extends BaseDao<Role> {

    List<Role> findAll();
    List<Role> findRoles(Map filters);


}
