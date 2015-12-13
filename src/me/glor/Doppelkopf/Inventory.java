package me.glor.Doppelkopf;

public class Inventory {
	/**
	 * -1 in inv means that there is no card
	 */
	public int[] inv = new int[12];
	public int count = 0;
	private int linearSearch(int key) {
		for(int i=0; i<count; i++) {
			if(inv[i]==key) {
				return i;
			}
		}
		return -1;
	}
	private int binarySearch(int[] array, int max, int key) {
		int high = max;
		int low = 0;
		int mid;
		while(low <= high) {
			mid = (high+low)/2;
			if(array[mid]<key) {
				high = mid-1;
			} else if(array[mid]>key) {
				low = mid-1;
			} else {
				return mid;
			}
		}
		return -1;
	}

	private void insertionSort(int[] array, int lo, int hi) {
		int tmp;
		for(int i=lo; i<=hi; i++) {
			for(int j=i; j>lo && array[i]<array[j-1] ; j--) {
				tmp = array[j];
				array[j] = array[j-1];
				array[j-1] = tmp;
			}
		}
	}
	
	public void add(int card) {
		/*if(count==11) {
			insertionSort(inv, 0, inv.length);
		}*/
		if(count==12)
			throw new IndexOutOfBoundsException("Too many cards in your inventory.");
		inv[count] = card;
		count++;
	}
	public void remove(int card) {
		if(count == 0)
			throw new IndexOutOfBoundsException("Your inventory is empty. You cannot remove a card.");
		int i = linearSearch(card);
		count--;
		inv[i] = inv[count];
		inv[count] = -1;
		
	}
	/*
	 * could be replaced by additional bitmask
	 */
	public boolean hasCard(int card) {
		return linearSearch(card)!=-1;
		//return binarySearch(inv, 0, count-1) != -1;
	}
	public String toString() {
		String s = "";
		for(int i=0; i<count-1; i++) {
			s += Cards.toString(inv[i]) + ", ";
		}
		s+=Cards.toString(inv[count-1]);
		return s;
	}

	public Inventory copy() {
		Inventory newInv = new Inventory();
		for(int i=0; i<count; i++) {
			newInv.add(inv[i]);
		}
		return newInv;
	}
}
