package com.zhaoyani.controller;

import com.github.pagehelper.StringUtil;
import com.zhaoyani.entity.UserInfo;
import com.zhaoyani.result.Result;
import com.zhaoyani.result.ResultCodeEnum;
import com.zhaoyani.service.UserInfoService;
import com.zhaoyani.util.MD5;
import com.zhaoyani.vo.LoginVo;
import com.zhaoyani.vo.RegisterVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@ResponseBody
@RequestMapping("/userInfo")
public class UserInfoController {

    @DubboReference
    private UserInfoService userInfoService;

    @RequestMapping("/sendCode/{phone}")
    public Result sendCode(@PathVariable String phone, HttpSession session){
        Random random = new Random();
        int randomCode = random.nextInt(9999-1000+1)+1000;
        String code = Integer.toString(randomCode);
        session.setAttribute(phone,code);
        return Result.ok(code);
    }

    @RequestMapping("/register")
    public Result register(@RequestBody RegisterVo registerVo,HttpSession session){
        //1.进行非空校验
        String phone = registerVo.getPhone();
        String code = registerVo.getCode();
        String password = registerVo.getPassword();
        String nickName = registerVo.getNickName();
        if(StringUtil.isEmpty(phone) || StringUtil.isEmpty(code)||StringUtil.isEmpty(password)||StringUtil.isEmpty(nickName)){
            return Result.build(null, ResultCodeEnum.PARAM_ERROR);
        }
        //2.验证手机号是否为空
        UserInfo userInfo = userInfoService.findUserInfoByPhone(phone);
        if(userInfo!=null){
            return Result.build(null,ResultCodeEnum.PHONE_REGISTER_ERROR);
        }
        //3.验证验证码是否正确
        String trueCode = (String) session.getAttribute(phone);
        if(trueCode!=null){
            if(!code.equals(trueCode)){
                return Result.build(null,ResultCodeEnum.CODE_ERROR);
            }
        }else{
            return Result.build(null,ResultCodeEnum.CODE_ERROR);
        }
        //5.添加数据
        UserInfo info = new UserInfo();
        info.setPhone(phone);
        info.setNickName(nickName);
        info.setPassword(MD5.encrypt(password));
        userInfoService.insert(info);
        return Result.ok();

    }

    @RequestMapping("/login")
    public Result login(@RequestBody LoginVo loginVo,HttpSession session){
        //1.数据非空校验
        String phone = loginVo.getPhone();
        String password = loginVo.getPassword();
        if(StringUtil.isEmpty(phone)|| StringUtil.isEmpty(password)){
            return Result.build(null,ResultCodeEnum.PARAM_ERROR);
        }
        //2.验证手机号是否正确
        UserInfo userInfo = userInfoService.findUserInfoByPhone(phone);
        if(userInfo==null){
            return Result.build(null,ResultCodeEnum.ACCOUNT_ERROR);
        }
        //3.验证密码是否正确
        if(!userInfo.getPassword().equals(MD5.encrypt(password))){
            return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
        }
        //4.验证该账户是否被锁定
        if(userInfo.getStatus()!=1){
            return Result.build(null,ResultCodeEnum.ACCOUNT_LOCK_ERROR);
        }
        //5.返回数据
        Map map = new HashMap();
        map.put("nickName",userInfo.getNickName());
        map.put("password",userInfo.getPassword());

        //6.将当前登录人的对象存储到session中
        session.setAttribute("userInfo",userInfo);
        return Result.ok(map);
    }

    @RequestMapping("/logout")
    public Result logout(HttpSession session){
        session.removeAttribute("userInfo");
        return Result.ok();
    }
}
