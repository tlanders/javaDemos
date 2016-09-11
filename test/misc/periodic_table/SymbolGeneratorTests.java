package misc.periodic_table;

import static org.junit.Assert.assertFalse;

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
}
