package com.zhaoyani.service;

import com.zhaoyani.entity.Admin;
import com.zhaoyani.entity.AdminRole;

public interface AdminRoleService extends BaseService<AdminRole> {

    void insertAdminRole(Long adminId,String[] roleIds);


}
