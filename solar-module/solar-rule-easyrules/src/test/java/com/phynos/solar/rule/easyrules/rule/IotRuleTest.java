package com.phynos.solar.rule.easyrules.rule;

import com.phynos.solar.codec.device.IotDevice;
import com.phynos.solar.codec.device.file.JsonDeviceBuild;
import org.apache.commons.lang3.RandomUtils;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("规则引擎-IOT测试")
@SpringBootTest(classes = IotRuleTest.class)
class IotRuleTest {

    @DisplayName("规则测试")
    @Test
    public void testIot() throws Exception {
        IotRule openRule = new IotRuleBuild().fronJson("/ruler/open.json").build();
        assertNotNull(openRule);
        IotRule closeRule = new IotRuleBuild().fronJson("/ruler/close.json").build();
        assertNotNull(closeRule);
        //注册规则
        Rules rules = new Rules();
        rules.register(openRule);
        rules.register(closeRule);

        Facts facts = new Facts();
        final Map<String, IotDevice> deviceMap = new HashMap<>();
        IotDevice device1 = createDevice1("0101");
        deviceMap.put(device1.getSn(), device1);
        IotDevice device2 = createDevice2("0201");
        deviceMap.put(device2.getSn(), device2);
        facts.put("deviceMap", deviceMap);
        RulesEngine rulesEngine = new DefaultRulesEngine();
        for (int i = 0; i < 100; i++) {
            int t = RandomUtils.nextInt(0, 50);
            deviceMap.get("0101").getAttrs().get("temp").setValue(t + "");
            rulesEngine.fire(rules, facts);
            Thread.sleep(100);
        }
    }

    private static IotDevice createDevice1(String sn) {
        IotDevice device = new JsonDeviceBuild(sn).fromResource("/product/temp.json").build();
        return device;
    }

    private static IotDevice createDevice2(String sn) {
        IotDevice device = new JsonDeviceBuild(sn).fromResource("/product/box.json").build();
        return device;
    }

}