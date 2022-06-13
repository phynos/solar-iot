package com.phynos.solar.oss;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 常见content-type
 *
 * @author lupc
 * @date 2022/6/13 14:37
 */
@Getter
public enum ViewContentType {

    /**
     * 正常二进制下载类型
     */
    DEFAULT("default", "application/octet-stream"),
    JSON("json", "application/json"),
    XML("xml", "text/xml"),
    HTML("html", "text/html"),
    PDF("pdf", "application/pdf"),
    MSWORD("msword", "application/msword"),
    JPG("jpg", "image/jpeg"),
    TIFF("tiff", "image/tiff"),
    GIF("gif", "image/gif"),
    JFIF("jfif", "image/jpeg"),
    PNG("png", "image/png"),
    TIF("tif", "image/tiff"),
    ICO("ico", "image/x-icon"),
    JPEG("jpeg", "image/jpeg"),
    WBMP("wbmp", "image/vnd.wap.wbmp"),
    FAX("fax", "image/fax"),
    NET("net", "image/pnetvue"),
    JPE("jpe", "image/jpeg"),
    RP("rp", "image/vnd.rn-realpix");

    private final String prefix;

    private final String type;

    ViewContentType(String prefix, String type) {
        this.prefix = prefix;
        this.type = type;
    }

    public static String getContentType(String prefix) {
        if (StringUtils.isEmpty(prefix)) {
            return DEFAULT.getType();
        }
        prefix = prefix.substring(prefix.lastIndexOf(".") + 1);
        for (ViewContentType value : ViewContentType.values()) {
            if (prefix.equalsIgnoreCase(value.getPrefix())) {
                return value.getType();
            }
        }
        return DEFAULT.getType();
    }

}
