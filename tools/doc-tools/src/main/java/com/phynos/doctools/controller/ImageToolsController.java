package com.phynos.doctools.controller;

import com.phynos.doctools.images.ImageToPdf;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2025/3/19 19:22
 */
@RestController
@RequestMapping("/image")
public class ImageToolsController {

    @Value("${file.path}")
    private String filePath;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(
            @RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(fileName);
        if (!StringUtils.equalsAnyIgnoreCase(extension, "png", "jpg", "jpeg")) {
            Map<String, Object> data = new HashMap<>();
            data.put("msg", "仅仅支持转换jpg和png格式图片");
            return ResponseEntity.ok(data);
        }
        String baseName = FilenameUtils.getBaseName(fileName);
        String fileRealName = UUID.randomUUID().toString() + ".pdf";
        try (OutputStream os = new FileOutputStream(filePath + "/" + fileRealName)) {
            ImageToPdf.imageToPdf(file.getInputStream(), os);
            Map<String, Object> data = new HashMap<>();
            data.put("fileLocation", "/tools/files/" + fileRealName);
            data.put("fileRealName", fileRealName);
            data.put("fileName", baseName + ".pdf");
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> data = new HashMap<>();
        data.put("msg", "转换失败");
        return ResponseEntity.ok(data);
    }

    @PostMapping("/uploadDirect")
    public void uploadDirect(
            @RequestParam("file") MultipartFile file,
            HttpServletResponse response) {
        // 将文件内容写入响应的输出流
        try (OutputStream outputStream = response.getOutputStream()) {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=abc.pdf");
            ImageToPdf.imageToPdf(file.getInputStream(), outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }


}
