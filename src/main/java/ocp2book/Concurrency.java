package ocp2book;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Concurrency {
	public static void main(String [] args) {
		System.out.println("Concurrency...");

		// parallel concat strings adding "-" in between
		System.out.println(Arrays.asList('a','b','c','d','e')
				.parallelStream()
				.reduce("", 
						(s, c) -> {
							//System.out.println("s=" + s + ", c=" + c);
							return s.equals("") ? String.valueOf(c) : c + "-" + s; 
						},
						(s1, s2) -> {
							//System.out.println("s1=" + s1 + ", s2=" + s2);
							return (s1.equals("") || s2.equals("")) ? s1 + s2 : s1 + "-" + s2;
						})
				);
		
		Stream<String> ohMy = Stream.of("lions", "tigers", "bears").parallel();
		ConcurrentMap<Integer, String> map = ohMy   
				.collect(Collectors.toConcurrentMap(String::length, 
						v -> v,        
						(s1, s2) -> s1 + "," + s2));
		System.out.println(map); // {5=lions,bears, 6=tigers}System.out.println(map.getClass()); // java.util.concurrent.ConcurrentHashMap
		
		Stream<String> ohMy2 = Stream.of("lions", "tigers", "bears").parallel();
		ConcurrentMap<Integer, List<String>> map2 = ohMy2.collect(   
				Collectors.groupingByConcurrent(String::length));
		System.out.println(map2);
		
		System.out.println(Arrays.asList("ducky","chicken","flamingo","pelican")   
				.parallelStream().parallel() // q1   
				.reduce(0,      
						(cnt, str) -> cnt + str.length(), // q2      
						(cnt1, cnt2) -> cnt1 + cnt2)); // q3
		
		System.out.println("Concurrency done.");
	}
}
