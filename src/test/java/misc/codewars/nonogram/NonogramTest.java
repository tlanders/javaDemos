package misc.codewars.nonogram;

import org.junit.Test;

import java.util.*;

import static misc.codewars.nonogram.NonogramRow.makeRow;
import static org.junit.Assert.assertTrue;

public class NonogramTest {
    @Test
    public void testFindPossibleRows() {
        assertTrue(compareRows(findRows(0, null), null));
        assertTrue(compareRows(findRows(1, null), null));
        assertTrue(compareRows(findRows(1, 1), makeRow(true)));
        assertTrue(compareRows(findRows(1, 0), makeRow(false)));
        assertTrue(compareRows(findRows(2, 0), makeRow(false, false)));
        assertTrue(compareRows(findRows(2, 1),
                makeRow(true, false), makeRow(false, true)));
        assertTrue(compareRows(findRows(2, 2), makeRow(true, true)));
        assertTrue(compareRows(findRows(3, 0), makeRow(false, false, false)));
        assertTrue(compareRows(findRows(3, 3), makeRow(true, true, true)));
        assertTrue(compareRows(findRows(3, 2),
                makeRow(true, true, false), makeRow(false, true, true)));
        assertTrue(compareRows(findRows(3, 1),
                makeRow(true, false, false), makeRow(false, true, false), makeRow(false, false, true)));
    }

    public List<NonogramRow> findRows(int nonogramSize, int... runLengths) {
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

    public boolean compareRows(List<NonogramRow> rowList, NonogramRow... rowArray) {
        System.out.println("comparing " + rowList + " to " + Arrays.toString(rowArray));

        if(rowList == null && rowArray == null) {
            return true;
        }

        if(rowList != null && rowArray != null && rowList.size() == rowArray.length) {
            for(int arrayIndexToTest = 0; arrayIndexToTest < rowList.size(); arrayIndexToTest++) {
                if(!rowArray[arrayIndexToTest].equals(rowList.get(arrayIndexToTest))) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }
}
