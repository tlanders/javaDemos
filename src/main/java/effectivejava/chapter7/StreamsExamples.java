package effectivejava.chapter7;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class StreamsExamples {
	public static void main(String [] args) throws Exception {
		if(args.length >= 1) {
			Files.lines(Paths.get(args[0])).collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()))
				.forEach((k,v) -> System.out.printf("k: %s, v: %d\n", k, v));
		} else {
			System.out.println("ERROR: please provide word file name.");
		}
	}
}
