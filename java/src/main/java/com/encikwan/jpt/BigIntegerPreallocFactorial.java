package com.encikwan.jpt;

import java.math.BigInteger;

/**
 * Implementation of factorial using sequential multiplication of preallocated BigInteger values
 * 
 * @author Wan
 *
 */
public class BigIntegerPreallocFactorial {
	private int f = 0;
	private BigInteger[] array = null;

	public BigIntegerPreallocFactorial(int f){
		this.f = f;
		int counter = f;
		array = new BigInteger[f + 1];
		while(counter > 0){
			array[counter] = new BigInteger("" + counter);
			counter--;
			
		}
	}
	
	public BigInteger calculate(){
		BigInteger result = new BigInteger("1");
		int counter = this.f;
		while(counter > 0){
			result = result.multiply(array[counter]);
			counter--;
		}
		
		
		return result;
	}
}
