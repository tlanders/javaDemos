package misc.codewars.nonogram;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class NonogramTest {
    @Test
    public void testFindPossibleRows() {
        assertTrue(compareArrays(findPossibleRows(0, null), null));
        assertTrue(compareArrays(findPossibleRows(1, null), null));
        assertTrue(compareArrays(findPossibleRows(1, 1), new boolean[] {true}));
        assertTrue(compareArrays(findPossibleRows(1, 0), new boolean[] {false}));
    }

    public boolean [] findPossibleRows(int nonogramSize, int... rowSpec) {
        if(rowSpec == null || rowSpec.length <= 0) {
            return null;
        } else {
            boolean [] row = new boolean[nonogramSize];
            for(int i = 0; i < nonogramSize; i++) {
                row[i] = rowSpec[0] > 0;
            }
            return row;
        }
    }

    public boolean compareArrays(boolean [] arr1, boolean [] arr2) {
        if(arr1 == null && arr2 == null) {
            return true;
        }

        if(arr1 != null && arr2 != null && arr1.length == arr2.length) {
            for(int i = 0; i < arr1.length; i++) {
                if(arr1[i] != arr2[i]) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }
}
