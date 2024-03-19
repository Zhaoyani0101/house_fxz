package com.zhaoyani.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zhaoyani.entity.Role;
import com.zhaoyani.service.PermissionService;
import com.zhaoyani.service.RolePermissionService;
import com.zhaoyani.service.RoleService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.dsig.XMLValidateContext;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController{

    @DubboReference
    private RoleService roleService;

    @DubboReference
    private PermissionService permissionService;

    @DubboReference
    private RolePermissionService rolePermissionService;

    @RequestMapping
    public String findAll(Map map,HttpServletRequest request){
        Map<String,Object> filters = getFilters(request);
        //List<Role> list = roleService.findRoles(filters);
        PageInfo<Role> page = roleService.findPage(filters);
        map.put("page",page);
        map.put("filters",filters);
        return "role/index";
    }

    @RequestMapping("/save")
    public String sava(Role role){
        roleService.insert(role);
        return "common/success";
    }

    @RequestMapping("/edit/{roleId}")
    public String edit(@PathVariable Long roleId,Map map){
        Role role = roleService.getById(roleId);
        map.put("role",role);
        return "role/edit";
    }

    @RequestMapping("/update")
    public String update(Role role) {
        roleService.update(role);
        return "common/success";
    }

    @RequestMapping("/delete/{roleId}")
    public String delete(@PathVariable Long roleId) {
        roleService.delete(roleId);
        return "redirect:/role";
    }

    @RequestMapping("/assignShow/{roleId}")
    public String assignShow(@PathVariable Long roleId,Map map){
        map.put("roleId",roleId);

        List<Map<String, Object>> zNodes = permissionService.findZnodes(roleId);
        map.put("zNodes",zNodes);
        return "role/assignShow";
    }

    @RequestMapping("/assignPermission")
    public String assignPermission(Long roleId,String[] permissionIds){
         rolePermissionService.insertRolePermission(roleId,permissionIds);
        return "common/success";

    }





}
