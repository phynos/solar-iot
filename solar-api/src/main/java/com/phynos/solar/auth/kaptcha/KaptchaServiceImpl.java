package com.phynos.solar.auth.kaptcha;

import com.google.code.kaptcha.Producer;
import com.phynos.solar.util.tools.UuidUtil;
import com.phynos.solar.util.json.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.imageio.ImageIO;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class KaptchaServiceImpl implements KaptchaService {

    public final static ConcurrentHashMap<String, String> CACHE = new ConcurrentHashMap<>();

    @Autowired
    Producer captchaProducer;

    @Override
    public void kaptcha(HttpServletResponse response) {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "create_date-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String capText = captchaProducer.createText();//获取数值，保存起来，用于提交验证
        String sessionId = UuidUtil.uid();
        saveKaptcha(sessionId, capText);
        response.setHeader("RLES_SESSION", sessionId);
        BufferedImage bi = captchaProducer.createImage(capText);
        try (OutputStream out = response.getOutputStream()) {
            ImageIO.write(bi, "jpg", out);
        } catch (IOException e) {
            log.error(e.getMessage(), e.getCause());
        }
    }

    @Override
    public ResultCodeEnum valid(String code) {
        //先判断需不需要验证码
        //检查传入参数
        if (StringUtils.isEmpty(code)) {
            return ResultCodeEnum.IMAGE_VALID_CODE_REQUIRED;
        }
        //检查是否生成过验证码
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //临时会话ID
        String sessionId = request.getHeader("RLES_SESSION");
        //通过会话ID，到缓存中查找验证码
        String verifycode = getKaptcha(sessionId);
        if (StringUtils.isEmpty(verifycode)) {
            return ResultCodeEnum.IMAGE_VALID_CODE_REQUIRED;
        }
        //
        clean(sessionId);
        //比对验证码
        if (!StringUtils.equalsIgnoreCase(code, verifycode)) {
            return ResultCodeEnum.VALID_CODE_ERROR;
        }
        return ResultCodeEnum.OK;
    }

    public void clean(String sessionId) {
        CACHE.remove(sessionId);
    }

    private void saveKaptcha(String sessionId, String capText) {
        CACHE.put(sessionId, capText);
    }

    private String getKaptcha(String sessionId) {
        if (StringUtils.isEmpty(sessionId)) {
            return null;
        }
        return CACHE.get(sessionId);
    }

    public static HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        return request;
    }

    public static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }

}
