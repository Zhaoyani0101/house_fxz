package com.zhaoyani.controller;



import com.github.pagehelper.PageInfo;
import com.zhaoyani.entity.Community;
import com.zhaoyani.entity.Dict;
import com.zhaoyani.service.CommunityService;
import com.zhaoyani.service.DictService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.support.AbstractMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/community")
public class CommunityController extends BaseController{

    @DubboReference
    private CommunityService communityService;
    @DubboReference
    private DictService dictService;

    @RequestMapping
    public String findAll(HttpServletRequest request,Map map){
        Map<String, Object> filters = getFilters(request);
        PageInfo<Community> page = communityService.findPage(filters);
        map.put("page",page);
        map.put("filters",filters);
        List<Dict> areaList = dictService.findDictByCode("beijing");
        map.put("areaList",areaList);
        return "community/index";
    }

    @RequestMapping("/create")
    public String create(Map map){
        List<Dict> areaList = dictService.findDictByCode("beijing");
        map.put("areaList",areaList);
        return "community/create";
    }

    @RequestMapping("/save")
    public String save(Community community){
        communityService.insert(community);
        return "common/success";
    }

    @RequestMapping("/edit/{communityId}")
    public String edit(@PathVariable Long communityId,Map map){
        Community community = communityService.getById(communityId);
        List<Dict> areaList = dictService.findDictByCode("beijing");
        map.put("community",community);
        map.put("areaList",areaList);
        return "community/edit";
    }

    @RequestMapping("/update")
    public String update(Community community){
        communityService.update(community);
        return "common/success";
    }

    @RequestMapping("/delete/{communityId}")
    public String delete(@PathVariable Long communityId){
        communityService.delete(communityId);
        return "redirect:/community";
    }
}
