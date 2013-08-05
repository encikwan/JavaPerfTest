package com.encikwan.jpt;

import org.apache.commons.math.util.MathUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class DoubleConcurrentFactorialTest {
	private static Logger log = LogManager.getLogger(
			DoubleConcurrentFactorialTest.class.getName());


	@Test
	public void testSimple(){
		long f = 3;
		double expectedResult = 6f;
		DoubleConcurrentFactorial factorial = new DoubleConcurrentFactorial(f);
		long start = System.nanoTime();
		
		double result = factorial.calculate();
		long end = System.nanoTime();
		
		assert(result == expectedResult);
		
		if(log.isDebugEnabled()){
			log.debug("Time(ns) for " + f + "!=" + (end - start));
		}
		
	}

	@Test
	public void testMedium(){
		int f = 99;
		
		double expectedResult = MathUtils.factorialDouble(f);
		DoubleConcurrentFactorial factorial = new DoubleConcurrentFactorial(f);
		long start = System.nanoTime();
		double result = factorial.calculate();
		long end = System.nanoTime();
		
		assert(result  == expectedResult);
		
		if(log.isDebugEnabled()){
			log.debug("Time(ns) for " + f + "!=" + (end - start));
		}
		
	}

}
