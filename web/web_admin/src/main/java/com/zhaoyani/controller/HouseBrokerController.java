package com.zhaoyani.controller;

import com.zhaoyani.entity.Admin;
import com.zhaoyani.entity.House;
import com.zhaoyani.entity.HouseBroker;
import com.zhaoyani.service.HouseBrokerService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/houseBroker")
public class HouseBrokerController extends BaseController {

    @DubboReference
    private HouseBrokerService houseBrokerService;



    @RequestMapping("/create/{houseId}")
    public String create(@PathVariable Long houseId, Map map){
        map.put("houseId",houseId);
        List<Admin> adminList = houseBrokerService.findHouseOtherBroker(houseId);
        map.put("adminList",adminList);
        return "houseBroker/create";
    }

    @RequestMapping("/save")
    public String sava(Long houseId,Long brokerId){
        HouseBroker houseBroker = new HouseBroker();
        houseBroker.setHouseId(houseId);
        houseBroker.setBrokerId(brokerId);
        houseBrokerService.insert(houseBroker);
        return "common/success";
    }

    @RequestMapping("/delete/{houseId}/{houseBrokerId}")
    public String delete(@PathVariable Long houseId,@PathVariable Long houseBrokerId){
        houseBrokerService.delete(houseBrokerId);
        return "redirect:/house/show/"+houseId;
    }

}
