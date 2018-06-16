package effectivejava.chapter7;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class StreamsExamples {
	public static void main(String [] args) throws Exception {
		if(args.length < 1) {
			System.out.println("ERROR: please provide word file name.");
		}
		
		Map<String, Long> freqMap = Files.lines(Paths.get(args[0])).collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));
		System.out.println(freqMap);
		
		List<String> topKeys = freqMap.keySet().stream()
				.sorted(comparing(freqMap::get).reversed())
				.limit(10)
				.collect(toList());
		
		topKeys.forEach(System.out::println);
	}
}
