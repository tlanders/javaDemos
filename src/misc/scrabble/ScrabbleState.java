package misc.scrabble;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tlanders on 7/17/2016.
 */
public class ScrabbleState {
    protected Map<String, Integer> unplayedTiles = new HashMap<>();

    public ScrabbleState() {
        initTiles();
    }

    protected void initTiles() {
        unplayedTiles.put("A", 9);
        unplayedTiles.put("B", 2);
        unplayedTiles.put("C", 2);
        unplayedTiles.put("D", 4);
        unplayedTiles.put("E", 12);
        unplayedTiles.put("F", 2);
        unplayedTiles.put("G", 3);
        unplayedTiles.put("H", 2);
        unplayedTiles.put("I", 9);
        unplayedTiles.put("J", 1);
        unplayedTiles.put("K", 1);
        unplayedTiles.put("L", 4);
        unplayedTiles.put("M", 2);
        unplayedTiles.put("N", 6);
        unplayedTiles.put("O", 8);
        unplayedTiles.put("P", 2);
        unplayedTiles.put("Q", 1);
        unplayedTiles.put("R", 6);
        unplayedTiles.put("S", 4);
        unplayedTiles.put("T", 6);
        unplayedTiles.put("U", 4);
        unplayedTiles.put("V", 2);
        unplayedTiles.put("W", 2);
        unplayedTiles.put("X", 1);
        unplayedTiles.put("Y", 2);
        unplayedTiles.put("Z", 1);
        unplayedTiles.put("_", 2);
    }
}
