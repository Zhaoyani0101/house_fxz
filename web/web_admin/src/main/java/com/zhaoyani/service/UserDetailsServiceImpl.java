package com.zhaoyani.service;

import com.zhaoyani.entity.Admin;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @DubboReference
    private AdminService adminService;

    @DubboReference
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminService.findAdminByUserName(username);
        if(admin==null){
            throw  new UsernameNotFoundException("用户名没有找到！");
        }

        List<String> codeList = permissionService.findCodeByAdminId(admin.getId());
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        for (String code : codeList) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(code);
            grantedAuthorityList.add(simpleGrantedAuthority);
        }


        return new User(username,admin.getPassword(), grantedAuthorityList);

    }
}
