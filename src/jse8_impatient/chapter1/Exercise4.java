package jse8_impatient.chapter1;

import java.io.File;
import java.util.Arrays;

/**
 * Created by tlanders on 11/22/2014.
 */
public class Exercise4 {
    public static void main(String [] args) {
        if (args.length < 1) {
            System.out.println("USAGE: java Exercise4 INPUT_DIR");
            System.exit(1);
        }

        System.out.println("Exercise4, dir=" + args[0]);

        Arrays.stream(new File(args[0]).listFiles()).sorted((file1, file2) -> {
            if(file1.isFile() && file2.isFile() || file1.isDirectory() && file2.isDirectory()) {
                // files are same types so compare their names
                return file1.getName().compareTo(file2.getName());
            } else if(file1.isDirectory()) {
                // types are different, directories are shown first so return -1
                return -1;
            } else {
                // types are different, files are shown last so return 1
                return 1;
            }
        }).forEach(f -> System.out.println((f.isFile() ? "file: " : "dir: ") + f.getName()));
    }
}
