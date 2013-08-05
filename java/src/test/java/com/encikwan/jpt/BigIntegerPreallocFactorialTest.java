package com.encikwan.jpt;

import java.math.BigInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class BigIntegerPreallocFactorialTest extends PerformanceTest{
	private static Logger log = LogManager.getLogger(BigIntegerPreallocFactorialTest.class
			.getName());
	
	@Test
	public void testSimple(){
		int f = 3;
		BigInteger expectedResult = new BigInteger("" + 6);
		BigIntegerPreallocFactorial factorial = new BigIntegerPreallocFactorial(f);
		long start = System.nanoTime();
		BigInteger result = factorial.calculate();
		long end = System.nanoTime();
		
		assert(result.compareTo(expectedResult) == 0);
		
		if(log.isDebugEnabled()){
			log.debug("Time(ns) for " + f + "!=" + (end - start));
		}
		
	}

	@Test
	public void testPerformance() {
		String approach1 = "BigInteger preallocated";
		String approach2 = "BigInteger";
		int f = 999;
		long time1 = 0, time2 = 0;
		long mem1 = 0, mem2 = 0;
		int iteration = 30;
		
		long start = 0;
		long memoryUsedBefore = 0;
		BigIntegerPreallocFactorial factorial1 = new BigIntegerPreallocFactorial(f);
		BigIntegerFactorial factorial2 = new BigIntegerFactorial(f);
		BigInteger result1 = null;
		BigInteger result2 = null;

		
		for (int i = 0; i < iteration; i++) {
			memoryUsedBefore = getMemoryUsed();
			start = System.nanoTime();
			result1 = factorial1.calculate();
			time1 = time1 + (System.nanoTime() - start);
			mem1 = mem1 + (getMemoryUsed() - memoryUsedBefore);
		}	

		for (int i = 0; i < iteration; i++) {
			memoryUsedBefore = getMemoryUsed();
			start = System.nanoTime();
			result2 = factorial2.calculate();
			time2 = time2 + (System.nanoTime() - start);
			mem2 = mem2 + (getMemoryUsed() - memoryUsedBefore);
			assert (result1.compareTo(result2) == 0);
		}
		

		if (log.isDebugEnabled()) {
			log.debug("Time difference(ns) between " + approach1 + " and "
					+ approach2 + " for " + f + "!=" +  ((time1 - time2)
					/ iteration) );
			log.debug("Memory difference between " + approach1 + " and "
					+ approach2 + " for " + f + "!=" + ((mem1 - mem2)
					/ iteration) + "; mem1=" + mem1 + ",mem2=" + mem2);
			log.debug("result1=" + result1);
			log.debug("result2=" + result2);
		}

	}


}
