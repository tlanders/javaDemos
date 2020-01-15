package misc.codewars.sport_ranking;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Solution to https://www.codewars.com/kata/5e0baea9d772160032022e8c.
 */
public class LeagueOrder {
    public static int[] computeRanks(int number, int[][] games) {
        List<GameResult> results = Arrays.stream(games)
                .map((int [] result) -> new GameResult(result[0], result[1], result[2], result[3]))
                .collect(Collectors.toList());

        Set<TeamResult> trSet = results.stream()
                .map(gameResult -> Stream.of(
                        new TeamResult(gameResult.getHomeTeam(),
                                gameResult.isHomeWinner(),
                                gameResult.isVisitingWinner(),
                                gameResult.getScoreDifferential(),
                                gameResult.getHomeScore()),
                        new TeamResult(gameResult.getVisitingTeam(),
                                gameResult.isVisitingWinner(),
                                gameResult.isHomeWinner(),
                                gameResult.getScoreDifferential() * -1,
                                gameResult.getVisitingScore()))
                        )
                .flatMap(Function.identity())
                .collect(HashSet::new,
                        (HashSet set, TeamResult tr) -> set.add(tr),
                        (s1, s2) -> s1.addAll(s2)
                );

        System.out.println("teams: " + trSet.size());
        System.out.println(trSet);
        // your solution

        TeamResult2 [] teamArray = Arrays.stream(games)
                .map(result -> Stream.of(
                            new TeamResult2(result[0], result[2], result[3]),
                            new TeamResult2(result[1], result[3], result[2])
                        ))
                .flatMap(Function.identity())
                .collect(() -> (TeamResult2[]) new TeamResult2[number],
                        (BiConsumer<TeamResult2[], TeamResult2>) (TeamResult2[] arr, TeamResult2 tr) -> arr[tr.getTeam()] = tr,
                        (TeamResult2 [] t1, TeamResult2 [] t2) -> {
                            for(int i = 0; i < number; i++) {
                                if(t1[i] != null) {
                                    t1[i].add(t2[i]);
                                } else {
                                    t1[i] = t2[i];
                                }
                            }
                        }
                );

        System.out.println("teams: " + teamArray.length);
        System.out.println(Arrays.toString(teamArray));
        // your solution


        return null;
    }

    private static class TeamResult2 {
        private int team;
        private int wins;
        private int losses;
        private int draws;
        private int scoreDifferential;
        private int goalsScored;

        public TeamResult2(int team, int teamScore, int opponentScore) {
            this.team = team;
            this.wins = teamScore > opponentScore ? 1 : 0;
            this.losses = opponentScore > teamScore ? 1 : 0;
            this.draws = teamScore == opponentScore ? 1 : 0;
            this.scoreDifferential = teamScore - opponentScore;
            this.goalsScored = teamScore;
        }

        @Override
        public String toString() {
            return "TeamResult2{" +
                    "team=" + team +
                    ", wins=" + wins +
                    ", losses=" + losses +
                    ", draws=" + draws +
                    ", scoreDifferential=" + scoreDifferential +
                    ", goalsScored=" + goalsScored +
                    '}';
        }

        public void add(TeamResult2 t) {
            if(t != null) {
                wins += t.wins;
                losses += t.losses;
            }
        }

        public int getPoints() {
            return wins * 2 + draws;
        }

        public int getTeam() {
            return team;
        }

        public void setTeam(int team) {
            this.team = team;
        }

        public int getWins() {
            return wins;
        }

        public void setWins(int wins) {
            this.wins = wins;
        }

        public int getLosses() {
            return losses;
        }

        public void setLosses(int losses) {
            this.losses = losses;
        }

        public int getDraws() {
            return draws;
        }

        public void setDraws(int draws) {
            this.draws = draws;
        }

        public int getScoreDifferential() {
            return scoreDifferential;
        }

        public void setScoreDifferential(int scoreDifferential) {
            this.scoreDifferential = scoreDifferential;
        }

        public int getGoalsScored() {
            return goalsScored;
        }

        public void setGoalsScored(int goalsScored) {
            this.goalsScored = goalsScored;
        }
    }

    private static class TeamResult {
        private int teamNumber;
        private int wins;
        private int losses;
        private int scoreDifferential;
        private int goalsScored;

        public TeamResult(int teamNumber, int wins, int losses, int scoreDifferential, int goalsScored) {
            this.teamNumber = teamNumber;
            this.wins = wins;
            this.losses = losses;
            this.scoreDifferential = scoreDifferential;
            this.goalsScored = goalsScored;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TeamResult that = (TeamResult) o;
            return teamNumber == that.teamNumber;
        }

        @Override
        public int hashCode() {
            return Objects.hash(teamNumber);
        }

        @Override
        public String toString() {
            return "TeamResult{" +
                    "teamNumber=" + teamNumber +
                    ", wins=" + wins +
                    ", losses=" + losses +
                    ", scoreDifferential=" + scoreDifferential +
                    ", goalsScored=" + goalsScored +
                    '}';
        }

        public int getTeamNumber() {
            return teamNumber;
        }

        public void setTeamNumber(int teamNumber) {
            this.teamNumber = teamNumber;
        }

        public int getWins() {
            return wins;
        }

        public void setWins(int wins) {
            this.wins = wins;
        }

        public int getLosses() {
            return losses;
        }

        public void setLosses(int losses) {
            this.losses = losses;
        }

        public int getScoreDifferential() {
            return scoreDifferential;
        }

        public void setScoreDifferential(int scoreDifferential) {
            this.scoreDifferential = scoreDifferential;
        }

        public int getGoalsScored() {
            return goalsScored;
        }

        public void setGoalsScored(int goalsScored) {
            this.goalsScored = goalsScored;
        }
    }
    private static class GameResult {
        private int homeTeam;
        private int visitingTeam;
        private int homeScore;
        private int visitingScore;

        public GameResult(int homeTeam, int visitingTeam, int homeScore, int visitingScore) {
            this.homeTeam = homeTeam;
            this.visitingTeam = visitingTeam;
            this.homeScore = homeScore;
            this.visitingScore = visitingScore;
        }

        public int getScoreDifferential() {
            return homeScore - visitingScore;
        }

        public int isHomeWinner() {
            return homeScore > visitingScore ? 1 : 0;
        }

        public int isVisitingWinner() {
            return isHomeWinner() > 0 ? 0 : 1;
        }

        public int getHomeTeam() {
            return homeTeam;
        }

        public void setHomeTeam(int homeTeam) {
            this.homeTeam = homeTeam;
        }

        public int getVisitingTeam() {
            return visitingTeam;
        }

        public void setVisitingTeam(int visitingTeam) {
            this.visitingTeam = visitingTeam;
        }

        public int getHomeScore() {
            return homeScore;
        }

        public void setHomeScore(int homeScore) {
            this.homeScore = homeScore;
        }

        public int getVisitingScore() {
            return visitingScore;
        }

        public void setVisitingScore(int visitingScore) {
            this.visitingScore = visitingScore;
        }
    }
}
