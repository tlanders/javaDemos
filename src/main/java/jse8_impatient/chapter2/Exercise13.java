package jse8_impatient.chapter2;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by tlanders.
 */
public class Exercise13 {
    public static void main(String [] args) throws Exception {
        System.out.println("Chapter2 - Exercise13");

        if (args.length < 1) {
            System.out.println("USAGE: java Exercise13 INPUT_FILE");
            System.exit(1);
        }

        String contents = new String(Files.readAllBytes(
                Paths.get(args[0])), StandardCharsets.UTF_8); // Read file into string
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        words.parallelStream().filter(s -> s.length() <= 12 && s.length() > 0)
                .collect(Collectors.groupingByConcurrent(String::length, Collectors.counting()))
                .forEach((key, val) -> System.out.println(key + ": " + val));
    }
}
