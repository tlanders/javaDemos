package misc.codewars.nonogram;

import java.util.*;

public class NonogramRow {
    protected boolean [] row;

    public NonogramRow mergeRow(NonogramRow rowToAppend) {
        return this;
    }

    public static List<NonogramRow> findRows(int nonogramSize, int... runLengths) {
        if(runLengths == null || runLengths.length <= 0) {
            return null;
        } else if(runLengths[0] == 0) {
            List<NonogramRow> rows = new ArrayList<>();
            rows.add(makeRow(nonogramSize));
            return rows;
        } else {
            List<NonogramRow> rows = new ArrayList<>();
            for(int i = 0; i < nonogramSize - runLengths[0] + 1; i++) {
                boolean [] row = new boolean[nonogramSize];
                for(int rowIndex = i; rowIndex < runLengths[0] + i; rowIndex++) {
                    row[rowIndex] = true;
                }


                rows.add(makeRow(row));
            }
            return rows;
        }
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

//        System.out.println(this);
    }

    @Override
    public String toString() {
        return "NonogramRow{" +
                Arrays.toString(row) +
                '}';
    }
}
