package com.zhaoyani.controller;

import com.zhaoyani.entity.HouseUser;
import com.zhaoyani.service.HouseUserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/houseUser")
public class HouseUserController extends BaseController {

    @DubboReference
    private HouseUserService houseUserService;

    @RequestMapping("/create/{houseId}")
    public String create(@PathVariable Long houseId, Map map){
        map.put("houseId",houseId);
        return "houseUser/create";
    }

    @RequestMapping("/save")
    public String save(HouseUser houseUser){
        houseUserService.insert(houseUser);
        return "common/success";
    }

    @RequestMapping("/edit/{houseBrokerId}")
    public String edit(@PathVariable Long houseBrokerId,Map map){
        HouseUser houseUser = houseUserService.getById(houseBrokerId);
        map.put("houseUser",houseUser);
        return "houseUser/edit";
    }

    @RequestMapping("/update")
    public String update(HouseUser houseUser){
        houseUserService.update(houseUser);
        return "common/success";
    }

    @RequestMapping("/delete/{houseId}/{houseUserId}")
    private String delete(@PathVariable Long houseId,@PathVariable Long houseUserId){
        houseUserService.delete(houseUserId);
        return "redirect:/house/show/"+houseId;
    }


}
