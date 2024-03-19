package com.zhaoyani.controller;


import com.zhaoyani.entity.HouseImage;
import com.zhaoyani.result.Result;
import com.zhaoyani.service.HouseImageService;
import com.zhaoyani.util.QiniuUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/houseImage")
public class HouseImageController {

    @DubboReference
    private HouseImageService houseImageService;

    @RequestMapping("/uploadShow/{houseId}/{type}")
    public String showUpload(@PathVariable Long houseId, @PathVariable Integer type, Map map){
        map.put("houseId",houseId);
        map.put("type",type);
        return "house/upload";
    }

    @RequestMapping("/upload/{houseId}/{type}")
    @ResponseBody
    public Result upload(@PathVariable Long houseId,
                         @PathVariable Integer type,
                         @RequestParam("file") MultipartFile[] files) throws IOException {
        for (MultipartFile file : files) {
            //1.将图片上传到七牛云
            String fileName = UUID.randomUUID().toString();
            QiniuUtil.upload2Qiniu(file.getBytes(),fileName);
            //2.将图片信息保存到数据库
            HouseImage houseImage = new HouseImage();
            houseImage.setHouseId(houseId);
            houseImage.setImageName(fileName);
            houseImage.setImageUrl("http://s9wk97c0p.hn-bkt.clouddn.com/"+fileName);
            houseImage.setType(type);
            houseImageService.insert(houseImage);
        }
        return Result.ok();
    }


    @RequestMapping("/delete/{houseId}/{houseImageId}")
    public String delete(@PathVariable Long houseId,@PathVariable Long houseImageId){
        //1.删除七牛云空间中的图片
        HouseImage houseImage = houseImageService.getById(houseImageId);
        QiniuUtil.deleteFileFromQiniu(houseImage.getImageName());
        //2.删除数据库中的图片信息（逻辑删除）
        houseImageService.delete(houseImageId);
        return "redirect:/house/show/"+houseId;
    }
}
