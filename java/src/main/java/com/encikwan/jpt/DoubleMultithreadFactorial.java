package com.encikwan.jpt;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Implementation of factorial using multiple threads to multiply double values
 * 
 * @author Wan
 *
 */
public class DoubleMultithreadFactorial {
	private long f = 0;
	
	private int threadcount = 0;
	private MultiplyThread[] tasks = null;
	private Collection<MultiplyThread> coll  = new ArrayList<MultiplyThread>();	

	public DoubleMultithreadFactorial(long f){
		this.f = f;
		int numberOfThreads = (Runtime.getRuntime().availableProcessors()/2);
		if(numberOfThreads <0){
			numberOfThreads = 1;
		}
		threadcount = numberOfThreads;
		
		tasks = new MultiplyThread[threadcount];
		for(int i = 0; i < threadcount; i++){
			tasks[i] = new MultiplyThread();
		}
	}
	
	public double calculate(){
		double result = 1f;
		long counter = this.f;
		
		double n1 = 1, n2 = 1;
		
		while(counter > 0){
			n1 = 1;
			n2 = 1;
			coll.clear();
			for(int i = 0; i < threadcount; ){
				if(counter > 0){
					n1 = counter;
				}
				if(--counter > 0){
					n2 = counter;
				}

				tasks[i].setNumbers(n1, n2);
				coll.add(tasks[i]);
				tasks[i].run();
				
				i = i + 2;
	
			}
			
				
				for(MultiplyThread thread : coll){
					try {
						thread.join();
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
					result = result * thread.getResult();
				}
			
			
			counter = counter - threadcount;
		}
		
		return result;
	}
	
	
	private class MultiplyThread extends Thread{
		double n1 = 0, n2 = 0;
		double result = 0;
		
		public void setNumbers(double n1, double n2){
			this.n1 = n1;
			this.n2 = n2;
		}
		
		public double getResult(){
			return result;
		}	
		

		public void run() {
			result = n1 * n2;
		}
		
	}	
	
}
