package misc.kata;

import java.util.ArrayList;
import java.util.List;

public class RectangleIntoSquares {
    public static List<Integer> sqInRect(int lng, int wdth) {
        if(lng == wdth || lng <= 0 || wdth <= 0) {
            return null;
        }

        if(lng < wdth) {
            List<Integer> nextSquares = sqInRect(wdth - lng, lng);
            List<Integer> finalSquares = new ArrayList<>();
            if(nextSquares != null) {
                finalSquares.add(lng);
                finalSquares.addAll(nextSquares);
            } else {
                finalSquares.add(lng);
                finalSquares.add(lng);
            }
            return finalSquares;
        } else {
            return sqInRect(wdth, lng);
        }
    }
}
