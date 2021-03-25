package com.phynos.solar.rule.easyrules.rule;

import com.phynos.solar.codec.device.IotDevice;
import com.phynos.solar.codec.device.file.JsonDeviceBuild;
import com.phynos.solar.rule.easyrules.action.DeviceAction;
import com.phynos.solar.rule.easyrules.condition.ConditionType;
import com.phynos.solar.rule.easyrules.condition.DeviceConditon;
import com.phynos.solar.rule.easyrules.condition.OperType;
import com.phynos.solar.util.json.JsonUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomUtils;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("规则引擎-IOT测试")
@SpringBootTest(classes = IotRuleTest.class)
class IotRuleTest {

    @DisplayName("规则测试")
    @Test
    public void testIot() throws Exception {
        String json = getJson("/ruler/open.json");
        IotRule openRule = JsonUtil.stringToObject(json, IotRule.class);
        assertNotNull(openRule);
        json = getJson("/ruler/close.json");
        IotRule closeRule = JsonUtil.stringToObject(json, IotRule.class);
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

    private String getJson(String file) throws Exception {
        String json;
        try (InputStream is = getClass().getResourceAsStream(file)) {
            json = IOUtils.toString(is, StandardCharsets.UTF_8);
        }
        return json;
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