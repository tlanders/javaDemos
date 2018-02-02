package java8_in_action.chapter7;

public class Chapter7 {
	
    public static void main(String ... args) {
        System.out.println("chapter 7 main starting...");
        final String SENTENCE = " Nel   mezzo del cammin  di nostra  vita mi  ritrovai in una  selva oscura ché la  dritta via era   smarrita ";
        System.out.println("iterative sentence words=" + countWordsIteratively(SENTENCE));
         	
        System.out.println("main exiting.");
    }
    
    protected static int countWordsIteratively(String str) {
    	int count = 0;
    	boolean foundWord = false;
    	
    	for(char c : str.toCharArray()) {
    		if(Character.isWhitespace(c)) {
    			if(foundWord) { foundWord = false; }
    		} else {
    			if(!foundWord) {
    				foundWord = true;
    				count++;
    			}
    		}
    	}
    	
    	return count;
    }
}
