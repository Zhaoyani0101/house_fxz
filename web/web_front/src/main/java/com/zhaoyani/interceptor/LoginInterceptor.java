package com.zhaoyani.interceptor;

import com.zhaoyani.entity.UserInfo;
import com.zhaoyani.result.Result;
import com.zhaoyani.result.ResultCodeEnum;
import com.zhaoyani.util.WebUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取userInfo对象
        HttpSession session = request.getSession();
        UserInfo userInfo =(UserInfo) session.getAttribute("userInfo");
        //2.进行判断
        if(userInfo==null){
            WebUtil.writeJSON(response,Result.build(null,ResultCodeEnum.LOGIN_AUTH));
            return false;
        }

        return true;
    }
}
