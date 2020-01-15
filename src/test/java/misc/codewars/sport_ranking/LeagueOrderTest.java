package misc.codewars.sport_ranking;

import org.junit.*;
import static org.junit.Assert.*;
//import org.junit.runners.*;

public class LeagueOrderTest {

    @Test
    public void basicTest() {
        assertArrayEquals(new int[]{1, 2}, LeagueOrder.computeRanks(2, new int[][]{{0, 1, 1, 0}}));
    }

    @Test
    public void example1() {
        assertArrayEquals(new int[]{4,4,6,3,1,2},
                LeagueOrder.computeRanks(6, new int[][]
                        {{0, 5, 2, 2},
                                {1, 4, 0, 2},
                                {2, 3, 1, 2},
                                {1, 5, 2, 2},
                                {2, 0, 1, 1},
                                {3, 4, 1, 1},
                                {2, 5, 0, 2},
                                {3, 1, 1, 1},
                                {4, 0, 2, 0}}));
    }

    @Test
    public void example2() {
        assertArrayEquals(new int[]{2,3,4,1,5,6},
                LeagueOrder.computeRanks(6, new int[][]
                        {{0, 5, 2, 0},
                                {1, 4, 2, 2},
                                {2, 3, 1, 3},
                                {1, 5, 0, 0},
                                {2, 0, 2, 1},
                                {3, 4, 3, 1}}));
    }

    @Test
    public void example3() {
        assertArrayEquals(new int[]{3,1,1,3},
                LeagueOrder.computeRanks(4, new int[][]
                        {{0, 3, 1, 1},
                                {1, 2, 2, 2},
                                {1, 3, 2, 0},
                                {2, 0, 2, 0}}));
    }

    @Test
    public void exampleEmpty() {
        assertArrayEquals(new int[]{1,1,1,1,1,1,1,1,1,1},
                LeagueOrder.computeRanks(10, new int[0][]));
    }

    @Test
    public void exampleOneGame() {
        assertArrayEquals(new int[]{1,2,2,2,2,2,2,8},
                LeagueOrder.computeRanks(8, new int[][]{{0, 7, 2, 0}}));
    }

}
