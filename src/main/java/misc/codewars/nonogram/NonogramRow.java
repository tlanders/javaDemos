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
        System.out.println(this + " - val=" + val);
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
            List<NonogramRow> rows = new ArrayList<>();
            int occupiedSpots = 0;
            if(runLengths.length > 1) {
                occupiedSpots = runLengths[1] + 1;
            }
            for(int i = 0; i < nonogramSize - (runLengths[0] - 1) - occupiedSpots; i++) {
                boolean [] row = new boolean[nonogramSize - occupiedSpots];
                for(int rowIndex = i; rowIndex < runLengths[0] + i; rowIndex++) {
                    row[rowIndex] = true;
                }

                if(runLengths.length > 1) {
                    NonogramRow prefixRow = makeRow(row).trim(false);
                    List<NonogramRow> suffixRows = new ArrayList<>();
                    suffixRows.addAll(findRows(nonogramSize - prefixRow.size() - 1, runLengths[1]));
                    for (NonogramRow suffixRow : suffixRows) {
                        rows.add(prefixRow.mergeRow(false).mergeRow(suffixRow));
                    }
                } else {
                    rows.add(makeRow(row));
                }
            }
            return rows;
        }
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
