package misc.periodic_table;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Created by tlanders on 9/11/2016.
 */
public class SymbolGeneratorTests {
    @Test
    public void testValidCheck() {
        SymbolGenerator sgen = new SymbolGenerator();
        assertFalse("two char test failed", sgen.isValidSymbol("Element", "E"));
        assertFalse("two char test failed", sgen.isValidSymbol("Element", "Ele"));
    }

    @Test
    public void validSymbolTestsForPart1() {
        SymbolGenerator sgen = new SymbolGenerator();

        assertFalse(sgen.isValidSymbol("Spenglerium", "E")); // -> false
        assertTrue(sgen.isValidSymbol("Spenglerium", "Ee")); // -> true
        assertTrue(sgen.isValidSymbol("Zeddemorium", "Zr")); // -> true
        assertFalse(sgen.isValidSymbol("Zeddemorium", "Xr")); // -> false
        assertTrue(sgen.isValidSymbol("Venkmine", "Kn")); // -> true
        assertFalse(sgen.isValidSymbol("Stantzon", "Zt")); // -> false
        assertFalse(sgen.isValidSymbol("Melintzum", "Nn")); // -> false
        assertFalse(sgen.isValidSymbol("Tullium", "Ty")); // -> false
        assertFalse(sgen.isValidSymbol("Tullium", "Tum")); // -> false
        assertFalse(sgen.isValidSymbol("Tullium", "Mm")); // -> false
    }
}
