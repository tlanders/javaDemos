package jse8_impatient.chapter2;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

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

    }
}
