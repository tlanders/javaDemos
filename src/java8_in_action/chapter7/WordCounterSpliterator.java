package java8_in_action.chapter7;

import java.util.Spliterator;
import java.util.function.Consumer;


public class WordCounterSpliterator implements Spliterator<Character> {
	protected final String string;
	protected int currentChar = 0;
	protected final static int SPLIT_THRESHOLD = 30;
	
	public WordCounterSpliterator(final String str) {
		System.out.println("WCS - len=" + str.length() + ", str=[" + str + "]" );
		string = str;
	}
	
	@Override
	public int characteristics() {
		return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
	}

	@Override
	public long estimateSize() {
		// assume average word length plus white spaces is 7
		return string.length() - currentChar;
	}

	@Override
	public boolean tryAdvance(Consumer<? super Character> action) {
		// process current char and return true if there are more left to be looked at
		action.accept(string.charAt(currentChar++));
		return currentChar < string.length();
	}

	@Override
	public Spliterator<Character> trySplit() {
		if(string.length() - currentChar > SPLIT_THRESHOLD) {
			// split - first find word boundary to do this at
			for(int splitPos = currentChar + (string.length() - currentChar) / 2; splitPos < string.length(); splitPos++) {
				if(Character.isWhitespace(string.charAt(splitPos))) {
					WordCounterSpliterator splitWCS = new WordCounterSpliterator(string.substring(currentChar, splitPos));
					currentChar = splitPos;
					System.out.println("  splitting at " + splitPos);
					return splitWCS;
				}
			}
		}
		System.out.println("  not splitting" );
		return null;
	}
}
