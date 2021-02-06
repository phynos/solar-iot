package com.phynos.solar.rule.easyrules;

import com.phynos.solar.rule.easyrules.action.DeviceAction;
import com.phynos.solar.rule.easyrules.condition.ConditionType;
import com.phynos.solar.rule.easyrules.condition.DeviceConditon;
import com.phynos.solar.rule.easyrules.device.IotDevice;
import com.phynos.solar.rule.easyrules.rule.HelloWorldRule;
import com.phynos.solar.rule.easyrules.rule.IotRule;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.jexl.JexlRule;

import java.util.*;

/**
 * @author by lupc
 * @date 2021-02-06 10:53
 */
public class EasyRulesTest {

    public static void main(String[] args) throws Exception {

        // create facts
        Facts facts = new Facts();
        facts.put("age", 1);

        // create rules
        Rules rules = new Rules();
        rules.register(new HelloWorldRule());

        Rule jexlRule = new JexlRule()
                .name("jexlRuler")
                .description("")
                .when("age > 18")
                .then("System.out.println(\"jexl rule action\");");
        rules.register(jexlRule);

        // create a rules engine and fire rules on known facts
        RulesEngine rulesEngine = new DefaultRulesEngine();
        for (int i = 0; i < 100; i++) {
            facts.clear();
            facts.put("age", i);
            rulesEngine.fire(rules, facts);
        }

        test();
    }

    private static void test() throws InterruptedException {
        IotRule openRule = new IotRule();
        openRule.setName("空气自动开启");
        openRule.setDescription("温度传感器数据大于28且小于40自动开启空调");
        openRule.setConditionType(ConditionType.或);
        //条件
        List<DeviceConditon> conditons1 = new ArrayList<>(2);
        openRule.setDeviceConditons(conditons1);
        //行为
        List<DeviceAction> actions1 = new ArrayList<>();
        openRule.setActions(actions1);

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
        deviceMap.put("0101", new IotDevice());
        deviceMap.put("0201", new IotDevice());
        facts.put("deviceMap", deviceMap);
        Random random = new Random(System.currentTimeMillis());
        RulesEngine rulesEngine = new DefaultRulesEngine();
        for (int i = 0; i < 100; i++) {
            int t = random.nextInt();
            deviceMap.get("0101").getSignals().get("temp").setVal(t);
            rulesEngine.fire(rules, facts);
            Thread.sleep(300);
        }
    }


}
