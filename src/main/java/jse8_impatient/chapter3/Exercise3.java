package jse8_impatient.chapter3;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * Created by tlanders.
 */
public class Exercise3 {
    private static Logger logger = Logger.getLogger(Exercise3.class.getName());

    public static void main(String [] args) throws Exception {
        System.out.println("Exercise3");

        Assert.assertCheck(() -> 1 <= 3, () -> "this will pass");
        Assert.assertCheck(() -> 4 <= 3, () -> "this will fail");
    }
}

class Assert {
    public static void assertCheck(BooleanSupplier condition) {
        if(!condition.getAsBoolean()) {
            throw new AssertionError();
        }
    }
    public static void assertCheck(BooleanSupplier condition, Supplier<String> errorMessage) {
        if(!condition.getAsBoolean()) {
            throw new AssertionError(errorMessage.get());
        }
    }
}
