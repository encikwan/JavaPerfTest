package com.encikwan.jpt;

import java.math.BigInteger;

import org.apache.commons.math.util.MathUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class DoubleFactorialTest extends PerformanceTest {
	private static Logger log = LogManager.getLogger(DoubleFactorialTest.class
			.getName());

	@Test
	public void testMedium(){
		int f = 99;
		
		double expectedResult = MathUtils.factorialDouble(f);
		DoubleFactorial factorial = new DoubleFactorial(f);
		long start = System.nanoTime();
		double result = factorial.calculate();
		long end = System.nanoTime();
		
		assert(result  == expectedResult);
		
		if(log.isDebugEnabled()){
			log.debug("Time(ns) for " + f + "!=" + (end - start));
		}
		
	}
	
	
	@Test
	public void testPerformance() {
		String approach1 = "BigInteger";
		String approach2 = "double";
		int f = 99;
		long time1 = 0, time2 = 0;
		long mem1 = 0, mem2 = 0;
		int iteration = 30;
		double expectedResult = MathUtils.factorialDouble(f);
		long start = 0;
		long memoryUsedBefore = 0;
		BigIntegerFactorial factorial1 = new BigIntegerFactorial(f);
		DoubleFactorial factorial2 = new DoubleFactorial(f);
		BigInteger result1 = null;
		double result2 = 0;

		
		for (int i = 0; i < iteration; i++) {
			memoryUsedBefore = getMemoryUsed();
			start = System.nanoTime();
			result1 = factorial1.calculate();
			time1 = time1 + (System.nanoTime() - start);
			mem1 = mem1 + (getMemoryUsed() - memoryUsedBefore);
			assert (result1.doubleValue() == expectedResult);
		}	
		
		for (int i = 0; i < iteration; i++) {
			memoryUsedBefore = getMemoryUsed();
			start = System.nanoTime();
			result2 = factorial2.calculate();
			time2 = time2 + (System.nanoTime() - start);
			mem2 = mem2 + (getMemoryUsed() - memoryUsedBefore);
			assert (result2 == expectedResult);
		}

		if (log.isDebugEnabled()) {
			log.debug("Time difference(ns) between " + approach1 + " and "
					+ approach2 + " for " + f + "!=" +  ((time1 - time2)
					/ iteration) );
			log.debug("Memory difference between " + approach1 + " and "
					+ approach2 + " for " + f + "!=" + ((mem1 - mem2)
					/ iteration) + "; mem1=" + mem1 + ",mem2=" + mem2);
		}	

	}

	@Test
	public void testPerformanceFluctuation() {
		int f = 99;
		int iteration = 3;
		long[] time1 = {0,0,0};
		double expectedResult = MathUtils.factorialDouble(f);
		long start = 0;
		
		DoubleFactorial factorial1 = new DoubleFactorial(f);
		double result1 = 0;
		
		for (int i = 0; i < iteration; i++) {
			start = System.nanoTime();
			
			result1 = factorial1.calculate();
			time1[i] = time1[i] + (System.nanoTime() - start);
			
			assert (result1 == expectedResult);

		}

		if (log.isDebugEnabled()) {
			for(int i = 0; i < iteration; i++){
				log.debug("Time for " + f + "! iteration#" + i + "=" +  time1[i] );
			}

		}

	}

}
