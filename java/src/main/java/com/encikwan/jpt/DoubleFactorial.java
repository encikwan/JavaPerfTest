package com.encikwan.jpt;


/**
 * Implementation of factorial using sequential multiplication of double values
 * 
 * @author Wan
 *
 */
public class DoubleFactorial {
	private long f = 0;

	public DoubleFactorial(long f){
		this.f = f;
	}
	
	public double calculate(){
		double result = 1f;
		long counter = this.f;
		while(counter > 0){
			result = result * counter;
			counter--;
		}
		
		return result;
	}
}
