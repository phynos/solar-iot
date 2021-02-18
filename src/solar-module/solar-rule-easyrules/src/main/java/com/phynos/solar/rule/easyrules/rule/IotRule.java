package com.phynos.solar.rule.easyrules.rule;

import com.phynos.solar.rule.easyrules.RuleFireType;
import com.phynos.solar.rule.easyrules.action.DeviceAction;
import com.phynos.solar.rule.easyrules.condition.ConditionType;
import com.phynos.solar.rule.easyrules.condition.DeviceConditon;
import com.phynos.solar.rule.easyrules.device.IotDevice;
import lombok.Getter;
import lombok.Setter;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;

import java.util.List;
import java.util.Map;

/**
 * @author by lupc
 * @date 2021-02-06 13:45
 */
@Setter
@Getter
public class IotRule implements Rule {

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
