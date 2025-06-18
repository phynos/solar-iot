package com.solar.iot.rule.easyrules.rule;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

/**
 * @author by lupc
 * @date 2021-02-06 10:57
 */
@Rule(name = "Hello World rule", description = "Always say hello world")
public class HelloWorldRule {

    @Condition
    public boolean when(@Fact("age") int age) {
        return age % 8 == 0;
    }

    @Action
    public void then(Facts facts) throws Exception {
        int age = facts.get("age");
        System.out.println("age:" + age);
    }

}
