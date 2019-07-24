package misc.codewars.nonogram;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class NonogramTest {
    @Test
    public void testFindPossibleRows() {
        assertTrue(compareListsOfArrays(findPossibleRows(0, null), null));
        assertTrue(compareListsOfArrays(findPossibleRows(1, null), null));
        assertTrue(compareListsOfArrays(findPossibleRows(1, 1), new Boolean[] {true}));
        assertTrue(compareListsOfArrays(findPossibleRows(1, 0), new Boolean[] {false}));
        //assertTrue(compareListsOfArrays(findPossibleRows(2, 1), new boolean[] {true, false}, new boolean[] {false, true}));
    }

    public List<Boolean[]> findPossibleRows(int nonogramSize, int... rowSpec) {
        if(rowSpec == null || rowSpec.length <= 0) {
            return null;
        } else {
            List<Boolean[]> rows = new ArrayList<>();
            Boolean [] row = new Boolean[nonogramSize];
            for(int i = 0; i < nonogramSize; i++) {
                row[i] = rowSpec[0] > 0;
            }
            rows.add(row);
            return rows;
        }
    }

    public boolean compareListsOfArrays(List<Boolean[]> listOfArrays1, Boolean[]... arr2) {
        if(listOfArrays1 == null && arr2 == null) {
            return true;
        }

        if(listOfArrays1 != null && arr2 != null && listOfArrays1.size() == arr2.length) {
            for(int arrayIndexToTest = 0; arrayIndexToTest < listOfArrays1.size(); arrayIndexToTest++) {
                if(!compareArray(arr2[arrayIndexToTest], listOfArrays1.get(arrayIndexToTest))) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    private boolean compareArray(Boolean[] arr1, Boolean[] arr2) {
        for(int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
}
