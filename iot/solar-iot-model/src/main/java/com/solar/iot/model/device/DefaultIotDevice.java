package com.solar.iot.model.device;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.solar.iot.model.product.DefaultIotProduct;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.jexl3.*;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * IOT设备
 *
 * @author by lupc
 * @date 2021-02-25 9:45
 */
@Slf4j
public class DefaultIotDevice implements IotDevice {

    //创建表达式引擎对象
    static final JexlEngine jexl = new JexlBuilder().create();
    JexlContext jexlContext = new MapContext();

    private String sn;

    @Getter
    @Setter
    private DefaultIotProduct product;

    /**
     * 拥有表达式的属性缓存
     */
    @JsonIgnore
    private final Map<String, IotAtrribute> exprAttrCache = new HashMap<>();

    /**
     * jexl表达式预编译缓存
     */
    @JsonIgnore
    private final Map<String, JexlExpression> jexlExprCache = new HashMap<>();

    @Getter
    @Setter
    private String location;
    @Getter
    @Setter
    private String lat;

    @Getter
    @Setter
    private String lng;

    public DefaultIotDevice(String sn) {
        this.sn = sn;
    }

    @Override
    public String getSn() {
        return sn;
    }

    @Override
    public void refresh(String key, String value) {
        IotAtrribute atrribute = getAttrs().get(key);
        if (atrribute == null) {
            log.warn("无法找到属性，key={}", key);
            return;
        }
        if (StringUtils.isEmpty(atrribute.getExpr())) {
            atrribute.setValue(value);
        }
        calcExpr();
    }

    @Override
    public void finishInit() {
        //初始化表达式上下文
        getAttrs().forEach((k, attribute) -> {
            jexlContext.set(k, attribute);
            if (StringUtils.isNotEmpty(attribute.getExpr())) {
                //缓存有表达式的属性
                exprAttrCache.put(k, attribute);
                //预编译表达式
                JexlExpression expr = jexl.createExpression(attribute.getExpr());
                jexlExprCache.put(k, expr);
            }
        });
    }

    @Override
    public boolean getOnline() {
        return false;
    }

    @Override
    public LocalDateTime getLastDataTime() {
        return LocalDateTime.now();
    }

    @Override
    public String getCode() {
        return product.getCode();
    }

    @Override
    public String getVendor() {
        return product.getVendor();
    }

    @Override
    public String getVersion() {
        return product.getVersion();
    }

    @Override
    public Map<String, IotAtrribute> getAttrs() {
        return product.getAttrs();
    }

    @Override
    public Map<String, IotEvent> getEvents() {
        return product.getEvents();
    }

    @Override
    public Map<String, IotService> getServices() {
        return product.getServices();
    }

    /**
     * 计算所有的 表达式属性
     */
    private void calcExpr() {
        exprAttrCache.forEach((k, v) -> {
            JexlExpression e = jexlExprCache.get(k);//直接从缓存获取预编译的表达式
            Object o = e.evaluate(jexlContext);
            log.debug("信号名称：{}，表达式：{}，结果：{}", v.getName(), v.getExpr(), o);
            v.setValue(o.toString());
        });
    }

}
