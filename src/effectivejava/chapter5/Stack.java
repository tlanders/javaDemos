package effectivejava.chapter5;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Objects;

public class Stack {
	private Object [] items;
	private static final int DEFAULT_CAPACITY = 3;
	private int index = 0;
	
	public Stack(int initialCapacity) {
		if(initialCapacity > 0) {
			items = new Object[DEFAULT_CAPACITY];
		} else {
			items = new Object[initialCapacity];
		}
	}
	
	public Stack() {
		this(DEFAULT_CAPACITY);
	}
	
	public Object pop() {
		if(index <= 0) {
			throw new EmptyStackException();
		}
		
		Object item = items[--index];
		items[index] = null;
		return item;
	}
	
	public void push(Object obj) {
		Objects.requireNonNull(obj);
		
		ensureCapacity();
		items[index++] = obj;
	}
	
	private void ensureCapacity() {
		if(index >= items.length) {
			items = Arrays.copyOf(items, items.length * 2 + 1);
		}
	}
}
