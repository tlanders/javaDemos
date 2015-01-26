package jse8_impatient.chapter3;

import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

/**
 * Created by tlanders.
 */
public class Exercise2 {
    private static Logger logger = Logger.getLogger(Exercise2.class.getName());

    public static void main(String [] args) throws Exception {
        System.out.println("Exercise2");

        withLock(new ReentrantLock(), () -> System.out.println("printing with lock"));

        System.out.println("Exercise2, done");
    }

    public static void withLock(ReentrantLock myLock, Runnable action) {
        myLock.lock();
        try {
            action.run();
        } finally {
            myLock.unlock();
        }
    }
}
