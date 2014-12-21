package jse8_impatient.chapter2;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tlanders.
 */
public class Exercise3 {
    public static void main(String [] args) throws Exception {
        System.out.println("Exercise3");

        if (args.length < 1) {
            System.out.println("USAGE: java Exercise3 INPUT_FILE");
            System.exit(1);
        }

        String contents = new String(Files.readAllBytes(Paths.get(args[0])), StandardCharsets.UTF_8); // Read file into string
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+")); // Split into words; nonletters are delimiters


        // count using stream
        long startTime = System.currentTimeMillis();
        long count;
        for(int i = 0; i < 10; i++) {
            count = words.stream().filter(w -> w.length() > 12).count();
            System.out.println("Exercise3, stream count=" + count);
        }
        long stopTime = System.currentTimeMillis();
        System.out.println("Exercise3, stream time=" + (stopTime - startTime));

        // count using parallel
        startTime = System.currentTimeMillis();
        long parallelCount;
        for(int i = 0; i < 10; i++) {
            parallelCount = words.parallelStream().filter(w -> w.length() > 12).count();
            System.out.println("Exercise3, parallel count=" + parallelCount);
        }
        stopTime = System.currentTimeMillis();

        System.out.println("Exercise3, parallel time=" + (stopTime - startTime));
    }
}
