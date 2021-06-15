package com.phynos.solar.front.module.tenancy;

import com.solar.iot.model.device.IotDevice;
import com.solar.iot.rule.easyrules.rule.IotRuleImpl;
import lombok.Getter;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 租户类
 * <p>
 * 每个租户拥有独立的设备列表，规则列表，规则引擎（包含上下文）
 * </p>
 *
 * @author lupc
 * @date 2021/3/25 19:24
 */
public class IotTenancy {
    /**
     * 租户编号
     */
    @Getter
    private final String tenancyId;
    /**
     * 租户的设备列表
     */
    private final Map<String, IotDevice> deviceMap = new HashMap<>();
    /**
     * 租户的规则
     */
    private final List<IotRuleImpl> rulers = new ArrayList<>();
    private final RulesEngine rulesEngine = new DefaultRulesEngine();
    private final Facts facts = new Facts();
    private final Rules rules = new Rules();

    public IotTenancy(String tenancyId) {
        this.tenancyId = tenancyId;
        facts.put("deviceMap", deviceMap);
    }

    public IotDevice getDevice(String sn) {
        return deviceMap.get(sn);
    }

    public void addDevice(IotDevice... devices) {
        for (IotDevice device : devices) {
            deviceMap.put(device.getSn(), device);
        }
    }

    public void addRule(IotRuleImpl... iotRuleImpls) {
        for (IotRuleImpl iotRuleImpl : iotRuleImpls) {
            rulers.add(iotRuleImpl);
        }
        //注册规则
        rules.register(iotRuleImpls);
    }

    public void fireRules() {
        rulesEngine.fire(rules, facts);
    }

}
