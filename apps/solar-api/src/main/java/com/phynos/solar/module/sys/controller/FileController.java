package com.phynos.solar.module.sys.controller;

import com.phynos.solar.oss.OssException;
import com.phynos.solar.oss.OssTemplate;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.HandlerMapping;

/**
 * OSS接口
 *
 * @author lupc
 * @date 2022/6/13 16:01
 */
@Controller
public class FileController {

    @Autowired
    OssTemplate ossTemplate;

    @GetMapping("/file/{bucketName}/**")
    public void download(
            @PathVariable("bucketName") String bucketName,
            HttpServletResponse response,
            HttpServletRequest request) throws OssException {
        String objectName = extractPathFromPattern(request);
        ossTemplate.download(response, bucketName, objectName);
    }

    private String extractPathFromPattern(final HttpServletRequest request) {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        return new AntPathMatcher().extractPathWithinPattern(bestMatchPattern, path);
    }

}
