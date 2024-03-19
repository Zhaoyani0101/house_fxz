package com.zhaoyani.service.impl;

import com.zhaoyani.dao.AdminRoleDao;
import com.zhaoyani.dao.BaseDao;
import com.zhaoyani.dao.RoleDao;
import com.zhaoyani.entity.Role;
import com.zhaoyani.service.RoleService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DubboService
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private AdminRoleDao adminRoleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public List<Role> findRoles(Map<String,Object> filters) {
        return roleDao.findRoles(filters);
    }

    @Override
    public Map<String, List<Role>> findRoleByAdminId(Long adminId) {
        //1.查询所有角色信息
        List<Role> roleList = roleDao.findAll();
        //2.当前用户已拥有角色id
        List<Long> roleIds = adminRoleDao.findRoleIdByAdminId(adminId);
        //3.进行分类
        List<Role> noAssignRoleList = new ArrayList<>();
        List<Role> assignRoleList = new ArrayList<>();
        for (Role role : roleList) {
            if(roleIds.contains(role.getId())){
                //用户已拥有角色
                assignRoleList.add(role);
            }else{
                //用户未拥有角色
                noAssignRoleList.add(role);
            }
        }
        Map<String,List<Role>> map = new HashMap<>();
        //4.将两个列表放到map中返回
        map.put("assignRoleList",assignRoleList);
        map.put("noAssignRoleList",noAssignRoleList);
        return map;
    }


    @Override
    public BaseDao<Role> getBaseDao() {
        return roleDao;
    }
}
