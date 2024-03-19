package com.zhaoyani.dao;

import com.zhaoyani.entity.Admin;
import com.zhaoyani.entity.AdminRole;

import java.util.List;

/**
 * ClassName: AdminDao
 * Package:com.zhaoyani.dao
 * Description:
 *
 * @Author ZYN
 * @Create 2024-02-29 8:24
 */
public interface AdminRoleDao extends BaseDao<AdminRole> {

    List<Long> findRoleIdByAdminId(Long adminId);

    void deleteByAdminId(Long adminId);


}
