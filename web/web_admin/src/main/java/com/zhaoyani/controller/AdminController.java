package com.zhaoyani.controller;

import com.github.pagehelper.PageInfo;
import com.zhaoyani.entity.Admin;
import com.zhaoyani.entity.AdminRole;
import com.zhaoyani.entity.Role;
import com.zhaoyani.service.AdminRoleService;
import com.zhaoyani.service.AdminService;
import com.zhaoyani.service.RoleService;
import com.zhaoyani.util.QiniuUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * ClassName: AdminController
 * Package:com.zhaoyani.controller
 * Description:
 *
 * @Author ZYN
 * @Create 2024-02-29 8:21
 */

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController{

    @DubboReference
    private AdminService adminService;

    @DubboReference
    private RoleService roleService;

    @DubboReference
    private AdminRoleService adminRoleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping
    public String findAll(Map map, HttpServletRequest request){
        Map<String,Object> filters = getFilters(request);
        PageInfo<Admin> page = adminService.findPage(filters);
        map.put("page",page);
        map.put("filters",filters);
        return "admin/index";
    }

    @RequestMapping("/create")
    public String create(){
        return "admin/create";
    }

    @RequestMapping("/save")
    public String save(Admin admin){
        //对admin对象中的password进行加密
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminService.insert(admin);
        return "common/success";
    }

    @RequestMapping("/edit/{adminId}")
    public String edit(@PathVariable Long adminId,Map map){
        Admin admin = adminService.getById(adminId);
        map.put("admin",admin);
        return "admin/edit";
    }

    @RequestMapping("/update")
    public String update(Admin admin){
        adminService.update(admin);
        return "common/success";
    }

    @RequestMapping("/delete/{adminId}")
    public String delete(@PathVariable Long adminId){
        adminService.delete(adminId);
        return "redirect:/admin";
    }

    @RequestMapping("/uploadShow/{adminId}")
    public String uploadShow(@PathVariable Long adminId,Map map){
        map.put("adminId",adminId);
        return "admin/upload";
    }

    @RequestMapping("/upload")
    public String upload(Long adminId, @RequestParam("file") MultipartFile file) throws IOException {
        //1.将图片上传到七牛云
        String fileName = UUID.randomUUID().toString();
        QiniuUtil.upload2Qiniu(file.getBytes(),fileName);
        //2.将当前图片的url设置完当前用户的head_url上
        String headUrl="http://s9wk97c0p.hn-bkt.clouddn.com/"+fileName;
        adminService.updateHeadUrl(adminId,headUrl);
        return "common/success";
    }

    @RequestMapping("/assignShow/{adminId}")
    public String assignShow(@PathVariable Long adminId,Map map){
        //1.adminId
        map.put("adminId",adminId);
        //2.当前用户已拥有角色列表
        //3.当前用户未拥有角色列表
        Map<String, List<Role>> roleByAdminId = roleService.findRoleByAdminId(adminId);
        map.putAll(roleByAdminId);
        return "admin/assignShow";
    }

    @RequestMapping("/assignRole")
    private String assignRole(Long adminId,String [] roleIds){
           adminRoleService.insertAdminRole(adminId,roleIds);
        return "common/success";
    }




}
