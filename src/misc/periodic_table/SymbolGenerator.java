package misc.periodic_table;

/**
 * Created by tlanders on 9/10/2016.
 */
public class SymbolGenerator {
    public boolean isValidSymbol(String elementName, String symbolToTest) {
        boolean result = false;
        System.out.println(elementName + " : " + symbolToTest);

        // rules:
        // 1. must have two chars
        // 2. both letters must be in element name
        // 3. letters must appear in same order as in element name
        // 4. letters can be same is appear at least twice in element name

        if(symbolToTest == null || symbolToTest.length() != 2) {
            System.out.println("  not valid - symbol must have two chars");
            return false;
        }

        String elementNameLower = elementName.toLowerCase();
        String symbolToTestLower = symbolToTest.toLowerCase();
        int firstIndex = elementNameLower.indexOf(symbolToTestLower.charAt(0));
        if(firstIndex < 0) {
            System.out.println("  not valid - first char=" + symbolToTest.charAt(0) + " not in element");
            return false;
        } else {
            int secondIndex = elementNameLower.indexOf(symbolToTestLower.charAt(1), firstIndex);
            if(secondIndex < 0) {
                System.out.println("  not valid - second char=" + symbolToTest.charAt(1) + " not in element");
                return false;
            }
        }

        System.out.println("  VALID - all rules followed");
        return true;
    }
}
