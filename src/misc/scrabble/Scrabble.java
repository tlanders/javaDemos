package misc.scrabble;

import java.util.stream.IntStream;

/**
 * Solution to Scrabble Sets problem.
 * https://dzone.com/articles/java-code-challenge-scrabble-sets
 */
public class Scrabble {
    public static void main(String [] args) throws Exception {
        System.out.println("Scrabble starting...");

        try {
            //playTiles("AEERTYOXMCNB_S");
            playTiles("PQAREIOURSTHGWIOAE_");
            playTiles("LQTOONOEFFJZT");
            playTiles("AXHDRUIOR_XHJZUQEE");
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Scrabble done.");
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
