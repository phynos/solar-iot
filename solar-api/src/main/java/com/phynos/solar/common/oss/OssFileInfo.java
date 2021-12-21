package com.phynos.solar.common.oss;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.minio.StatObjectResponse;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2021/12/21 09:27
 */
@Data
public class OssFileInfo {

    private String objectName;

    private String fileName;

    private long fileSize;

    private String contentType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime lastModified;

    public static OssFileInfo fromMinioStat(StatObjectResponse stat) {
        if(stat == null || stat.deleteMarker()) {
            return null;
        }
        OssFileInfo ossFileInfo = new OssFileInfo();
        ossFileInfo.setObjectName(stat.object());
        ossFileInfo.setFileSize(stat.size());
        ossFileInfo.setLastModified(stat.lastModified().toLocalDateTime());
        ossFileInfo.setContentType(stat.contentType());
        return ossFileInfo;
    }

}
