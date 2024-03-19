package com.zhaoyani.controller;


import com.github.pagehelper.PageInfo;
import com.zhaoyani.entity.UserInfo;
import com.zhaoyani.result.Result;
import com.zhaoyani.service.UserFollowService;
import com.zhaoyani.vo.UserFollowVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
@RequestMapping("/userFollow")
public class UserFollowController {

    @DubboReference
    private UserFollowService userFollowService;

    @RequestMapping("/auth/follow/{houseId}")
    public Result follow(@PathVariable Long houseId, HttpSession session){
        //1.获取当前登录人对象
        UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
        //2.调用业务层进行数据添加
        userFollowService.follow(userInfo.getId(),houseId);
        return Result.ok();
    }

    @RequestMapping("/auth/list/{pageNum}/{pageSize}")
    public Result list(@PathVariable Integer pageNum,@PathVariable Integer pageSize,HttpSession session){
        //1.获取当前登录人对象
        UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
        PageInfo<UserFollowVo> page = userFollowService.findFollowByUser(pageNum, pageSize, userInfo.getId());
        return Result.ok(page);
    }

    @RequestMapping("/auth/cancelFollow/{userFollowId}")
    public Result cancelFollow(@PathVariable Long userFollowId){
        //1.获取当前登录人对象
        userFollowService.delete(userFollowId);
        return Result.ok();
    }
}
