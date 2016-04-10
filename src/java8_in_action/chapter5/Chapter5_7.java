package java8_in_action.chapter5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by tlanders on 4/6/2016.
 */
public class Chapter5_7 {
    public static void main(String [] args) {
        System.out.println("Chapter 5_7");

        try(Stream<String> lines = Files.lines(Paths.get("c:\\", "bdlog.txt"))) {

            List<String> uniqueWords = lines.flatMap(l -> Arrays.stream(l.split(" ")))
                    .distinct()
                    .collect(Collectors.toList());
            long uniqueWordCount = uniqueWords.size();
            System.out.println("unique words=" + uniqueWordCount);

            uniqueWords.stream().forEach(System.out::println);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        System.out.println("Chapter 5_7 done.");
    }
}
