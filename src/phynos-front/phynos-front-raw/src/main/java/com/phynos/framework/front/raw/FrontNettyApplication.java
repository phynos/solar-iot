package com.phynos.framework.front.raw;

import com.phynos.framework.front.raw.netty.IotNettyServer;
import com.phynos.framework.front.raw.util.ClassUtil;
import org.apache.commons.jexl3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FrontNettyApplication implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
    IotNettyServer iotNettyServer;

	public static void main(String[] args) {
		SpringApplication.run(FrontNettyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ClassUtil.initTypeToMsgClassMap("com.phynos.framework.front.raw.message");

		JexlEngine jexl = new JexlBuilder().create();

		// Create an expression
		String jexlExp = "foo.bar() + 2 + x";
		JexlExpression e = jexl.createExpression( jexlExp );

		// Create a context and add data
		JexlContext jc = new MapContext();
		jc.set("foo", new Foo() );
		jc.set("x",100);

		// Now evaluate the expression, getting the result
		Object o = e.evaluate(jc);

		logger.debug(o.toString());

		iotNettyServer.start();
	}

	public static class Foo {
		
		public int bar(){
			return 2;
		}
		
	}
	
}
