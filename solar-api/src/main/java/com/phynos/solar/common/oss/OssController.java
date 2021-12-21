package com.phynos.solar.common.oss;

import com.alibaba.excel.util.IoUtils;
import com.phynos.solar.util.json.R;
import io.minio.StatObjectResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2021/12/21 10:47
 */
@RestController
@RequestMapping("/oss")
public class OssController {

    @Autowired
    OssTemplate ossTemplate;

    @RequestMapping("/object/info")
    public R<OssFileInfo> getObjectInfo() {
        OssFileInfo obj = ossTemplate.getObjectInfo("test", "quasar-logo.png");
        return R.data(obj);
    }

    @RequestMapping("/object/download")
    public void dowanlod(String objectName, HttpServletResponse response) throws IOException {
        try (InputStream is = ossTemplate.getObject("test", objectName);
             OutputStream os = response.getOutputStream()) {
            OssFileInfo obj = ossTemplate.getObjectInfo("test", objectName);
            response.setContentType(obj.getContentType());
            response.setContentLengthLong(obj.getFileSize());
            IOUtils.copy(is, os);
        }
    }


    @RequestMapping("/object/url")
    public String getURL() {
        return ossTemplate.getObjectURL("test", "quasar-logo.png");
    }

    @RequestMapping("/object/upload")
    public R<OssFileInfo> dowanlod(@RequestBody MultipartFile file) throws IOException {
        OssFileInfo ossFileInfo = ossTemplate.saveObjectRealName("test", "apk/", file);
        return R.data(ossFileInfo);
    }

}
