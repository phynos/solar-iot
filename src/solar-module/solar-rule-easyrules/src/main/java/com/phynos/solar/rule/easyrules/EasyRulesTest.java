package com.phynos.solar.rule.easyrules;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.jexl.JexlRule;

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
    }

}
