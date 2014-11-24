package jse8_impatient.chapter1;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by tlanders on 11/23/2014.
 *
 * Interface with default method for exercise 9.
 */
interface Collection2<T> extends Collection<T> {
    default void forEachIf(Consumer<T> action, Predicate<T> filter) {
        forEach(obj -> {
            if(filter.test(obj)) {
                action.accept(obj);
            }
        });
    }
}
