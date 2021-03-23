package com.phynos.solar.rule.easyrules;

import com.phynos.solar.codec.device.DefaultIotDevice;
import com.phynos.solar.codec.device.IotAtrribute;
import com.phynos.solar.codec.device.IotDevice;
import com.phynos.solar.codec.device.file.JsonDeviceBuild;
import com.phynos.solar.rule.easyrules.action.DeviceAction;
import com.phynos.solar.rule.easyrules.condition.ConditionType;
import com.phynos.solar.rule.easyrules.condition.DeviceConditon;
import com.phynos.solar.rule.easyrules.condition.OperType;
import com.phynos.solar.rule.easyrules.rule.HelloWorldRule;
import com.phynos.solar.rule.easyrules.rule.IotRule;
import org.apache.commons.lang3.RandomUtils;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.jexl.JexlRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by lupc
 * @date 2021-02-06 10:53
 */
public class EasyRulesTest {

    public static void main(String[] args) throws Exception {
        //
        //testJexl();
        //
        testIot();
    }

    private static void testJexl() {
        // create facts
        Facts facts = new Facts();
        facts.put("age", 1);
        facts.put("pat", new Pat());

        // create rules
        Rules rules = new Rules();
        rules.register(new HelloWorldRule());

        Rule jexlRule = new JexlRule()
                .name("jexlRuler")
                .description("")
                .when("age > 18")
                .then("pat.test()");
        rules.register(jexlRule);

        // create a rules engine and fire rules on known facts
        RulesEngine rulesEngine = new DefaultRulesEngine();
        for (int i = 0; i < 100; i++) {
            facts.put("age", RandomUtils.nextInt(1, 99));
            rulesEngine.fire(rules, facts);
        }
    }

    private static void testIot() throws InterruptedException {
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

    public static class Pat {
        public void test() {
            System.out.println("执行了MMMMMM");
        }
    }


}
