package effectivejava.chapter7;

//import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.stream.Stream;

import org.junit.Test;

public class Item45Tests {
	private static final BigInteger TWO = BigInteger.valueOf(2); 
	/**
	 * Finds first x mersenne primes. Mersenne primes are of the form (2^p - 1) where p is a prime.
	 */
	@Test
	public void findMersennePrimes() {
		Stream.iterate(TWO, BigInteger::nextProbablePrime)
			.map(prime -> TWO.pow(prime.intValueExact()).subtract(BigInteger.ONE))
			.filter(maybePrime -> maybePrime.isProbablePrime(50))
			.limit(15)
			.forEach(System.out::println);
	}
}
