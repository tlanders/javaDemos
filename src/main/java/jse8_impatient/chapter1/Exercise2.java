package jse8_impatient.chapter1;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by tlanders on 11/3/2014.
 */
public class Exercise2 {
    public static void main(String [] args) {
        if(args.length < 1) {
            System.out.println("USAGE: java Exercise2 INPUT_DIR");
            System.exit(1);
        }

        System.out.println("Exercise2, dir=" + args[0]);
        System.out.println("=== with inner class ===");
        File [] subDirs = (new File(args[0])).listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        for(File f : subDirs) {
            System.out.println("found subdir=" + f.getName());
        }

        System.out.println("=== with lambda ===");
        File [] subDirs1 = (new File(args[0])).listFiles(aFile -> aFile.isDirectory());

        for(File f : subDirs1) {
            System.out.println("found subdir=" + f.getName());
        }

        System.out.println("=== with method reference ===");
        File [] subDirs2 = (new File(args[0])).listFiles(File::isDirectory);

        for(File f : subDirs2) {
            System.out.println("found subdir=" + f.getName());
        }

    }
}
