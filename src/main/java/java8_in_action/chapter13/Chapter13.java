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
		System.out.println("find, theList=" + theList);
		List<List<Integer>> subsets = new ArrayList<>();
		if(theList.isEmpty()) {
			subsets.add(theList);
			return subsets;
		}
		
		Integer i = theList.get(0);
		List<List<Integer>> otherSubsets = findSubsets(theList.subList(1, theList.size()));
		subsets.addAll(otherSubsets);
		for(List<Integer> li : otherSubsets) {
			List<Integer> newList = new ArrayList<>();
			newList.add(i);
			newList.addAll(li);
			subsets.add(newList);
		}
		
		return subsets;
	}
}
