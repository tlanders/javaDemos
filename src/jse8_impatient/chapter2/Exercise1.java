package jse8_impatient.chapter2;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tlanders on 11/22/2014.
 */
public class Exercise1 {
    public static void main(String [] args) throws Exception {
        System.out.println("Exercise1");

        if (args.length < 1) {
            System.out.println("USAGE: java Exercise1 INPUT_FILE");
            System.exit(1);
        }

        String contents = new String(Files.readAllBytes(
                Paths.get(args[0])), StandardCharsets.UTF_8); // Read file into string
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
        // Split into words; nonletters are delimiters

        long count = 0;
        for (String w : words) {
            System.out.println("  w=" + w);

            if (w.length() > 12) count++;
        }

        System.out.println("Exercise1, for loop count=" + count);

        // same as above but with parallel stream
        count = words.parallelStream().filter(w -> w.length() > 12).count();

        System.out.println("Exercise1, parallel stream count=" + count);


    }
}
