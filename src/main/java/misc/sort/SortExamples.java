package misc.sort;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Based on examples in https://dzone.com/articles/comparators-in-more-functional-way.
 * @author tlanders
 */
public class SortExamples {
	public static void sortOldWay(final List< String > words) {
	    words.sort(new Comparator< String > () {
	        @Override
	        public int compare(String s1, String s2) {
	            if (s2.length() == s1.length()) {
	                return s1.compareTo(s2);
	            } else {
	                return s2.length() - s1.length();
	            }
	        }
	    });
	}
	
	public static void sortNewWay(final List<String> words) {
        words.sort(Comparator.comparing(String::length).thenComparing(Comparator.reverseOrder()).reversed());
	}
	
	public static void sortNewWayIntuitive(final List<String> words) {
		final Function<String, Integer> byLength = String::length;
		//final Function<String, Integer> byLength = s -> s.length();
//        words.sort(Comparator.comparing(byLength).reversed());
//        words.sort(Comparator.comparing(byLength).thenComparing(Comparator.reverseOrder()));
//        words.sort(Comparator.comparing(byLength)
//                .thenComparing(Comparator.reverseOrder()).reversed());
        words.sort(Comparator.comparing(String::length).reversed().thenComparing(Comparator.naturalOrder()));
	}

	public static <K,V extends Comparable<? super V>> Map<K,V> sortMapByValue(Map<K,V> mapToSort) {
//	public static Map<String,String> sortMapByValue(Map<String, String> mapToSort) {
		return mapToSort.entrySet().stream()
				.sorted(Map.Entry.comparingByValue())
//				.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1,e2) -> e1, LinkedHashMap::new));
//				.sorted((entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue()))
				.collect(LinkedHashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()), Map::putAll);
	}

	public static void main(String [] args) throws Exception {
		System.out.println("main started");

		Map<String,String> m = new HashMap<>();
		m.put("1", "abc");
		m.put("a", "23");
		m.put("4", "GDJ");
		m.put("gh", "834");
		m.put("AB", "834");
		m.put("3", "PCw");
		m.put("SD", "kve");
        m.put("Z", "23");

		System.out.println("unsorted m=" + m);

		Map<String,String> resultMap = sortMapByValue(m);

		System.out.println("sorted=" + resultMap);

		Map<String, Integer> m2 = new HashMap<>();
        m2.put("1", 7);
        m2.put("a", 54);
        m2.put("4", 9);
        m2.put("gh", 3);
        m2.put("AB", 23);

        System.out.println("unsorted m2=" + m2);
        Map<String,Integer> resultMap2 = sortMapByValue(m2);

        System.out.println("sorted=" + resultMap2);

        if(args.length >= 1) {
			List<String> words = Files.lines(Paths.get(args[0])).collect(Collectors.toList());	
			List<String> words2 = new ArrayList<>(words);			
			List<String> words3 = new ArrayList<>(words);
			List<String> words4 = new ArrayList<>(words);
			
			sortOldWay(words2);
			sortNewWay(words3);
			sortNewWayIntuitive(words4);
			System.out.println("unsorted:" + words);
			System.out.println("sorted by len reversed:" + words2);
			System.out.println("new way sorted by len reversed:" + words3);
			System.out.println("new way2 sorted by len reversed:" + words4);
		} else {
			System.out.println("ERROR: please provide word file name.");
		}

		System.out.println("main done.");
	}
}
