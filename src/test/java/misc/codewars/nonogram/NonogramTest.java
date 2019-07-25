package misc.codewars.nonogram;

import org.junit.Test;

import java.util.*;

import static misc.codewars.nonogram.NonogramRow.findRows;
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
        //assertTrue(compareRows(findRows(3, 1, 1), makeRow(true, false, true)));
    }

    @Test
    public void testMergeRows() {
        assertTrue(compareRows(makeRow(false).mergeRow(null), makeRow(false)));
    }

    public boolean compareRows(NonogramRow row1, NonogramRow row2) {
        return row1.equals(row2);
    }

    public boolean compareRows(List<NonogramRow> rowList, NonogramRow... rowArray) {
        System.out.println("comparing " + rowList + " to " + Arrays.toString(rowArray));

        if(rowList == null && rowArray == null) {
            return true;
        }

        if(rowList != null && rowArray != null && rowList.size() == rowArray.length) {
            for(int arrayIndexToTest = 0; arrayIndexToTest < rowList.size(); arrayIndexToTest++) {
                if(!compareRows(rowArray[arrayIndexToTest], rowList.get(arrayIndexToTest))) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }
}
