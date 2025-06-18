package com.solar.iot.rule.easyrules.rule;

import org.apache.commons.lang3.RandomUtils;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.jexl.JexlRule;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

@DisplayName("规则引擎-普通测试")
@SpringBootTest(classes = HelloWorldRuleTest.class)
class HelloWorldRuleTest {


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

    public static class Pat {
        public void test() {
            System.out.println("执行了MMMMMM");
        }
    }

}