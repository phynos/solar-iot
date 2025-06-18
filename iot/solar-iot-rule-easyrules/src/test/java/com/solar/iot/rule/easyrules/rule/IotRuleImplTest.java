package com.solar.iot.rule.easyrules.rule;

import com.solar.iot.model.device.IotDevice;
import com.solar.iot.model.device.file.JsonDeviceBuild;
import org.apache.commons.lang3.RandomUtils;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * 规则引擎-单元测试
 */
@DisplayName("规则引擎-IOT测试")
@SpringBootTest(classes = IotRuleImplTest.class)
class IotRuleImplTest {

    static final Map<String, IotDevice> deviceMap = new HashMap<>();
    static final RulesEngine rulesEngine = new DefaultRulesEngine();
    static final Rules rules = new Rules();
    static final Facts facts = new Facts();

    @BeforeAll
    public static void init() {
        //初始化设备
        IotDevice device1 = new JsonDeviceBuild("22R201512DB21188").fromResource("/product/temp.json").build();
        deviceMap.put(device1.getSn(), device1);
        IotDevice device2 = new JsonDeviceBuild("0102").fromResource("/product/temp.json").build();
        deviceMap.put(device2.getSn(), device2);
        //初始化规则
        IotRuleImpl openRule = new IotRuleBuild().fronJson("/ruler/open.json").build();
        assertNotNull(openRule);
        IotRuleImpl closeRule = new IotRuleBuild().fronJson("/ruler/close.json").build();
        assertNotNull(closeRule);
        //注册规则
        rules.register(openRule);
        rules.register(closeRule);
        //
        facts.put("deviceMap", deviceMap);
    }

    @DisplayName("规则测试")
    @Test
    public void testIot() throws Exception {
        for (int i = 0; i < 100; i++) {
            int t = RandomUtils.nextInt(0, 50);
            deviceMap.get("22R201512DB21188").getAttrs().get("temp").setValue(t + "");
            rulesEngine.fire(rules, facts);
            Thread.sleep(100);
        }
    }

}