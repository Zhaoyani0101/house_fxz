package com.zhaoyani.controller;


import com.zhaoyani.entity.Dict;
import com.zhaoyani.result.Result;
import com.zhaoyani.service.DictService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/dict")
@ResponseBody
public class DictController {

    @DubboReference
    private DictService dictService;

    @RequestMapping("/findListByDictCode/{code}")
    public Result findListByDictCode(@PathVariable String code){
        List<Dict> dictList = dictService.findDictByCode(code);
        return Result.ok(dictList);
    }

    @RequestMapping("/findListByParentId/{parentId}")
    public Result findListByParentId(@PathVariable Long parentId){
        List<Dict> dictList = dictService.findDictByParentId(parentId);
        return Result.ok(dictList);
    }
}
