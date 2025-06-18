package com.solar.iot.model;

import org.apache.commons.jexl3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author by lupc
 * @date 2021-02-25 8:51
 */
public class Test {

    Logger logger = LoggerFactory.getLogger(getClass());

    private void testJexl() {
        JexlEngine jexl = new JexlBuilder().create();

        // Create an expression
        String jexlExp = "foo.bar() + 2 + x";
        JexlExpression e = jexl.createExpression(jexlExp);

        // Create a context and add data
        JexlContext jc = new MapContext();
        jc.set("foo", new Foo());
        jc.set("x", 100);

        // Now evaluate the expression, getting the result
        Object o = e.evaluate(jc);

        logger.debug(o.toString());
    }

    public static class Foo {

        public int bar() {
            return 2;
        }

    }

}
