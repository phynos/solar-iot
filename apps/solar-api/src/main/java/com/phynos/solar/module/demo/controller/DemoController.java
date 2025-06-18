package com.phynos.solar.module.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.output.ChunkedOutputStream;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2023/7/14 11:44
 */
@Slf4j
@Controller
public class DemoController {

    @GetMapping("/demo/chunk")
    public void chunk(HttpServletResponse response) throws IOException, InterruptedException {
        response.getWriter().write("first pack");
        response.flushBuffer();

        Thread.sleep(3000);

        response.getWriter().write("second pack");
        response.flushBuffer();

        Thread.sleep(3000);

        response.getWriter().write("third pack");
        response.flushBuffer();
    }

}
