package com.encikwan.jpt;

/**
 * Implementation of factorial using sequential multiplication of long values
 * 
 * @author Wan
 *
 */
public class SimpleFactorial {
	long f = 0;

	public SimpleFactorial(long f){
		this.f = f;
	}
	
	public long calculate(){
		long result = 1;
		long counter = this.f;
		while(counter > 0){
			result = result * counter;
			counter--;
		}
		
		return result;
	}
}
