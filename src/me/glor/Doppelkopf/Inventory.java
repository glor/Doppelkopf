package me.glor.Doppelkopf;

import java.util.Iterator;

import me.glor.Doppelkopf.Core.Cards;
import me.glor.Doppelkopf.Core.Constants;

public class Inventory implements Iterable<Integer> {
	
	public static class InventoryIterator implements Iterator<Integer> {
		Inventory inv;
		private InventoryIterator() {
		}
		int i;
		public InventoryIterator(Inventory inv) {
			this.inv = inv;
			i = 0;
		}
		public boolean hasNext() {
			return i<inv.getSize();
		}

		public Integer next() {
			i++;
			return inv.getElem(i - 1);
		}
		
	}
	
	public Inventory() {
	}
	/*
	 * unbox for more speed and safety
	 */
	public Inventory(int[] inv) {
		for(int i:inv)
			add(i);
	}
	
	private int size = 0;
	private int[] inv = new int[Constants.CARDS_PER_PLAYER];
	private int[] content = new int[48];
	
	protected int getElem(int i) {
		return inv[i];
	}
	/*
	 * O(1)
	 */
	public void add(int card) {
		if(size==Constants.CARDS_PER_PLAYER)
			throw new IndexOutOfBoundsException("Too many cards in your inventory.");
		inv[size] = card;
		content[card]++;
		size++;
	}
	/*
	 * O(n) = 1 to 12 iterations
	 * => O(1)
	 */
	public void remove(int card) {
		if(size == 0)
			throw new IndexOutOfBoundsException("Your inventory is empty. You cannot remove a card.");
		if(content[card] == 0)
			throw new RuntimeException("No such card in inventory.");
		int i = 0;
		while(i < size && inv[i] != card)
			i++;
		size--;
		inv[i] = inv[size];
		inv[size] = -1;
		content[card]--;
	}
	
	public int getSize() {
		return size;
	}
	/*
	 * O(1)
	 */
	public boolean hasCard(int card) {
		return content[card] > 0;
	}
	public String toString() {
		String s = "";
		for(int i=0; i<size-1; i++) {
			s += Cards.toString(inv[i]) + ", ";
		}
		s+=Cards.toString(inv[size-1]);
		return s;
	}

	public Inventory copy() {
		return new Inventory(inv);
	}
	public Iterator<Integer> iterator() {
		return new InventoryIterator(this);
	}
	public int[] getArray() {
		int[] copy = new int[size];
		for(int i=0; i<inv.length; i++)
			copy[i] = inv[i];
		return copy;
	}
}
