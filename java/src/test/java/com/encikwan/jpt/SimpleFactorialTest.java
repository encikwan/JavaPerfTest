package com.encikwan.jpt;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;


public class SimpleFactorialTest {
	private static Logger log = LogManager.getLogger(
			SimpleFactorialTest.class.getName());
	
	@Test
	public void testSimple(){
		long f = 3;
		long expectedResult = 6;
		SimpleFactorial factorial = new SimpleFactorial(f);
		long start = System.nanoTime();
		
		long result = factorial.calculate();
		long end = System.nanoTime();
		
		assert(result == expectedResult);
		
		if(log.isDebugEnabled()){
			log.debug("Time(ns) for " + f + "!=" + (end - start));
		}
		
	}
	

}
