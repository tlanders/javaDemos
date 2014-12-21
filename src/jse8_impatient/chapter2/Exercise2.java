package jse8_impatient.chapter2;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tlanders.
 */
public class Exercise2 {
    public static void main(String [] args) throws Exception {
        System.out.println("Exercise2");

        if (args.length < 1) {
            System.out.println("USAGE: java Exercise2 INPUT_FILE");
            System.exit(1);
        }

        String contents = new String(Files.readAllBytes(Paths.get(args[0])), StandardCharsets.UTF_8); // Read file into string
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+")); // Split into words; nonletters are delimiters

        // filter with limit of 5
        long count = words.stream().filter(w -> w.length() > 12).peek(e -> System.out.println("filter called, word=" + e)).limit(5).count();

        System.out.println("Exercise2, filter limit of 5 stream count=" + count);
    }
}
