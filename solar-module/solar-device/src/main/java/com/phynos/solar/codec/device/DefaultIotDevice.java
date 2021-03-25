package com.phynos.solar.codec.device;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.phynos.solar.codec.product.DefaultIotProduct;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.jexl3.*;
import org.apache.commons.lang3.StringUtils;

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

    @JsonIgnore
    private Map<String, IotAtrribute> exprAttrs;

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
        if (StringUtils.isNotEmpty(atrribute.getExpr())) {
            jexlContext.set(key, value);
            JexlExpression e = jexl.createExpression(atrribute.getExpr());
            Object o = e.evaluate(jexlContext);
            log.debug("表达式结果：{}", o);
        } else {
            atrribute.setValue(value);
        }
        calcExpr();
    }

    @Override
    public void finishInit() {
        //初始化表达式属性
        exprAttrs = new HashMap<>();
        //初始化表达式上下文
        getAttrs().forEach((k, v) -> {
            jexlContext.set(k, v);
            if (StringUtils.isNotEmpty(v.getExpr())) {
                exprAttrs.put(k, v);
            }
        });
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

    private void calcExpr() {
        exprAttrs.forEach((k, v) -> {
            JexlExpression e = jexl.createExpression(v.getExpr());
            Object o = e.evaluate(jexlContext);
            log.debug("表达式结果：{}", o);
            v.setValue(o.toString());
        });
    }

}
