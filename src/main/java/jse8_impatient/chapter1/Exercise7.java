package jse8_impatient.chapter1;

/**
 * Created by tlanders on 11/22/2014.
 */
public class Exercise7 {
    public static void main(String [] args) {
        System.out.println("Exercise7");

        // uncheck catches checked exceptions and rethrows them as unchecked so that Runnable lambda
        // will compile.
        new Thread(andThen(
                () -> {
                    System.out.println("run1 starting");
                    Thread.sleep(1000);
                    System.out.println("run1 exiting");
                },
                () -> {
                    System.out.println("run2 starting");
                    Thread.sleep(1000);
                    System.out.println("run2 exiting");
                })
        ).start();
    }

    public static Runnable andThen(RunnableEx run1, RunnableEx run2) {
        return () -> {
            try {
                run1.run();
                run2.run();
            } catch (Exception e) {
                // catch all checked exceptions and rethrow as unchecked
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        };
    }
}
