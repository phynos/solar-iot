package com.phynos.solar.rule.easyrules.rule;

import com.phynos.solar.codec.device.IotDevice;
import com.phynos.solar.codec.device.file.JsonDeviceBuild;
import com.phynos.solar.rule.easyrules.action.DeviceAction;
import com.phynos.solar.rule.easyrules.condition.ConditionType;
import com.phynos.solar.rule.easyrules.condition.DeviceConditon;
import com.phynos.solar.rule.easyrules.condition.OperType;
import org.apache.commons.lang3.RandomUtils;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("规则引擎-IOT测试")
@SpringBootTest(classes = IotRuleTest.class)
class IotRuleTest {

    @DisplayName("")
    @Test
    public void testIot() throws InterruptedException {
        IotRule openRule = new IotRule();
        openRule.setName("空气自动开启");
        openRule.setDescription("温度传感器数据大于28且小于40自动开启空调");
        openRule.setConditionType(ConditionType.或);
        //条件
        List<DeviceConditon> conditonList = new ArrayList<>(2);
        DeviceConditon deviceConditon = new DeviceConditon();
        deviceConditon.setOperType(OperType.大于);
        deviceConditon.setDeviceSn("0101");
        deviceConditon.setSignalKey("temp");
        deviceConditon.setLimit("26");
        conditonList.add(deviceConditon);
        openRule.setDeviceConditons(conditonList);

        //行为
        List<DeviceAction> actionList = new ArrayList<>();
        DeviceAction action = new DeviceAction();
        action.setDeviceSn("0201");
        action.setParameter("");
        action.setCommand("开启空调");
        actionList.add(action);
        openRule.setActions(actionList);

        IotRule closeRule = new IotRule();
        closeRule.setName("空气自动关闭");
        closeRule.setDescription("温度传感器数据小于16或大于40自动开启空调");
        closeRule.setConditionType(ConditionType.与);
        //条件
        List<DeviceConditon> conditons2 = new ArrayList<>(2);
        closeRule.setDeviceConditons(conditons2);
        //行为
        List<DeviceAction> actions2 = new ArrayList<>();
        closeRule.setActions(actions2);

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
            Thread.sleep(300);
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