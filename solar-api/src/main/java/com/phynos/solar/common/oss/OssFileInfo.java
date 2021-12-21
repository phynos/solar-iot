package com.phynos.solar.common.oss;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.minio.StatObjectResponse;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 文件（对象）持久化信息
 *
 * @author lupc
 * @date 2021/12/21 09:27
 */
@Data
public class OssFileInfo {

    /**
     * 对象名称，唯一
     */
    private String objectName;

    private String fileRealName;

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
