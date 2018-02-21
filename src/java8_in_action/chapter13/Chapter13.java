package java8_in_action.chapter13;

import java.util.ArrayList;
import java.util.List;

public class Chapter13 {
	public static void main(String [] args) {
		System.out.println("Chapter 13...");
		List<Integer> initialList = new ArrayList<>();
		initialList.add(1);
		initialList.add(4);
		initialList.add(9);
		
		System.out.println("init=" + initialList);
		List<List<Integer>> subsets = findSubsets(initialList);

		System.out.println("subsets=" + subsets);
		
		System.out.println("Chapter 13 done.");
	}
	
	protected static List<List<Integer>> findSubsets(List<Integer> theList) {
		return null;
	}
}
