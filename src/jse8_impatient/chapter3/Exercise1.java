package jse8_impatient.chapter3;

import java.util.function.BooleanSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

/**
 * Created by tlanders on 11/22/2014.
 */
public class Exercise1 {
    private static Logger logger = Logger.getLogger(Exercise1.class.getName());

    public static void main(String [] args) throws Exception {
        System.out.println("Exercise1");

        IntStream.iterate(0, i -> i + 1).limit(5).forEach(i -> {
            logIf(Level.FINEST, () -> i >= 3, () -> "Finest log message, i=" + i);
            logIf(Level.INFO, () -> i >= 3, () -> "Info log message, i=" + i);
            logIf(Level.WARNING, () -> i >= 3, () -> "Warning log message, i=" + i);
            logIf(Level.SEVERE, () -> i >= 3, () -> "Severe log message, i=" + i);
        });

        System.out.println("Exercise1, done");
    }

    public static void logIf(Level level, BooleanSupplier ifCondition, Supplier<String> message) {
        if(logger.isLoggable(level)) {
            if(ifCondition.getAsBoolean()) {
                logger.log(level, message.get());
            }
        }
    }
}
