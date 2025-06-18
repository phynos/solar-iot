package com.phynos.doctools.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2025/3/20 10:12
 */
@Controller
@RequestMapping("/files")
public class FileDownloadController {

    @Value("${file.path}")
    private String sysFilePath;

    @GetMapping("/{fileName}")
    public ResponseEntity<Resource> viewPdf(@PathVariable("fileName") String fileName) {
        // 指定本地PDF文件的路径
        String filePath = sysFilePath + "/" + fileName; // 请替换为实际路径
        File file = new File(filePath);

        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        Resource resource = new FileSystemResource(file);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getName() + "\"")
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(resource);
    }

}
