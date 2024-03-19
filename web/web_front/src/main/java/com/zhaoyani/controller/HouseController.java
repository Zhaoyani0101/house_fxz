package com.zhaoyani.controller;


import com.github.pagehelper.PageInfo;
import com.zhaoyani.entity.*;
import com.zhaoyani.result.Result;
import com.zhaoyani.service.*;
import com.zhaoyani.vo.HouseQueryVo;
import com.zhaoyani.vo.HouseVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/house")
@Controller
@ResponseBody
public class HouseController  extends BaseController{

    @DubboReference
    private HouseService houseService;

    @DubboReference
    private CommunityService communityService;

    @DubboReference
    private HouseBrokerService houseBrokerService;

    @DubboReference
    private HouseImageService houseImageService;

    @DubboReference
    private AdminService adminService;

    @DubboReference
    private UserFollowService userFollowService;

    @RequestMapping("/list/{pageNum}/{pageSize}")
    public Result list(@PathVariable Integer pageNum,
                       @PathVariable Integer pageSize,
                       @RequestBody HouseQueryVo houseQueryVo){

        PageInfo<HouseVo> page = houseService.findListByQueryVo(pageNum, pageSize, houseQueryVo);

        return Result.ok(page);

    }

    @RequestMapping("/info/{houseId}")
    public Result info(@PathVariable Long houseId, HttpSession session){
        //1.当前房源信息
        House house = houseService.getById(houseId);
        //2.当前房源小区信息
        Community community = communityService.getById(house.getCommunityId());
        //3.当前房源经纪人信息
        List<HouseBroker> houseBrokerList = houseBrokerService.findBrokerByHouseId(houseId);
        if(houseBrokerList==null || houseBrokerList.size()<0){
            Admin admin = adminService.getById(1);
            HouseBroker defaultBroker = new HouseBroker();
            defaultBroker.setHouseId(houseId);
            defaultBroker.setBrokerId(admin.getId());
            defaultBroker.setBrokerName(admin.getName());
            defaultBroker.setBrokerHeadUrl(admin.getHeadUrl());
            houseBrokerList.add(defaultBroker);
        }

        //4.当前房源图片
        List<HouseImage> houseImage1List = houseImageService.findImageByHouseId(houseId, 1);
        //5.当前房源是否被关注
        boolean isFollow = false;


        UserFollow userFollow = new UserFollow();
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if(userInfo != null){
            UserFollow follow = userFollowService.isFollow(userInfo.getId(), houseId);
            if(follow!=null){
                isFollow =true;
            }
        }
        Map map = new HashMap();
        map.put("house",house);
        map.put("community",community);
        map.put("houseBrokerList",houseBrokerList);
        map.put("houseImage1List",houseImage1List);

        map.put("isFollow",isFollow);


       return Result.ok(map);
    }
}
