package com.phynos.solar.common.oss;

import com.phynos.solar.util.json.R;
import io.minio.StatObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void dowanlod() {

    }


    @RequestMapping("/object/url")
    public String getURL() {
        return ossTemplate.getObjectURL("test", "quasar-logo.png");
    }

}
