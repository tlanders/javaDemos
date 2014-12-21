package jse8_impatient.chapter2;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by tlanders.
 */
public class Exercise4 {
    public static void main(String [] args) throws Exception {
        System.out.println("Exercise4");

//        if (args.length < 1) {
//            System.out.println("USAGE: java Exercise4 INPUT_FILE");
//            System.exit(1);
//        }

        int[] values = {1, 4, 9, 16};
        String[] strVals = {"a", "b", "c"};

        // stream has only one int[] element
        Stream.of(values).forEach(val -> System.out.println("val=" + val + ", type=" + val.getClass().getCanonicalName()));

        // stream has val for each string in array
        Stream.of(strVals).forEach(val -> System.out.println("val=" + val + ", type=" + val.getClass().getCanonicalName()));

        // stream has only one int[] element
        Arrays.stream(values).forEach(val -> System.out.println("val=" + val));
    }
}
