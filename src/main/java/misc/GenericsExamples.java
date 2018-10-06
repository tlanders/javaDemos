package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Based on examples in https://nofluffjuststuff.com/magazine/2016/09/time_to_really_learn_generics_a_java_8_perspective.
 * @author tlanders
 */
public class GenericsExamples {

	public static void main(String [] args) throws Exception {
		System.out.println("main started");

		List<Integer> ints = Arrays.asList(1, 2, 3, 4);
		List<Double> dbls = Arrays.asList(1.2, 2.3, 3.4, 5.6, 7.8);
		List<Number> nums = new ArrayList<>(ints);
		nums.addAll(dbls);

		printList(ints);
		System.out.println("ints sum=" + sumList(ints));

		printList(dbls);
		System.out.println("dbls sum=" + sumList(dbls));

		printList(nums);
		System.out.println("nums sum=" + sumList(nums));

		List<Number> doubled = doubleList(ints);
        printList(doubled);
        System.out.println("doubled sum=" + sumList(doubled));

        List<Number> upTo = new ArrayList<>();
        numsUpTo(5, upTo);
        printList(upTo);

        System.out.println("main done.");
	}

	protected static void numsUpTo(int num, List<? super Number> destList) {
        IntStream.rangeClosed(1, num).forEach(destList::add);
    }

    /**
     * Doubles each element in a List
     */
    protected static List<Number> doubleList(List<? extends Number> theList) {
        return theList.stream().mapToDouble(Number::doubleValue).map(d -> d * 2).boxed().collect(Collectors.toList());
    }

    /**
     * Uses wildcard to allow any List.
     */
    protected static void printList(List<?> list) {
        list.stream().forEach(System.out::println);
    }

	/**
	 * Uses upper-bounded wildcard so it can support different number types.
	 * @param numList
	 * @return
	 */
	protected static double sumList(List<? extends Number> numList) {
		return numList.stream().mapToDouble(Number::doubleValue).sum();
	}
}
