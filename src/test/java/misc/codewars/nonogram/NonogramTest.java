package misc.codewars.nonogram;

import org.junit.Test;

import java.util.*;

import static misc.codewars.nonogram.NonogramRow.findRows;
import static misc.codewars.nonogram.NonogramRow.makeRow;
import static org.junit.Assert.assertTrue;

public class NonogramTest {
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
    }

    private void assertRows(List<NonogramRow> possibleRows, NonogramRow... expectedRows) {
        assertTrue(compareRows(possibleRows, expectedRows));
    }

    private void assertRow(NonogramRow row, NonogramRow expectedRow) {
        assertTrue(compareRow(row, expectedRow));
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
        System.out.println("comparing " + row1 + " to " + row2);
        return row1.equals(row2);
    }

    private boolean compareRows(List<NonogramRow> rowList, NonogramRow... rowArray) {
        System.out.println("comparing " + rowList + " to " + Arrays.toString(rowArray));

        if(rowList == null && rowArray == null) {
            return true;
        }

        if(rowList != null && rowArray != null && rowList.size() == rowArray.length) {
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
}
