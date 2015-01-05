package jse8_impatient.chapter2;

import java.util.stream.Stream;

/**
 * Created by tlanders.
 */
public class Exercise10 {
    public static void main(String [] args) throws Exception {
        System.out.println("Chapter2 - Exercise10");

        Stream<Double> sd = Stream.of(9.0, 1.2, 2.3, 3.4, 4.5, -2.0);

        double avg = average(sd);

        System.out.println("  stream avg=" + avg);
    }

    public static double average(Stream<Double> sd) {
        return sd.reduce(new Averager(),
                (a1, d1) -> new Averager(a1.sum + d1.doubleValue(), a1.count + 1),
                (a1, a2) -> new Averager(a1.sum + a2.sum, a1.count + a2.count)).average();
    }

    static class Averager {
        public final double sum;
        public final int count;

        Averager() {
            this(0,0);
        }

        Averager(double s, int c) {
            sum = s;
            count = c;
        }

        public double average() {
            return sum / count;
        }
    }
}
