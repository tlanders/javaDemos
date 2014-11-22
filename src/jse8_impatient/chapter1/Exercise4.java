package jse8_impatient.chapter1;

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
    }
}
