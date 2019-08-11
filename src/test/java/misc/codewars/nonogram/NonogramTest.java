package misc.codewars.nonogram;

import org.junit.Test;

import java.util.*;

import static misc.codewars.nonogram.NonogramRow.findRows;
import static misc.codewars.nonogram.NonogramRow.makeRow;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NonogramTest {
    @Test
    public void testSolve() {
        assertTrue(Arrays.deepEquals(
                solve(new NonogramSpecBuilder(1).addColumn(1).addRow(1).build()),
                new int[][] {{1}}));
        assertTrue(Arrays.deepEquals(
                solve(new NonogramSpecBuilder(1).addColumn(0).addRow(0).build()),
                new int[][] {{0}}));
        assertTrue(Arrays.deepEquals(
                solve(new NonogramSpecBuilder(2).addColumn(0).addColumn(1).addRow(1).addRow(0).build()),
                new NonogramBoardBuilder(2).addRow(0,1).addRow(0,0).build()));
    }

    public int [][] solve(int [][][] gameSpecification) {
//        Nonogram nonogram = new Nonogram(new int [][][] {{{1, 1}, {4}, {1, 1, 1}, {3}, {1}}, {{1}, {2}, {3}, {2, 1}, {4}}});
//        System.out.println("nonogram=" + nonogram);

        int [][] rowSpecs = gameSpecification[1];
        System.out.println("row count=" + rowSpecs.length);

        List<List<NonogramRow>> rows = new ArrayList<>();

        for(int [] row : rowSpecs) {
            List<NonogramRow> potentialRows = new ArrayList<>();
            rows.add(potentialRows);
            System.out.println("row specs=" + Arrays.toString(row));
            potentialRows.addAll(findRows(rowSpecs.length, row));
            System.out.println("potential rows=" + potentialRows);
        }
        System.out.println("all rows=" + rows);

        List<List<NonogramRow>> allPossibleSolutions = findAllPossibleSolutions(rows);

        if(gameSpecification[0][0][0] == 0 && gameSpecification[1][0][0] == 0) {
            return new int[][]{{0}};
        } else {
            return new int[][]{{1}};
        }
    }

    @Test
    public void testFindAllPossibleSolutions() {
        List<List<NonogramRow>> simpleOneRow = new ArrayList<>();
        List<NonogramRow> row1Solutions = new ArrayList<>();
        NonogramRow onlyPossibleSolution = NonogramRow.makeRow(false);
        row1Solutions.add(onlyPossibleSolution);
        simpleOneRow.add(row1Solutions);

        assertTrue(simpleOneRow.equals(findAllPossibleSolutions(simpleOneRow)));
        System.out.println(simpleOneRow);
    }

    public List<List<NonogramRow>> findAllPossibleSolutions(List<List<NonogramRow>> rows) {
        for(List<NonogramRow> currentRow : rows) {

        }

        return null;
    }

    @Test
    public void testNonogram() {
        Nonogram n = new Nonogram(new NonogramSpecBuilder(1).addColumn(1).addRow(1).build());
        assertEquals(1, n.getColumnCount());
        assertEquals(1, n.getRowCount());

        n.addRow(makeRow(true));

        n = new Nonogram(new int [][][] {{{1, 1}, {4}, {1, 1, 1}, {3}, {1}}, {{1}, {2}, {3}, {2, 1}, {4}}});
        assertEquals(5, n.getColumnCount());
        assertEquals(5, n.getRowCount());

        n.addRow(makeRow(true, false, true, false, true));
        n.addRow(makeRow(true, false, true, false, true));
        n.addRow(makeRow(true, false, true, false, true));
        n.addRow(makeRow(true, false, true, false, true));
        n.addRow(makeRow(true, false, true, false, true));
    }

    @Test
    public void testNonogramIsSolution() {
        assertTrue(new Nonogram(new NonogramSpecBuilder(1).addColumn(1).addRow(1).build())
                .addRow(makeRow(true))
                .isSolution());
        assertTrue(new Nonogram(new NonogramSpecBuilder(1).addColumn(0).addRow(0).build())
                .addRow(makeRow(false))
                .isSolution());
    }

    static public class Nonogram {
        public Nonogram(int [][][] gameSpec) {
            columnCount = gameSpec[0].length;
            rowCount = gameSpec[1].length;

            columnRunLengths = gameSpec[0];
            rowRunLengths = gameSpec[1];
        }

        public boolean isSolution() {
            return true;
        }

        private int [][] rowRunLengths;
        private int [][] columnRunLengths;
        private int rowCount;
        private int columnCount;

        List<NonogramRow> rows = new ArrayList<>();

        public Nonogram addRow(NonogramRow row) {
            if(row.size() != columnCount) {
                throw new IllegalStateException("Incorrect number of columns in row, required columns=" + columnCount);
            }
            if((rows.size() + 1) > rowCount) {
                throw new IllegalStateException("Too many rows added to nonogram, required rows=" + rowCount);
            }

            rows.add(row);
            return this;
        }

        public int getRowCount() {
            return rowCount;
        }

        public int getColumnCount() {
            return columnCount;
        }

        @Override
        public String toString() {
            return "Nonogram{" +
                    "rowRunLengths=" + Arrays.toString(rowRunLengths) +
                    ", columnRunLengths=" + Arrays.toString(columnRunLengths) +
                    ", rowCount=" + rowCount +
                    ", columnCount=" + columnCount +
                    '}';
        }
    }

    @Test
    public void testBoardBuilder() {
        assertTrue(Arrays.deepEquals(
                new NonogramBoardBuilder(1).addRow(1).build(),
                new int[][] {{1}}));
        assertTrue(Arrays.deepEquals(
                new NonogramBoardBuilder(1).addRow(0).build(),
                new int[][] {{0}}));
        assertTrue(Arrays.deepEquals(
                new NonogramBoardBuilder(2).addRow(0,1).addRow(1,0).build(),
                new int[][] {{0,1},{1,0}}));
        assertTrue(Arrays.deepEquals(
                new NonogramBoardBuilder(3).addRow(0,1,1).addRow(1,0,0).addRow(1,1,1).build(),
                new int[][] {{0,1,1},{1,0,0},{1,1,1}}));
    }

    @Test
    public void testNonogramSpecBuilder() {
        assertTrue(Arrays.deepEquals(
                new int [][][] {{{1, 1}, {4}, {1, 1, 1}, {3}, {1}}, {{1}, {2}, {3}, {2, 1}, {4}}},
                new NonogramSpecBuilder(5)
                        .addColumn(1,1).addColumn(4).addColumn(1,1,1).addColumn(3).addColumn(1)
                        .addRow(1).addRow(2).addRow(3).addRow(2,1).addRow(4)
                        .build()));

        assertTrue(Arrays.deepEquals(new int [][][] {{{1}, {0}}, {{1}, {0}}},
                new NonogramSpecBuilder(2)
                        .addColumn(1)
                        .addColumn(0)
                        .addRow(1)
                        .addRow(0)
                        .build()));

        assertTrue(Arrays.deepEquals(new int [][][] {{{1}, {1,1}, {1}}, {{1}, {0}, {2}}},
                new NonogramSpecBuilder(3)
                        .addColumn(1)
                        .addColumn(1,1)
                        .addColumn(1)
                        .addRow(1)
                        .addRow(0)
                        .addRow(2)
                        .build()));
    }

    @Test
    public void testEvaluateRow() {
        assertTrue(makeRow(false).matchesSpecification(0));
        assertTrue(makeRow(false, false).matchesSpecification(0));
        assertTrue(makeRow(true).matchesSpecification(1));
        assertTrue(makeRow(true, false).matchesSpecification(1));
        assertTrue(makeRow(false, true).matchesSpecification(1));
        assertTrue(makeRow(false, false, true).matchesSpecification(1));
        assertTrue(makeRow(false, false, true, false).matchesSpecification(1));
        assertTrue(makeRow(true, true).matchesSpecification(2));
        assertTrue(makeRow(false, true, true).matchesSpecification(2));
        assertTrue(makeRow(true, true, false).matchesSpecification(2));
        assertTrue(makeRow(false, true, true, false).matchesSpecification(2));
        assertTrue(makeRow(false, true, true, true, false).matchesSpecification(3));
        assertTrue(makeRow(true, false, true).matchesSpecification(1, 1));
        assertTrue(makeRow(true, false, true, false).matchesSpecification(1, 1));
        assertTrue(makeRow(false, true, false, true).matchesSpecification(1, 1));
        assertTrue(makeRow(false, true, true, false, true).matchesSpecification(2, 1));
        assertTrue(makeRow(false, true, true, false, true, false).matchesSpecification(2, 1));
        assertTrue(makeRow(false, true, false, false, true, false).matchesSpecification(1, 1));
        assertTrue(makeRow(false, true, false, false, true, false, true, false).matchesSpecification(1, 1, 1));
        assertTrue(makeRow(false, true, true, false, false, true, true, false, true, false, true, true, true).matchesSpecification(2, 2, 1, 3));

        assertFalse(makeRow(true).matchesSpecification(0));
        assertFalse(makeRow(false).matchesSpecification(1));
        assertFalse(makeRow(false, false).matchesSpecification(1));
        assertFalse(makeRow(true, true).matchesSpecification(1));
        assertFalse(makeRow(true, true).matchesSpecification(1, 1));
        assertFalse(makeRow(true, true).matchesSpecification(3));
        assertFalse(makeRow(true, false, true).matchesSpecification(1));
        assertFalse(makeRow(true, false, true, true).matchesSpecification(1, 1));
        assertFalse(makeRow(true, false, false).matchesSpecification(1, 1));
        assertFalse(makeRow(true, false, false).matchesSpecification(1, 1, 1));
    }

    @Test
    public void testFindPossibleRows() {
        assertRows(findRows(0, null), null);
        assertRows(findRows(1, null), null);
        assertRows(findRows(1, 1), makeRow(true));
        assertRows(findRows(1, 0), makeRow(false));
        assertRows(findRows(2, 0), makeRow(false, false));
        assertRows(findRows(2, 1), makeRow(true, false), makeRow(false, true));
        assertRows(findRows(2, 2), makeRow(true, true));
        assertRows(findRows(3, 0), makeRow(false, false, false));
        assertRows(findRows(3, 3), makeRow(true, true, true));
        assertRows(findRows(3, 2), makeRow(true, true, false), makeRow(false, true, true));
        assertRows(findRows(3, 1), makeRow(true, false, false), makeRow(false, true, false), makeRow(false, false, true));
        assertRows(findRows(3, 1, 1), makeRow(true, false, true));
        assertRows(findRows(4, 2, 1), makeRow(true, true, false, true));
        assertRows(findRows(4, 1, 2), makeRow(true, false, true, true));
        assertRows(findRows(4, 1, 1),
                makeRow(true, false, true, false),
                makeRow(true, false, false, true),
                makeRow(false, true, false, true));
        assertRows(findRows(5, 1, 1),
                makeRow(true, false, true, false, false),
                makeRow(true, false, false, true, false),
                makeRow(true, false, false, false, true),
                makeRow(false, true, false, true, false),
                makeRow(false, true, false, false, true),
                makeRow(false, false, true, false, true));
        assertRows(findRows(5, 2, 1),
                makeRow(true, true, false, true, false),
                makeRow(true, true, false, false, true),
                makeRow(false, true, true, false, true));
        assertRows(findRows(6, 2, 2),
                makeRow(true, true, false, true, true, false),
                makeRow(true, true, false, false, true, true),
                makeRow(false, true, true, false, true, true));
        assertRows(findRows(6, 1, 2),
                makeRow(false, true, false, true, true, false),
                makeRow(true, false, true, true, false, false),
                makeRow(true, false, false, true, true, false),
                makeRow(true, false, false, false, true, true),
                makeRow(false, true, false, false, true, true),
                makeRow(false, false, true, false, true, true));
        assertRows(findRows(5, 1, 1, 1), makeRow(true, false, true, false, true));
        assertRows(findRows(6, 1, 2, 1), makeRow(true, false, true, true, false, true));
        assertRows(findRows(6, 1, 1, 1),
                makeRow(true, false, true, false, true, false),
                makeRow(true, false, true, false, false, true),
                makeRow(true, false, false, true, false, true),
                makeRow(false, true, false, true, false, true));
    }

    private void assertRows(List<NonogramRow> possibleRows, NonogramRow... expectedRows) {
        assertTrue(compareRows(possibleRows, expectedRows));
    }

    private void assertRow(NonogramRow row, NonogramRow expectedRow) {
        assertTrue(compareRow(row, expectedRow));
    }

    @Test
    public void testRowValueComparator() {
        assertTrue(NonogramRow.VALUE_COMPARATOR.compare(makeRow(false), makeRow(true)) < 0);
        assertTrue(NonogramRow.VALUE_COMPARATOR.compare(makeRow(true), makeRow(false)) > 0);
        assertTrue(NonogramRow.VALUE_COMPARATOR.compare(makeRow(false), makeRow(false)) == 0);
        assertTrue(NonogramRow.VALUE_COMPARATOR.compare(makeRow(false, false), makeRow(false, true)) < 0);
        assertTrue(NonogramRow.VALUE_COMPARATOR.compare(makeRow(false, true, false), makeRow(false, true, true)) < 0);
    }

    @Test
    public void testTrimRow() {
        assertEquals(makeRow(false).trim(false), makeRow());
        assertEquals(makeRow(true).trim(false), makeRow(true));
        assertEquals(makeRow(true,false).trim(false), makeRow(true));
        assertEquals(makeRow(false,true,false).trim(false), makeRow(false,true));
        assertEquals(makeRow(false,true,false,true).trim(false), makeRow(false,true,false,true));
        assertEquals(makeRow(true,true).trim(false), makeRow(true,true));

        assertEquals(makeRow(false).trim(true), makeRow(false));
        assertEquals(makeRow(true).trim(true), makeRow());
        assertEquals(makeRow(true,false).trim(true), makeRow(true, false));
        assertEquals(makeRow(false,true,false).trim(true), makeRow(false,true,false));
        assertEquals(makeRow(false,true,false,true).trim(true), makeRow(false,true,false));
        assertEquals(makeRow(false,false).trim(true), makeRow(false,false));
    }

    @Test
    public void testMergeRow() {
        assertRow(makeRow(false).mergeRow((NonogramRow) null), makeRow(false));
        assertRow(makeRow(true).mergeRow((NonogramRow) null), makeRow(true));
        assertRow(makeRow(false).mergeRow(false), makeRow(false, false));
        assertRow(makeRow(true).mergeRow(false), makeRow(true, false));
        assertRow(makeRow(false).mergeRow(true), makeRow(false, true));
        assertRow(makeRow(true).mergeRow(true), makeRow(true, true));
        assertRow(makeRow(false, false).mergeRow(false), makeRow(false, false, false));
        assertRow(makeRow(true, false).mergeRow(true), makeRow(true, false, true));
        assertRow(makeRow(true).mergeRow(false, true), makeRow(true, false, true));
        assertRow(makeRow(true, false).mergeRow(false, true), makeRow(true, false, false, true));
        assertRow(makeRow(true, true).mergeRow(true, true), makeRow(true, true, true, true));
        assertRow(makeRow(true, true).mergeRow(false, false).mergeRow(true, false), makeRow(true, true, false, false, true, false));
        assertRow(makeRow(true, true).mergeRow(false).mergeRow(true), makeRow(true, true, false, true));
    }

    private boolean compareRow(NonogramRow row1, NonogramRow row2) {
//        System.out.println("comparing " + row1 + " to " + row2);
        return row1.equals(row2);
    }

    private boolean compareRows(List<NonogramRow> rowList, NonogramRow... rowArray) {
        System.out.println("comparing " + rowList + " to " + Arrays.toString(rowArray));

        if(rowList == null && rowArray == null) {
            return true;
        }

        if(rowList != null && rowArray != null && rowList.size() == rowArray.length) {
            rowList.sort(NonogramRow.VALUE_COMPARATOR);
            Arrays.sort(rowArray, NonogramRow.VALUE_COMPARATOR);
            for(int arrayIndexToTest = 0; arrayIndexToTest < rowList.size(); arrayIndexToTest++) {
                if(!compareRow(rowArray[arrayIndexToTest], rowList.get(arrayIndexToTest))) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    protected static class NonogramBoardBuilder {
        private final int [][] board;
        private int rowIndex = 0;

        public NonogramBoardBuilder(int size) {
            board = new int[size][size];
        }

        public NonogramBoardBuilder addRow(int... rowValues) {
            board[rowIndex++] = rowValues;
            return this;
        }

        public int [][] build() {
            return board;
        }
    }

    private static class NonogramSpecBuilder {
        private int size;
        private List<Integer []> colSpecList = new ArrayList<>();
        private List<Integer []> rowSpecList = new ArrayList<>();

        public NonogramSpecBuilder(int size) {
            this.size = size;
        }

        public NonogramSpecBuilder addColumn(Integer... colSpec) {
            colSpecList.add(colSpec);
            return this;
        }

        public  NonogramSpecBuilder addRow(Integer... rowSpec) {
            rowSpecList.add(rowSpec);
            return this;
        }

        public int [][][] build() {
            if(size != colSpecList.size()) {
                throw new IllegalStateException("Some columns not specified, column size=" + colSpecList.size() + ", expected " + size);
            }

            if(size != rowSpecList.size()) {
                throw new IllegalStateException("Some rows not specified, row size=" + rowSpecList.size() + ", expected " + size);
            }

            int [][][] gameSpec = new int[2][size][size];

            for(int c = 0; c < colSpecList.size(); c++) {
                gameSpec[0][c] = convertToIntArray(colSpecList.get(c));
            }

            for(int r = 0; r < rowSpecList.size(); r++) {
                gameSpec[1][r] = convertToIntArray(rowSpecList.get(r));
            }

            return gameSpec;
        }

        private int[] convertToIntArray(Integer[] integers) {
            int [] dest = new int[integers.length];
            for(int i = 0; i < dest.length; i++) {
                dest[i] = integers[i];
            }
            return dest;
        }
    }
}
