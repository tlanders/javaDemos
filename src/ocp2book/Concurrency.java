package ocp2book;

import java.util.Arrays;

public class Concurrency {
	public static void main(String [] args) {
		System.out.println("Concurrency...");

		// parallel concat strings adding "-" in between
		System.out.println(Arrays.asList('a','b','c','d','e')
				.parallelStream()
				.reduce("", 
						(s, c) -> {
							System.out.println("s=" + s + ", c=" + c);
							return s.equals("") ? String.valueOf(c) : c + "-" + s; 
						},
						(s1,s2) -> {
							System.out.println("s1=" + s1 + ", s2=" + s2);
							return (s1.equals("") || s2.equals("")) ? s1 + s2 : s1 + "-" + s2;
						})
				);
		System.out.println("Concurrency done.");
	}
}
