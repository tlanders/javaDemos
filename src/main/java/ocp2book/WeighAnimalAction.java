package ocp2book;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class WeighAnimalAction extends RecursiveAction {
	protected final int start;
	protected final int end;
	protected final Double [] weights;
	
	public WeighAnimalAction(int s, int e, Double[] w) {
		System.out.println("WeighAnimalAction: s=" + s + ", e=" + e);
		
		start = s;
		end = e;
		weights = w;
	}
	
	public void compute() {
		if(end - start > 3) {
			// split the task
			int mid = start + (end - start) / 2;
			invokeAll(
					new WeighAnimalAction(start, mid, weights),
					new WeighAnimalAction(mid, end, weights) );
		} else {
			for(int i = start; i < end; i++) {
				System.out.println("weight: " + weights[i] + " - st=" + start);
			}
		}
	}
	
	public static void main(String [] args) throws Exception {
		
		System.out.println("WeighAnimalAction starting...");

		final Double [] w = new Double[10];
		for(int i = 0; i < w.length; i++) {
			w[i] = new Random().nextDouble() * 100;
		}
		Arrays.sort(w);
		
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(new WeighAnimalAction(0, w.length, w));

		Thread.sleep(4000);
		
		System.out.println("WeighAnimalAction done.");
		
	}
}
