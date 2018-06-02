package jse8_impatient.chapter1;

import java.io.File;
import java.util.Arrays;

/**
 * Created by tlanders on 11/22/2014.
 */
public class Exercise6 {
    public static void main(String [] args) {
        System.out.println("Exercise6");

        // runnable lambda that catches checked exceptions
        new Thread(() -> {
            for(int i = 0; i < 10; i++) {
                System.out.println("running, i=" + i);
                try {
                    Thread.sleep(500);
                } catch(Exception e) {
                    // do nothing
                }
            }
            System.out.println("exiting");
        }).start();

        // uncheck catches checked exceptions and rethrows them as unchecked so that Runnable lambda
        // will compile.
        // can't use Callable because Thread requires Runnable objects.
        new Thread(uncheck(() -> {
            for(int i = 0; i < 10; i++) {
                System.out.println("uncheck running, i=" + i);
                Thread.sleep(500);
            }
            System.out.println("uncheck exiting");
        })).start();
    }

    public static Runnable uncheck(RunnableEx runner) {
        return () -> {
            try {
                runner.run();
            } catch(Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}

