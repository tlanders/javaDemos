package effectivejava.chapter5;

public class Stack {
	private Object [] items;
	private static final int DEFAULT_CAPACITY = 16;
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
		Object item = items[--index];
		items[index] = null;
		return item;
	}
	
	public void push(Object obj) {
		items[index++] = obj;
	}
}
