package effectivejava.chapter7;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalTime;
import java.util.stream.LongStream;

import org.junit.Test;

public class ParallelTests {
	/**
	 * pi(n) function returns all primes that are less than n.
	 * @param n
	 * @return
	 */
	public long pi(long n) {
		return LongStream.rangeClosed(2, n)
				.mapToObj(BigInteger::valueOf)
				.filter(x -> x.isProbablePrime(50))
				.count();
	}

	public long pip(long n) {
		return LongStream.rangeClosed(2, n)
				.parallel()
				.mapToObj(BigInteger::valueOf)
				.filter(x -> x.isProbablePrime(50))
				.count();
	}
	
	@Test
	public void testPi() {
		LocalTime startTime = LocalTime.now();
		System.out.println(startTime);
		long x = pi(10000000);

		LocalTime endTime = LocalTime.now();
		Duration d = Duration.between(startTime, endTime);
		System.out.println(x);
		
		System.out.println("time elapsed: " + d);	

		long x2 = pip(10000000);
		
		System.out.println(x);

		LocalTime endTime2 = LocalTime.now();
		Duration d2 = Duration.between(endTime, endTime2);
		System.out.println("parallel elapsed: " + d2);	

		assertEquals(x, x2);
	}
}
