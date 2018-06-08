package java8_in_action.chapter7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Chapter7 {
    public static void main(String ... args) {
        ystem.out.println("chapter 7 main starting...");
        
        final String SENTENCE = " Nel   mezzo del cammin  di nostra  vita mi  ritrovai in una  selva oscura che la  dritta via era   smarrita "
        		+ " Nel   mezzo del cammin  di nostra  vita mi  ritrovai in una  selva oscura che la  dritta via era   smarrita "
        		+ " Nel   mezzo del cammin  di nostra  vita mi  ritrovai in una  selva oscura che la  dritta via era";
       
        System.out.println("iterative sentence words=" + countWordsIteratively(SENTENCE));
       
        // functional word counter using streams
        Stream<Character> charStream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
        WordCounter wc = charStream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        System.out.println("func sentence words=" + wc.getWordCount());
        
        WordCounterSpliterator wcs = new WordCounterSpliterator(SENTENCE);
        wc = StreamSupport.stream(wcs, true).reduce(new WordCounter(0,  true), WordCounter::accumulate, WordCounter::combine);
        System.out.println("spliterator sentence words=" + wc.getWordCount());

        long [] nums = LongStream.rangeClosed(1, 100).toArray();
        
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(nums);
        Long sum = pool.invoke(task);
        System.out.println("sum=" + sum + ", created=" + ForkJoinSumCalculator.getNumCreated());
                    
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
    
    /**
     * Class to use for maintaining state during reduction operation to count words functionally. 
     * @author tlanders
     */
    protected static class WordCounter {
    	protected final int wordCount;
    	protected final boolean lastSpace;
    	
    	public WordCounter(int cnt, boolean lst) {
    		this.wordCount = cnt;
    		this.lastSpace = lst;
    	}
    	
    	public WordCounter accumulate(Character c) {
    		if(Character.isWhitespace(c)) {
    			if(!lastSpace) {
    				return new WordCounter(wordCount, true);
    			}
    		} else {
    			if(lastSpace) {
    				return new WordCounter(wordCount + 1, false);
    			}
    		}
    		return this;
    	}
    	
    	public WordCounter combine(WordCounter wc) {
    		// don't care about last space status when summing the results
    		return new WordCounter(this.wordCount + wc.getWordCount(), this.lastSpace);
    	}
    	
    	public int getWordCount() {
    		return wordCount;
    	}
    }
}
