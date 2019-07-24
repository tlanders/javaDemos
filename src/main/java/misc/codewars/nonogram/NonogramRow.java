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

    public NonogramRow(boolean... rowValues) {
        row = new boolean[rowValues.length];

        for(int i = 0; i < rowValues.length; i++) {
            row[i] = rowValues[i];
        }


    }
}
