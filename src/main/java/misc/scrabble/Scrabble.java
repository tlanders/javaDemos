package misc.scrabble;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Solution to Scrabble Sets problem.
 * https://dzone.com/articles/java-code-challenge-scrabble-sets
 */
public class Scrabble {
    protected static final Map<Character, Integer> TILES = new TreeMap<>();

    static {
        TILES.put('A', 9);
        TILES.put('B', 2);
        TILES.put('C', 2);
        TILES.put('D', 4);
        TILES.put('E', 12);
        TILES.put('F', 2);
        TILES.put('G', 3);
        TILES.put('H', 2);
        TILES.put('I', 9);
        TILES.put('J', 1);
        TILES.put('K', 1);
        TILES.put('L', 4);
        TILES.put('M', 2);
        TILES.put('N', 6);
        TILES.put('O', 8);
        TILES.put('P', 2);
        TILES.put('Q', 1);
        TILES.put('R', 6);
        TILES.put('S', 4);
        TILES.put('T', 6);
        TILES.put('U', 4);
        TILES.put('V', 2);
        TILES.put('W', 2);
        TILES.put('X', 1);
        TILES.put('Y', 2);
        TILES.put('Z', 1);
        TILES.put('_', 2);
    }

    public static void main(String [] args) throws Exception {
        System.out.println("Scrabble starting...");

        try {
            //playTiles("AEERTYOXMCNB_S");
            playTiles("PQAREIOURSTHGWIOAE_");
            playTiles("LQTOONOEFFJZT");
            playTiles("AXHDRUIOR_XHJZUQEE");
        } catch(Exception ex) {
            //ex.printStackTrace();
        }

        playTilesFunc("PQAREIOURSTHGWIOAE_");
        playTilesFunc("LQTOONOEFFJZT");
        playTilesFunc("AXHDRUIOR_XHJZUQEE");

        System.out.println("Scrabble done.");
    }

    protected static void playTilesFunc(String tiles) {
        Map<Character, Integer> gameState = new TreeMap<>(TILES);

        tiles.chars().forEach(ch -> gameState.put((char) ch, gameState.get((char) ch) - 1));

        List<Character> errors = gameState.entrySet().stream()
                .filter(entry -> entry.getValue() < 0).map(Map.Entry::getKey).collect(Collectors.toList());

        if(errors.isEmpty()) {
            Map<Integer,String> summary = gameState.entrySet().stream().collect(
                    Collectors.groupingBy(
                            Map.Entry::getValue,
                            Collectors.mapping(
                                    Map.Entry::getKey,
                                    Collectors.mapping(String::valueOf, Collectors.joining(", ")))));

            summary.entrySet().stream().sorted((a,b) -> b.getKey().compareTo(a.getKey()))
                    .forEachOrdered(entry -> {
                        if(entry.getValue().length() != 0) {
                            System.out.printf("%d : %s\n", entry.getKey(), entry.getValue());
                        }
                    });
            //dumpState(gameState);
        } else {
            errors.stream().forEach(ch ->
                System.out.println("Invalid input. More " + ch + "'s have been taken from the bag than possible.")
            );
        }

        //gameState.outputState();

        //System.out.print(gameState);
//        System.out.println("playing tiles=" + tiles);
//
//        for(int i = 0; i < tiles.length(); i++) {
//            char tile = tiles.charAt(i);
//            //System.out.println("playing tile=" + tile);
//
//            gameState.playTile(tile);
//        }
//        System.out.println("done playing tiles");
//        //System.out.print(gameState);
//        gameState.outputState();
    }

    protected static void dumpState(Map<Character, Integer> state) {
        state.keySet().forEach(ch -> System.out.println(ch + " : " + state.get(ch)));
    }

    protected static void playTiles(String tiles) throws Exception {
        ScrabbleState gameState = new ScrabbleState();
        //System.out.print(gameState);
        System.out.println("playing tiles=" + tiles);

        for(int i = 0; i < tiles.length(); i++) {
            char tile = tiles.charAt(i);
            //System.out.println("playing tile=" + tile);

            gameState.playTile(tile);
        }
        System.out.println("done playing tiles");
        //System.out.print(gameState);
        gameState.outputState();
    }
}
