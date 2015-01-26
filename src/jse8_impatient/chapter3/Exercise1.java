package jse8_impatient.chapter3;

import java.util.function.BooleanSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

/**
 * Created by tlanders.
 */
public class Exercise1 {
    private static Logger logger = Logger.getLogger(Exercise1.class.getName());

    public static void main(String [] args) throws Exception {
        System.out.println("Exercise1");

        IntStream.range(0, 5).forEach(i -> {
            BooleanSupplier condition = () -> i >= 3;
            logIf(Level.FINEST, condition, () -> "Finest log message, i=" + i);
            logIf(Level.INFO, condition, () -> "Info log message, i=" + i);
            logIf(Level.WARNING, condition, () -> "Warning log message, i=" + i);
            logIf(Level.SEVERE, condition, () -> "Severe log message, i=" + i);
        });

        System.out.println("Exercise1, done");
    }

    public static void logIf(Level level, BooleanSupplier ifCondition, Supplier<String> message) {
        if(logger.isLoggable(level) && ifCondition.getAsBoolean()) {
            logger.log(level, message.get());
        }
    }
}
