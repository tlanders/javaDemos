package misc.codewars.nonogram;

import java.util.Arrays;

public class NonogramRow {
    protected boolean [] row;

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
