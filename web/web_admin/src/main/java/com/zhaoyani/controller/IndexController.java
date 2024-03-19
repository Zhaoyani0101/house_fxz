package com.zhaoyani.controller;

import com.zhaoyani.entity.Admin;
import com.zhaoyani.entity.Permission;
import com.zhaoyani.service.AdminService;
import com.zhaoyani.service.PermissionService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @DubboReference
    private PermissionService permissionService;
    
    @DubboReference
    private AdminService adminService;

    @RequestMapping("/index")
    public String toIndexPage(Map map){

//        Long adminId = 1L;
        //1.获取到security提供的User对象
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        //2.根据用户名获取admin对象
        Admin admin = adminService.findAdminByUserName(user.getUsername());
        List<Permission> permissionByAdminId = permissionService.findPermissionByAdminId(admin.getId());
        map.put("permissionList",permissionByAdminId);
        return "frame/index";
    }

    @RequestMapping("/login")
    public String login(){
        return "frame/login";
    }

    @RequestMapping("/auth")
    public String auth(){
        return "frame/auth";
    }
}
