package me.glor.Doppelkopf;

/**
 * Basic Integer Stack on static array. Because using a double linked list sucks.
 * @author glor
 */
public class IntStack {
	private final int size;
	private int array[];
	private int index = -1;
	@SuppressWarnings("unused")
	private IntStack() {
		size = 0;
	}
	public IntStack(int size) {
		this.size = size;
		array = new int[size];
	}
	public void push(int i) throws Exception {
		if(index==size) {
			throw new Exception("Stack full");
		}
		array[index++] = i;
	}
	public int top() {
		return array[index];
	}
	public int pop() throws Exception {
		if(index<0) {
			throw new Exception("Stack empty");
		}
		return array[index++ - 1];
	}
}
