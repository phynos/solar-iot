package com.phynos.framework.web.api.controller;

import com.phynos.framework.core.json.JsonResult;
import com.phynos.framework.core.json.ResultCodeEnum;
import com.phynos.framework.core.util.FileUploadUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Lupc
 * @Date: 2019/10/10 20:24
 **/
@RestController
@RequestMapping("/api/debug")
public class DebugController {

    @PostMapping("/upload")
    public JsonResult testUpload(@RequestParam("file") MultipartFile fileUpload){
        FileUploadUtil.saveFileToDisk(fileUpload,fileUpload.getOriginalFilename(),"upload");
        return JsonResult.code(ResultCodeEnum.OK);
    }

}
