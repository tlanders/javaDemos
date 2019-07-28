package misc.codewars.nonogram;

import java.util.*;

public class NonogramRow {
    public static final Comparator<? super NonogramRow> VALUE_COMPARATOR = Comparator.comparing(NonogramRow::getValue);

    private Integer getValue() {
        int val = 0;
        for(int i = 0; i < row.length; i++) {
            if(row[i]) {
                val += Math.pow(2, i);
            }
        }
//        System.out.println(this + " - val=" + val);
        return val;
    }

    protected boolean [] row;

    public NonogramRow mergeRow(NonogramRow rowToAppend) {
        if(isEmptyRow(rowToAppend)) {
            return new NonogramRow(this.row);
        }

        NonogramRow row = new NonogramRow(this.size() + rowToAppend.size());
        row.setRow(0, this.row);
        row.setRow(this.row.length, rowToAppend.row);
        return row;
    }

    private boolean isEmptyRow(NonogramRow rowToAppend) {
        return rowToAppend == null || rowToAppend.size() <= 0;
    }

    public NonogramRow mergeRow(boolean... rowValues) {
        return mergeRow(new NonogramRow(rowValues));
    }

    private void setRow(int startIndex, boolean [] rowValues) {
        for(int i = startIndex; i < rowValues.length + startIndex; i++) {
            row[i] = rowValues[i - startIndex];
        }
    }

    public int size() {
        return row.length;
    }

    public static List<NonogramRow> findRows(int nonogramSize, int... runLengths) {
        if(runLengths == null || runLengths.length <= 0) {
            return null;
        } else if(runLengths[0] == 0) {
            List<NonogramRow> rows = new ArrayList<>();
            rows.add(makeRow(nonogramSize));
            return rows;
        } else {
            List<NonogramRow> prefixRows = findPrefixRows(nonogramSize, getOccupiedSpots(runLengths), runLengths[0]);

            if(hasMultipleRunRows(runLengths)) {
                return findMultipleRunRows(nonogramSize, prefixRows, runLengths);
            } else {
                return prefixRows;
            }
        }
    }

    private static List<NonogramRow> findMultipleRunRows(int nonogramSize, List<NonogramRow> prefixRows, int[] runLengths) {
        List<NonogramRow> rows = new ArrayList<>();
        for(NonogramRow prefixRow : prefixRows) {
            addSuffixRows(nonogramSize, runLengths, rows, prefixRow);
        }
        return rows;
    }

    private static void addSuffixRows(int nonogramSize, int[] runLengths, List<NonogramRow> rows, NonogramRow prefixRow) {
        NonogramRow trimmedRow = prefixRow.trim(false);
        List<NonogramRow> suffixRows = new ArrayList<>();
        suffixRows.addAll(findRows(nonogramSize - trimmedRow.size() - 1, Arrays.copyOfRange(runLengths, 1, runLengths.length)));
        for (NonogramRow suffixRow : suffixRows) {
            rows.add(trimmedRow.mergeRow(false).mergeRow(suffixRow));
        }
    }

    private static boolean hasMultipleRunRows(int[] runLengths) {
        return runLengths.length > 1;
    }

    private static int getOccupiedSpots(int[] runLengths) {
        int spots = 0;
        if(hasMultipleRunRows(runLengths)) {
            for (int i = 1; i < runLengths.length; i++) {
                spots += runLengths[i] + 1;
            }
        }
        System.out.println("spots=" + spots);
        return spots;
    }

    private static List<NonogramRow> findPrefixRows(int nonogramSize, int occupiedSpots, int runLength) {
        List<NonogramRow> prefixRows = new ArrayList<>();
        for(int i = 0; i < nonogramSize - (runLength - 1) - occupiedSpots; i++) {
            boolean[] row = new boolean[nonogramSize - occupiedSpots];
            for (int rowIndex = i; rowIndex < runLength + i; rowIndex++) {
                row[rowIndex] = true;
            }
            prefixRows.add(makeRow(row));
        }
        return prefixRows;
    }

    public NonogramRow trim(boolean valToTrim) {
        for(int i = row.length - 1; i >= 0; i--) {
            if(row[i] != valToTrim) {
                return new NonogramRow(Arrays.copyOf(row, i + 1));
            }
        }

        return new NonogramRow();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NonogramRow that = (NonogramRow) o;
        return Arrays.equals(row, that.row);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(row);
    }

    public static NonogramRow makeRow(boolean... rowValues) {
        return new NonogramRow(rowValues);
    }

    public static NonogramRow makeRow(int width) {
        return new NonogramRow(width);
    }

    private NonogramRow(int rowWidth) {
        row = new boolean[rowWidth];
    }

    private NonogramRow(boolean... rowValues) {
        this(rowValues.length);

        for(int i = 0; i < rowValues.length; i++) {
            row[i] = rowValues[i];
        }
    }

    @Override
    public String toString() {
        return "NonogramRow{" +
                Arrays.toString(row) +
                '}';
    }
}
