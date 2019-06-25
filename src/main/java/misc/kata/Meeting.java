package misc.kata;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * John has invited some friends. His list is:
 *
 * s = "Fred:Corwill;Wilfred:Corwill;Barney:Tornbull;Betty:Tornbull;Bjon:Tornbull;Raphael:Corwill;Alfred:Corwill";
 * Could you make a program that
 *
 * makes this string uppercase
 * gives it sorted in alphabetical order by last name.
 * When the last names are the same, sort them by first name. Last name and first name of a guest come in the result between parentheses separated by a comma.
 *
 * So the result of function meeting(s) will be:
 *
 * "(CORWILL, ALFRED)(CORWILL, FRED)(CORWILL, RAPHAEL)(CORWILL, WILFRED)(TORNBULL, BARNEY)(TORNBULL, BETTY)(TORNBULL, BJON)"
 * It can happen that in two distinct families with the same family name two people have the same first name too.
 */
public class Meeting {
    private static class NameData {
        public NameData(String f, String l) {
            first = f;
            last = l;
        }
        private final String first;
        private final String last;

        public String getFirst() {
            return first;
        }

        public String getLast() {
            return last;
        }
    }

    public static String meeting(final String s) {
        return Arrays.asList(s.toUpperCase().split(";"))
                .stream()
                .map(name -> {
                    String [] nameSplit = name.split(":");
                    return new NameData(nameSplit[0], nameSplit[1]);
                })
                .sorted(Comparator.comparing(NameData::getLast).thenComparing(NameData::getFirst))
                .map(nameData -> "(" + nameData.getLast() + ", " + nameData.getFirst() + ")")
                .collect(Collectors.joining(""));
    }

    public static void main(String [] args) {
        System.out.println();
    }
}
