package java8_in_action.chapter7;

import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {

	protected final long [] numbersToSum;
	protected final int startIndex;
	protected final int endIndex;
	protected static final int THRESHOLD = 5;
	
	public ForkJoinSumCalculator(long [] numbers) {
		this(numbers, 0, numbers.length);
	}
	
	public ForkJoinSumCalculator(long [] numbers, int start, int end) {
		System.out.println("ForkJoin [st, end, len] : " + start + ", " + end + ", " + numbers.length);
		numbersToSum = numbers;
		startIndex = start;
		endIndex = end;
	}
	
	@Override
	protected Long compute() {
		if(endIndex - startIndex > THRESHOLD) {
			int splitIndex = (startIndex + endIndex) / 2;
			System.out.println("  compute split at " + splitIndex);
			ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbersToSum, startIndex, splitIndex);
			rightTask.fork();
			
			ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbersToSum, splitIndex, endIndex);
			Long leftSum = leftTask.compute();
			Long rightSum = rightTask.join();
			
			return leftSum + rightSum;
		} else {
			System.out.println("  computing [st, end] : " + startIndex + ", " + endIndex);
			return computeSequentially();
		}
	}

	protected long computeSequentially() {
		long sum = 0;
		for(int i = startIndex; i < endIndex; i++) {
			sum += numbersToSum[i];
		}
		return sum;
	}
}
