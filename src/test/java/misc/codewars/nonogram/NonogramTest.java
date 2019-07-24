package misc.codewars.nonogram;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertTrue;

public class NonogramTest {
    @Test
    public void testFindPossibleRows() {
        assertTrue(compareListsOfArrays(findPossibleRows(0, null), null));
        assertTrue(compareListsOfArrays(findPossibleRows(1, null), null));
        assertTrue(compareListsOfArrays(findPossibleRows(1, 1), new NonogramRow(true)));
        assertTrue(compareListsOfArrays(findPossibleRows(1, 0), new NonogramRow(false)));
        assertTrue(compareListsOfArrays(findPossibleRows(2, 2), new NonogramRow(true, true)));
        assertTrue(compareListsOfArrays(findPossibleRows(2, 0), new NonogramRow(false, false)));
//        assertTrue(compareListsOfArrays(findPossibleRows(2, 1),
//                new Boolean[] {true, false}, new Boolean[] {false, true}));
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

    public boolean compareListsOfArrays(List<NonogramRow> listOfArrays1, NonogramRow... arr2) {
        if(listOfArrays1 == null && arr2 == null) {
            return true;
        }

        if(listOfArrays1 != null && arr2 != null && listOfArrays1.size() == arr2.length) {
            for(int arrayIndexToTest = 0; arrayIndexToTest < listOfArrays1.size(); arrayIndexToTest++) {
                if(!arr2[arrayIndexToTest].equals(listOfArrays1.get(arrayIndexToTest))) {
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
