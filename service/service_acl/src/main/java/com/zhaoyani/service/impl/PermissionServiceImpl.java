package com.zhaoyani.service.impl;

import com.zhaoyani.dao.BaseDao;
import com.zhaoyani.dao.PermissionDao;
import com.zhaoyani.dao.RolePermissionDao;
import com.zhaoyani.entity.Permission;
import com.zhaoyani.service.PermissionService;
import com.zhaoyani.util.PermissionHelper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DubboService
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;
    
    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    public BaseDao<Permission> getBaseDao() {
        return permissionDao;
    }

    @Override
    public List<Map<String, Object>> findZnodes(Long roleId) {
        //1.查询到所有的菜单
        List<Permission> permissionList = permissionDao.findAll();
        //2.将List<Permission>变成Map<String, Object>
        List<Map<String, Object>> zNodes = new ArrayList<>();

        for (Permission permission : permissionList) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",permission.getId());
            map.put("pId",permission.getParentId());
            map.put("name",permission.getName());

            List<Long> ids = rolePermissionDao.findPermissionIdsByRoleId(roleId);
            if(ids.contains(permission.getId())){
                map.put("checked",true);
            }
            zNodes.add(map);
        }
        return zNodes;
    }

    @Override
    public List<Permission> findPermissionByAdminId(Long adminId) {
        //预留一个超级管理员
        List<Permission> permissionList = null;
        if(adminId == 1){
            permissionList = permissionDao.findAll();
        }else{
            permissionList = permissionDao.findPermissionByAdminId(adminId);
        }
        List<Permission> permissionList1 = PermissionHelper.bulid(permissionList);

        return permissionList1;
    }

    @Override
    public List<Permission> findAll() {
        List<Permission> permissionList = permissionDao.findAll();
        return PermissionHelper.bulid(permissionList);
    }

    @Override
    public List<String> findCodeByAdminId(Long adminId) {
        List<String> codes = null;
        if(adminId==1){
           codes = permissionDao.findAllCode();
        }else{
            codes = permissionDao.findCodeByAdminId(adminId);
        }
        return codes;
    }

    @Override
    public void delete(Serializable id) {
        List<Permission> permissionList1 = permissionDao.findPermissionByParentId(id);
        if(permissionList1==null&&permissionList1.size()>0){
            for (Permission permission : permissionList1) {
                List<Permission> permissionList2 = permissionDao.findPermissionByParentId(permission.getId());
                    if(permissionList2==null&&permissionList2.size()>0){
                        for (Permission permission1 : permissionList2) {
                            permissionDao.delete(permission1.getId());
                        }
                    }
                  permissionDao.delete(permission.getId());
            }
        }
        permissionDao.delete(id);
    }
}
