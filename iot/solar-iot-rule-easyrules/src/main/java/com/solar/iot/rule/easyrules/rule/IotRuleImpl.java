package com.solar.iot.rule.easyrules.rule;

import com.solar.iot.model.device.IotDevice;
import com.solar.iot.rule.RuleFireType;
import com.solar.iot.rule.action.DeviceAction;
import com.solar.iot.rule.condition.ConditionType;
import com.solar.iot.rule.condition.DeviceConditon;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;

import java.util.List;
import java.util.Map;

/**
 * @author by lupc
 * @date 2021-02-06 13:45
 */
@Slf4j
@Setter
@Getter
public class IotRuleImpl implements Rule {

    /**
     * 规则名称
     */
    String name;

    /**
     * 规则描述
     */
    String description;

    /**
     * 规则触发类型
     */
    RuleFireType ruleFireType;

    /**
     * 条件类别
     */
    List<DeviceConditon> deviceConditons;

    /**
     * 条件的组合逻辑形式
     */
    ConditionType conditionType;

    /**
     * 执行
     */
    List<DeviceAction> actions;

    @Override
    public boolean evaluate(Facts facts) {
        Map<String, IotDevice> deviceMap = facts.get("deviceMap");
        if (conditionType == ConditionType.或) {
            for (DeviceConditon condition : deviceConditons) {
                boolean result = condition.evaluate(deviceMap);
                if (result) {
                    return true;
                }
            }
            return false;
        } else {
            boolean result = false;
            for (DeviceConditon condition : deviceConditons) {
                result = condition.evaluate(deviceMap);
                if (!result) {
                    return false;
                }
            }
            return result;
        }
    }

    @Override
    public void execute(Facts facts) throws Exception {
        log.debug("规则触发：{}", name);
        final Map<String, IotDevice> deviceMap = facts.get("deviceMap");
        for (DeviceAction action : actions) {
            action.execute(deviceMap);
        }
    }

    @Override
    public int compareTo(Rule o) {
        return 0;
    }
}
