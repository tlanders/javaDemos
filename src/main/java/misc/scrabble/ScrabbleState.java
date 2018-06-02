package misc.scrabble;

import java.util.*;

/**
 * Created by tlanders on 7/17/2016.
 */
public class ScrabbleState {
    protected Map<Character, Integer> unplayedTiles = new TreeMap<>();

    public ScrabbleState() {
        initTiles();
    }

    public void playTile(char tile) throws Exception {
        Integer tileCount = unplayedTiles.remove(tile);
        if(tileCount != null && tileCount > 0) {
            unplayedTiles.put(tile, tileCount - 1);
        } else {
            System.out.println("Invalid input. More " + tile + "'s have been taken from the bag than possible.");
            throw new Exception();
        }
    }

    public void outputState() {
        Map<Integer, List<Character>> outputMap = new TreeMap<>(Collections.reverseOrder());
        for(Character tile : unplayedTiles.keySet()) {
            Integer count = unplayedTiles.get(tile);
            if(count >= 0) {
                List<Character> tileList = outputMap.get(count);
                if (tileList == null) {
                    tileList = new ArrayList<>();
                    outputMap.put(count, tileList);
                }
                tileList.add(tile);
            }
        }

        for(Integer tileCount : outputMap.keySet()) {
            System.out.print(tileCount + " : ");
            List<Character> tList = outputMap.get(tileCount);
            System.out.print(tList.get(0));

            for(int i = 1; i < tList.size(); i++) {
                System.out.print(", " + tList.get(i));
            }
            System.out.print('\n');
        }
    }

    protected void initTiles() {
        unplayedTiles.put('A', 9);
        unplayedTiles.put('B', 2);
        unplayedTiles.put('C', 2);
        unplayedTiles.put('D', 4);
        unplayedTiles.put('E', 12);
        unplayedTiles.put('F', 2);
        unplayedTiles.put('G', 3);
        unplayedTiles.put('H', 2);
        unplayedTiles.put('I', 9);
        unplayedTiles.put('J', 1);
        unplayedTiles.put('K', 1);
        unplayedTiles.put('L', 4);
        unplayedTiles.put('M', 2);
        unplayedTiles.put('N', 6);
        unplayedTiles.put('O', 8);
        unplayedTiles.put('P', 2);
        unplayedTiles.put('Q', 1);
        unplayedTiles.put('R', 6);
        unplayedTiles.put('S', 4);
        unplayedTiles.put('T', 6);
        unplayedTiles.put('U', 4);
        unplayedTiles.put('V', 2);
        unplayedTiles.put('W', 2);
        unplayedTiles.put('X', 1);
        unplayedTiles.put('Y', 2);
        unplayedTiles.put('Z', 1);
        unplayedTiles.put('_', 2);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("Game state:\n");
        for(char tile : unplayedTiles.keySet()) {
            str.append(tile).append(" : ").append(unplayedTiles.get(tile)).append('\n');
        }
        return str.toString();
    }
}
