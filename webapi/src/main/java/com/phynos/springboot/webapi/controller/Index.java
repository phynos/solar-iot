package com.phynos.springboot.webapi.controller;

import com.phynos.springboot.webapi.model.JsonBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class Index {

    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/home")
    public JsonBase test() {
        logger.info("home");
        return new JsonBase(0,"home");
    }

    @RequestMapping("/login")
    public JsonBase login() {
        logger.info("登录");
        return new JsonBase(0,"login success");
    }

    @RequestMapping("/logout")
    public JsonBase logout(){
        logger.info("注销");
        return new JsonBase(0,"logout success");
    }

    @RequestMapping("/profile")
    public JsonBase profile() {
        logger.info("个人信息查询");
        return new JsonBase(0,"profile");
    }

    @PostMapping("/upload")
    public JsonBase upload(@RequestParam("file") MultipartFile file) {

        return new JsonBase(0,"upload");
    }

}

