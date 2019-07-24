package misc.codewars.nonogram;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertTrue;

public class NonogramTest {
    @Test
    public void testFindPossibleRows() {
        assertTrue(compareNonogramRows(findPossibleRows(0, null), null));
        assertTrue(compareNonogramRows(findPossibleRows(1, null), null));
        assertTrue(compareNonogramRows(findPossibleRows(1, 1), new NonogramRow(true)));
        assertTrue(compareNonogramRows(findPossibleRows(1, 0), new NonogramRow(false)));
        assertTrue(compareNonogramRows(findPossibleRows(2, 2), new NonogramRow(true, true)));
        assertTrue(compareNonogramRows(findPossibleRows(2, 0), new NonogramRow(false, false)));
//        assertTrue(compareNonogramRows(findPossibleRows(2, 1),
//                new NonogramRow(true, false), new NonogramRow(false, true)));
    }

    public List<NonogramRow> findPossibleRows(int nonogramSize, int... rowSpec) {
        if(rowSpec == null || rowSpec.length <= 0) {
            return null;
        } else {
            List<NonogramRow> rows = new ArrayList<>();
            boolean [] row = new boolean[nonogramSize];
            for(int i = 0; i < nonogramSize; i++) {
                row[i] = rowSpec[0] > 0;
            }
            rows.add(new NonogramRow(row));
            return rows;
        }
    }

    public boolean compareNonogramRows(List<NonogramRow> rowList, NonogramRow... rowArray) {
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

//    private boolean compareArray(Boolean[] arr1, Boolean[] arr2) {
//        for(int i = 0; i < arr1.length; i++) {
//            if (arr1[i] != arr2[i]) {
//                return false;
//            }
//        }
//        return true;
//    }
}
