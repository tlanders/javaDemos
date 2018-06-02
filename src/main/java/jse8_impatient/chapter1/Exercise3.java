package jse8_impatient.chapter1;

import java.io.File;

/**
 * Created by tlanders on 11/3/2014.
 */
public class Exercise3 {
    public static void main(String [] args) {
        if (args.length < 2) {
            System.out.println("USAGE: java Exercise3 INPUT_DIR EXTENSION");
            System.exit(1);
        }

        System.out.println("Exercise3, dir=" + args[0] + ", ext=" + args[1]);
        System.out.println("=== with lambda ===");

        String[] fileNames = (new File(args[0])).list((dir, fileName) -> fileName.endsWith(args[1]) && (new File(dir, fileName)).isFile());

        for(String name : fileNames) {
            System.out.println("found file=" + name);
        }
    }
}
