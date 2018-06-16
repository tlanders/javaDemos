package misc.sort;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
	
	public static void main(String [] args) throws Exception {
		System.out.println("main started");

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
