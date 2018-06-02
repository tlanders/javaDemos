package ocp2book;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class WeighAnimalTask extends RecursiveTask<Double> {
	protected final int start;
	protected final int end;
	protected final Double [] weights;
	
	public WeighAnimalTask(int s, int e, Double[] w) {
		System.out.println("WeighAnimalAction: s=" + s + ", e=" + e);
		
		start = s;
		end = e;
		weights = w;
	}
	
	public Double compute() {
		if(end - start > 3) {
			// split the task
			int mid = start + (end - start) / 2;
			WeighAnimalTask task1 = new WeighAnimalTask(start, mid, weights);
			task1.fork();
			
			return new WeighAnimalTask(mid, end, weights).compute() + task1.join();
		} else {
			double sum = 0;
			for(int i = start; i < end; i++) {
				System.out.println("compute: weight=" + weights[i] + ", s=" + start);
				sum += weights[i];
			}

			System.out.println("compute: sum=" + sum + ", s=" + start);
			
			return sum;
		}
	}
	
	public static void main(String [] args) throws Exception {
		
		System.out.println("WeighAnimalTask starting...");

		final Double [] w = new Double[10];
		for(int i = 0; i < w.length; i++) {
			w[i] = new Random().nextDouble() * 100;
		}
		Arrays.sort(w);
		
		ForkJoinPool pool = new ForkJoinPool();
		Double totalWeight = pool.invoke(new WeighAnimalTask(0, w.length, w));

		//Thread.sleep(4000);
		System.out.println("total weight=" + totalWeight);
		
		System.out.println("WeighAnimalTask done.");
		
	}
}
