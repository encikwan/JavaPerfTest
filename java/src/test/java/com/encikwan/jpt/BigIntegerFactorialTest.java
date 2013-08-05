package com.encikwan.jpt;

import java.math.BigInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.apache.commons.math.util.MathUtils;



public class BigIntegerFactorialTest {
	private static Logger log = LogManager.getLogger(
			BigIntegerFactorialTest.class.getName());
	
	@Test
	public void testSimple(){
		long f = 3;
		BigInteger expectedResult = new BigInteger("" + 6);
		BigIntegerFactorial factorial = new BigIntegerFactorial(f);
		long start = System.nanoTime();
		BigInteger result = factorial.calculate();
		long end = System.nanoTime();
		
		assert(result.compareTo(expectedResult) == 0);
		
		if(log.isDebugEnabled()){
			log.debug("Time(ns) for " + f + "!=" + (end - start));
		}
		
	}
	
	@Test
	public void testMedium(){
		long f = 20;
		
		BigInteger expectedResult = new BigInteger("2432902008176640000");
		BigIntegerFactorial factorial = new BigIntegerFactorial(f);
		long start = System.nanoTime();
		BigInteger result = factorial.calculate();
		long end = System.nanoTime();
		
		assert(result.compareTo(expectedResult) == 0);
		
		if(log.isDebugEnabled()){
			log.debug("Time(ns) for " + f + "!=" + (end - start));
		}
		
	}
	
	@Test
	public void testHuge(){
		int f = 999;
		
		BigIntegerFactorial factorial = new BigIntegerFactorial(f);
		long start = System.nanoTime();
		BigInteger result = factorial.calculate();
		long end = System.nanoTime();
		
		// assert(result.doubleValue() == expectedResult); not relevant as expectedResult = infinity
		
		if(log.isDebugEnabled()){
			log.debug("Time(ns) for " + f + "!=" + (end - start) );
		}
		
	}

	@Test
	public void testPerformanceFluctuation() {
		int f = 99;
		int iteration = 3;
		long[] time1 = {0,0,0};
		double expectedResult = MathUtils.factorialDouble(f);
		long start = 0;
		BigIntegerFactorial factorial1 = new BigIntegerFactorial(f);
		BigInteger result1 = null;
		
		for (int i = 0; i < iteration; i++) {
			start = System.nanoTime();
			result1 = factorial1.calculate();
			time1[i] = time1[i] + (System.nanoTime() - start);
			
			assert (result1.doubleValue() == expectedResult);

		}

		if (log.isDebugEnabled()) {
			for(int i = 0; i < iteration; i++){
				log.debug("Time for " + f + "! iteration#" + i + "=" +  time1[i] );
			}

		}

	}

}
