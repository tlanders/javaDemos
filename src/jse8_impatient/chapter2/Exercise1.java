package jse8_impatient.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tlanders on 11/22/2014.
 */
public class Exercise1 {
    public static void main(String [] args) throws Exception {
        System.out.println("Exercise8");

        // runners using enhanced for loop
        String[] names = { "Peter", "Paul", "Mary" };
        List<Runnable> runners = new ArrayList<>();
        for(String name : names) {
            runners.add(() -> System.out.println("1: " + name));
        }

        for(Runnable r : runners) {
            new Thread(r).start();
        }

        Thread.sleep(1000); // let these threads finish

        // runners using standard for loop. have to use assignment inside for loop (i.e. not names2[i]) to
        // make compiler happy.
        String[] names2 = { "Peter", "Paul", "Mary" };
        List<Runnable> runners2 = new ArrayList<>();
        for(int i = 0; i < names2.length; i++) {
            String n = names2[i];
            runners2.add(() -> System.out.println("2: " + n));
        }

        for(Runnable r : runners2) {
            new Thread(r).start();
        }
    }
}
