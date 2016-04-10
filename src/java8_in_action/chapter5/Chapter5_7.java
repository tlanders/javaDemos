package java8_in_action.chapter5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntSupplier;
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

        // generating first 20 fibonacci tuples with iterate
        Stream.iterate(new int[] {0,1}, n -> new int[] {n[1], n[0] + n[1]})
                .limit(20)
                .forEach(n-> System.out.println("(" + n[0] + "," + n[1] + ")"));

        // generating first 20 fibonaccci values with stateful generate (generally a bad thing because use in
        // multiple threads)
        IntStream.generate(new IntSupplier() {
            private int v1 = 0;
            private int v2 = 1;
            @Override
            public int getAsInt() {
                int tempVal = v1;
                v1 = v2;
                v2 = v1 + tempVal;
                return tempVal;
            }
        }).limit(20).forEach(System.out::println);

        System.out.println("Chapter 5_7 done.");
    }
}
