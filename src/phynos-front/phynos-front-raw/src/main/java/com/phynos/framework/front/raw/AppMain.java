package com.phynos.framework.front.raw;

import org.apache.commons.jexl3.*;

public class AppMain {

	public static void main(String[] args){
		 // Create or retrieve an engine
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
	    
	    System.out.println(o);
	}	
	
	public static class Foo {
		
		public int bar(){
			return 2;
		}
		
	}
	
}
