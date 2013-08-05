package com.encikwan.jpt;

import java.math.BigInteger;

/**
 * Implementation of factorial using sequential multiplication of BigInteger values
 * 
 * @author Wan
 *
 */
public class BigIntegerFactorial {
	private long f = 0;

	public BigIntegerFactorial(long f){
		this.f = f;
	}
	
	public BigInteger calculate(){
		BigInteger result = new BigInteger("1");
		long counter = this.f;
		while(counter > 0){
			/* new BigInteger object for each iteration */
			result = result.multiply(new BigInteger("" + counter));
			counter--;
		}

		return result;
	}
}
