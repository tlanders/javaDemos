package jse8_impatient.chapter2;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Created by tlanders.
 */
public class Exercise12 {
    public static void main(String [] args) throws Exception {
        System.out.println("Chapter2 - Exercise12");

        if (args.length < 1) {
            System.out.println("USAGE: java Exercise12 INPUT_FILE");
            System.exit(1);
        }

        String contents = new String(Files.readAllBytes(
                Paths.get(args[0])), StandardCharsets.UTF_8); // Read file into string
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        AtomicInteger[] shortWords = new AtomicInteger[12];
        IntStream.range(0, shortWords.length).parallel().forEach(index -> shortWords[index] = new AtomicInteger(0));

        words.parallelStream().forEach( item -> {
            if(item.length() <= shortWords.length && item.length() > 0) {
                shortWords[item.length() - 1].incrementAndGet();
            }
        });

        IntStream.range(0, shortWords.length).forEach(i -> System.out.println((i + 1) + ": " + shortWords[i]));
    }
}
