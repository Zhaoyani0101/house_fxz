package com.zhaoyani.controller;

import com.zhaoyani.entity.Permission;
import com.zhaoyani.service.PermissionService;
import com.zhaoyani.service.RolePermissionService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @DubboReference
    private PermissionService permissionService;

    @RequestMapping
    public String index(Map map){
        List<Permission> list = permissionService.findAll();
        map.put("list",list);
        return "permission/index";
    }

    @RequestMapping("/create")
    public String create(Permission permission,Map map){
        map.put("permission",permission);
        return "permission/create";
    }

    @RequestMapping("/save")
    public String save(Permission permission){
        permissionService.insert(permission);
        return "common/success";
    }

    @RequestMapping("/edit/{permissionId}")
    public String edit(@PathVariable Long permissionId,Map map){
        Permission permission = permissionService.getById(permissionId);
        map.put("permission",permission);
        return "permission/edit";
    }

    @RequestMapping("/update")
    public String update(Permission permission){
        permissionService.update(permission);
        return "common/success";
    }

    @RequestMapping("/delete/{permissionId}")
    public String delete(@PathVariable Long permissionId){
        permissionService.delete(permissionId);
        return "redirect:/permission";
    }
}
