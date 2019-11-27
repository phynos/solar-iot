package com.phynos.framework.web.api.controller;

import com.phynos.framework.core.json.JsonResult;
import com.phynos.framework.core.json.ResultCodeEnum;
import com.phynos.framework.core.service.DebugService;
import com.phynos.framework.core.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: Lupc
 * @Date: 2019/10/10 20:24
 **/
@RestController
@RequestMapping("/api/debug")
public class DebugController {

    @Value("${com.phynos.framework.core.time}")
    String buildTime;

    @Autowired
    DebugService debugService;

    @GetMapping("/spring/profiles")
    public String getActiveProfiles() {
        return debugService.getActiveProfiles();
    }

    @PostMapping("/upload")
    public JsonResult testUpload(@RequestParam("file") MultipartFile fileUpload){
        FileUploadUtil.saveFileToDisk(fileUpload,fileUpload.getOriginalFilename(),"upload");
        return JsonResult.code(ResultCodeEnum.OK);
    }

    @GetMapping("/buildTime")
    public String buildTime() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(buildTime);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, 8);
        date = cal.getTime();
        return sdf.format(date);
    }

}
