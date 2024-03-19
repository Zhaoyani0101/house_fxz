package com.zhaoyani.controller;


import com.zhaoyani.entity.Dict;
import com.zhaoyani.result.Result;
import com.zhaoyani.service.DictService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dict")
public class DictController {

    @DubboReference
    private DictService dictService;

    @RequestMapping
    public String toIndex(){
        return "dict/index";
    }


    @RequestMapping("/findZnodes")
    @ResponseBody
    public Result findZnodes(@RequestParam(defaultValue = "0") Long id){

        List<Map<String, Object>> znodes = dictService.findZnodes(id);

        return Result.ok(znodes);
    }
    
    @RequestMapping("/findListByParentId/{areaId}")
    @ResponseBody
    public Result findListByParentId(@PathVariable Long areaId){
        List<Dict> plateList = dictService.findDictByParentId(areaId);
        return Result.ok(plateList);

    }
}
