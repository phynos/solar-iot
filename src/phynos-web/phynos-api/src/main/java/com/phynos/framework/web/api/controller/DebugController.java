package com.phynos.framework.web.api.controller;

import com.phynos.framework.core.json.JsonResult;
import com.phynos.framework.core.json.ResultCodeEnum;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @Author: Lupc
 * @Date: 2019/10/10 20:24
 **/
@RestController
@RequestMapping("/api/debug")
public class DebugController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("/upload")
    public JsonResult testUpload(@RequestParam("file") MultipartFile fileUpload){
        //获取文件名
        String fileName = fileUpload.getOriginalFilename();
        //获取文件后缀名
        String suffixName = FilenameUtils.getExtension(fileName);
        //重新生成文件名
        try {
            //指定本地文件夹存储图片
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if(!path.exists()) {
                path = new File("");
            }
            logger.debug(path.getAbsolutePath());
            File upload = new File(path.getAbsolutePath(),"static/CaseUpload/");
            if(!upload.exists()) {
                upload.mkdirs();
            }
            //将图片保存到static文件夹里
            fileUpload.transferTo(new File(upload.getAbsolutePath(),fileName));
            return JsonResult.code(ResultCodeEnum.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.code(ResultCodeEnum.OPERATION_FAILED , e.getMessage());
        }
    }

}
