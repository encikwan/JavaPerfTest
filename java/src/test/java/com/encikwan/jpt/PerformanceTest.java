package com.encikwan.jpt;

/**
 * Base class for Java Performance Test
 * 
 * @author Wan
 *
 */
public class PerformanceTest {
	public static long getMemoryUsed() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

}
