package com.zhaoyani.controller;

import com.github.pagehelper.PageInfo;
import com.zhaoyani.entity.*;
import com.zhaoyani.service.*;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/house")
public class HouseController extends BaseController {

    @DubboReference
    private CommunityService communityService;

    @DubboReference
    private HouseService houseService;

    @DubboReference
    private DictService dictService;

    @DubboReference
    private HouseImageService houseImageService;

    @DubboReference
    private HouseBrokerService houseBrokerService;
    
    @DubboReference
    private HouseUserService houseUserService;

    @RequestMapping
    public String findALL(HttpServletRequest request, Map map){
        //当前页的房源信息
        Map<String, Object> filters = getFilters(request);
        PageInfo<House> page = houseService.findPage(filters);
        map.put("page",page);
        map.put("filters",filters);
        //高级查询信息
        getValues(map);
        return "house/index";
    }

    @RequestMapping("/create")
    public String create(Map map){
        getValues(map);
        return "house/create";
    }

    @RequestMapping("/save")
    public String sava(House house){
        houseService.insert(house);
        return "common/success";
    }

    @RequestMapping("/edit/{houseId}")
    public String edit(@PathVariable Long houseId,Map map){
        getValues(map);
        House house = houseService.getById(houseId);
        map.put("house",house);
        return "house/edit";
    }

    @RequestMapping("/update")
    public String update(House house){
        houseService.update(house);
        return "common/success";
    }

    @RequestMapping("/delete/{houseId}")
    public String delete(@PathVariable Long houseId){
        houseService.delete(houseId);
        return "redirect:/house";
    }

    @RequestMapping("/publish/{houseId}/{status}")
    public String publish(@PathVariable Long houseId,@PathVariable Integer status){
         houseService.publish(houseId,status);
        return "redirect:/house";
    }

    @RequestMapping("/show/{houseId}")
    public String show(@PathVariable Long houseId,Map map){
        //根据当前房源ID查找小区信息
        House house = houseService.getById(houseId);
        Community community = communityService.getById(house.getCommunityId());
        map.put("house",house);
        map.put("community",community);
        //根据当前房源ID查找房源图片和房产图片
        List<HouseImage> houseImage1List = houseImageService.findImageByHouseId(houseId, 1);
        List<HouseImage> houseImage2List = houseImageService.findImageByHouseId(houseId, 2);
        map.put("houseImage1List",houseImage1List);
        map.put("houseImage2List",houseImage2List);
        //查找当前房源的经纪人信息
        List<HouseBroker> houseBrokerList = houseBrokerService.findBrokerByHouseId(houseId);
        map.put("houseBrokerList",houseBrokerList);
        //查看当前房源的房东信息
        List<HouseUser> houseUserList = houseUserService.findUserByHouseId(houseId);
        map.put("houseUserList",houseUserList);
        return "house/show";
    }



    public void getValues(Map map){
        //所有小区信息
        List<Community> communityList = communityService.findAll();
        map.put("communityList",communityList);
        //户型、楼层、建筑风格、朝向、装修情况、房屋用途
        List<Dict> houseTypeList = dictService.findDictByCode("houseType");
        List<Dict> floorList = dictService.findDictByCode("floor");
        List<Dict> buildStructureList = dictService.findDictByCode("buildStructure");
        List<Dict> directionList = dictService.findDictByCode("direction");
        List<Dict> decorationList = dictService.findDictByCode("decoration");
        List<Dict> houseUseList = dictService.findDictByCode("houseUse");
        map.put("houseTypeList",houseTypeList);
        map.put("floorList",floorList);
        map.put("buildStructureList",buildStructureList);
        map.put("directionList",directionList);
        map.put("decorationList",decorationList);
        map.put("houseUseList",houseUseList);
    }


}
