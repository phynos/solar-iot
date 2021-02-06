package com.phynos.solar.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @Author: Lupc
 * @Date: 2019/10/28 17:21
 **/
public class FileUploadUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUploadUtil.class.getSimpleName());

    public static boolean saveFileToDisk(
            MultipartFile fileUpload,
            String fileName,
            String ...parentDirs) {
        try {
            //指定本地文件夹存储图片
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if (!path.exists()) {
                path = new File("");
            }
            logger.debug(path.getAbsolutePath());
            File upload = new File(path.getAbsolutePath(), "static/");
            for(String dir : parentDirs) {
                upload = new File(upload, dir + "/");
            }
            if (!upload.exists()) {
                upload.mkdirs();
            }
            //将图片保存到static文件夹里
            fileUpload.transferTo(new File(upload.getAbsolutePath(), fileName));
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e.getCause());
            return false;
        }
    }

    public static void deleteFileFromDisk(String fileName,
                                          String ...parentDirs) {
        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            File upload = new File(path.getAbsolutePath(), "static/");
            for(String dir : parentDirs) {
                upload = new File(upload, dir + "/");
            }
            File file = new File(upload.getAbsolutePath(), fileName);
            file.delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

