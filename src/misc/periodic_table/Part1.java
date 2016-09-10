package misc.periodic_table;

/**
 * Created by tlanders on 9/10/2016.
 */
public class Part1 {
    public static void main(String[] args) {
        System.out.println("Part1 starting...");
        SymbolGenerator sgen = new SymbolGenerator();

        // TODO - use junit to test these
        sgen.isValidSymbol("Spenglerium", "E"); // -> false
        sgen.isValidSymbol("Spenglerium", "Ee"); // -> true
        sgen.isValidSymbol("Zeddemorium", "Zr"); // -> true
        sgen.isValidSymbol("Zeddemorium", "Xr"); // -> false
        sgen.isValidSymbol("Venkmine", "Kn"); // -> true
        sgen.isValidSymbol("Stantzon", "Zt"); // -> false
        sgen.isValidSymbol("Melintzum", "Nn"); // -> false
        sgen.isValidSymbol("Tullium", "Ty"); // -> false
        sgen.isValidSymbol("Tullium", "Tum"); // -> false

        System.out.println("Part1 done.");
    }
}
