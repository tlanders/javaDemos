package effectivejava.chapter7;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Sublists {
	public static <E> Stream<List<E>> of(List<E> list) {
		return Stream.concat(Stream.of(Collections.emptyList()),
				prefixes(list).flatMap(Sublists::suffixes));
	}
	
	public static <E> Stream<List<E>> prefixes(List<E> list) {
		return Stream.of(Collections.emptyList());
	}
	
	public static <E> Stream<List<E>> suffixes(List<E> list) {
		return Stream.of(Collections.emptyList());
	}
}
