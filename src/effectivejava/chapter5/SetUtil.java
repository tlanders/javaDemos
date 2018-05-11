package effectivejava.chapter5;

import java.util.HashSet;
import java.util.Set;

public class SetUtil {
	private SetUtil() { }
	
	public static <E> Set<E> union(Set<? extends E> s1, Set<? extends E> s2) {
		Set<E> result = new HashSet<>(s1);
		result.addAll(s2);
		return result;
	}
}
