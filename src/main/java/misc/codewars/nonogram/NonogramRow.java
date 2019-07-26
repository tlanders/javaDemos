package misc.codewars.nonogram;

import java.util.*;

public class NonogramRow {
    protected boolean [] row;

    public NonogramRow mergeRow(NonogramRow rowToAppend) {
        if(rowToAppend == null || rowToAppend.size() <= 0) {
            return new NonogramRow(this.row);
        }

        NonogramRow row = new NonogramRow(this.size() + rowToAppend.size());
        row.setRow(0, this.row);
        row.setRow(this.row.length, rowToAppend.row);
        return row;
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
            List<NonogramRow> rows = new ArrayList<>();
            int occupiedSpots = 0;
            if(runLengths.length > 1) {
                occupiedSpots = runLengths[1] + 1;
            }
            for(int i = 0; i < nonogramSize - runLengths[0] + 1 - occupiedSpots; i++) {
                boolean [] row = new boolean[nonogramSize - occupiedSpots];
                for(int rowIndex = i; rowIndex < runLengths[0] + i; rowIndex++) {
                    row[rowIndex] = true;
                }

                if(runLengths.length > 1) {
                    List<NonogramRow> suffixRows = new ArrayList<>();
                    suffixRows.addAll(findRows(occupiedSpots - 1, runLengths[1]));
                    for (NonogramRow suffixRow : suffixRows) {
                        rows.add(makeRow(row).mergeRow(false).mergeRow(suffixRow));
                    }
                } else {
                    rows.add(makeRow(row));
                }
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
