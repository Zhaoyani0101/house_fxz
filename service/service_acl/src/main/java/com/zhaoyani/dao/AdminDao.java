package com.zhaoyani.dao;

import com.zhaoyani.entity.Admin;

/**
 * ClassName: AdminDao
 * Package:com.zhaoyani.dao
 * Description:
 *
 * @Author ZYN
 * @Create 2024-02-29 8:24
 */
public interface AdminDao extends BaseDao<Admin> {

    void updateHeadUrl(Long adminId,String headUrl);

    Admin findAdminByUserName(String username);

}
