package com.zhaoyani.service;

import com.zhaoyani.entity.Admin;

import java.util.List;

/**
 * ClassName: AdminService
 * Package:com.zhaoyani.service
 * Description:
 *
 * @Author ZYN
 * @Create 2024-02-29 8:25
 */
public interface AdminService extends BaseService<Admin>{

    void updateHeadUrl(Long adminId,String headUrl);

    Admin findAdminByUserName(String username);


}
