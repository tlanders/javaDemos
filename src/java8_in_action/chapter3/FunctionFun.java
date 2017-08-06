package java8_in_action.chapter3;

import java.util.function.Function;

public class FunctionFun {
    public static void main(String[] args) {
        System.out.println("FunctionFun starting.");

        Function<Integer, Integer> addTwo = n -> n + 2;
        Function<Integer, Integer> timesThree = n -> n * 3;
        Function<Integer, Integer> andThenOps = addTwo.andThen(timesThree);
        Function<Integer, Integer> composedOps = addTwo.compose(timesThree);

        Integer num1 = 12;
//        Integer num2 = -4;

        System.out.println("num1=" + num1);
        System.out.println("addTwo=" + addTwo.apply(num1));
        System.out.println("timesThree=" + timesThree.apply(num1));
        System.out.println("andThen=" + andThenOps.apply(num1));
        System.out.println("composed=" + composedOps.apply(num1));

//        System.out.println("num2=" + num2);

        System.out.println("FunctionFun done.");
    }
}