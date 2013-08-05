package com.encikwan.jpt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * Implementation of factorial using concurrent multiplication of double values
 * 
 * @author Wan
 *
 */
public class DoubleConcurrentFactorial {
	private long f = 0;
	private ExecutorService eservice = null;
	private int threadcount = 0;
	private MultiplyTask[] tasks = null;
	private Collection<Callable<Double>> coll = new ArrayList<Callable<Double>>();
	

	public DoubleConcurrentFactorial(long f){
		this.f = f;
		int numberOfThreads = (Runtime.getRuntime().availableProcessors()/2);
		if(numberOfThreads <0){
			numberOfThreads = 1;
		}
		threadcount = numberOfThreads;
		eservice = Executors.newFixedThreadPool(numberOfThreads);
		tasks = new MultiplyTask[threadcount];
		for(int i = 0; i < threadcount; i++){
			tasks[i] = new MultiplyTask();
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
				
				i = i + 2;
	
			}
			try {
				List<Future<Double>> list = eservice.invokeAll(coll);
				for(Future future : list){
					result = result * (Double)future.get();
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
			counter = counter - threadcount;
		}
		
		return result;
	}
	
	@SuppressWarnings("unused")
	private class MultiplyTask implements Callable<Double>{
		double n1 = 0, n2 = 0;
		public void setNumbers(double n1, double n2){
			this.n1 = n1;
			this.n2 = n2;
		}

		public Double call() throws Exception {
			Double result = null;
			result = n1 * n2;
			return result;
		}
		
	}	
	
}
