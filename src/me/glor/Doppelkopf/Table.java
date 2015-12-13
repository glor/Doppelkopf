package me.glor.Doppelkopf;

import java.util.ArrayList;
import java.util.Random;

public class Table {
	private int turn = 0;
	//currently just in mind for herz-10
	int[] used = new int[24];
	Inventory[] inv = new Inventory[4];
	public Table() {
		for(int i=0; i<4; i++)
			inv[i] = new Inventory();
	}
	/**
	 * @return 0<=n<=3 , the current player.
	 */
	public int getPlayer() {
		return turn & 3;
	}
	public int getRound() {
		return turn >> 2;
	}
	public boolean forceCard(int card) {
		used[card]++;
		turn++;
		return true;
	}
	public boolean putCard(int player, int card) {
		if(!check(card))
			return false;
		used[card]++;
		inv[getPlayer()].remove(card);
		turn++;
		return true;
	}
	public static void genPerm(int[] o) {
		int tmp;
		int random;
		Random rn = new Random();
		for(int i=0; i<o.length; i++) {
			random = Math.abs(rn.nextInt())%o.length;
			tmp = o[i];
			o[i] = o[random];
			o[random] = tmp;
		}
	}

	public int[] generateDeck() {
		int[] deck = new int[48];
		for(int i=0; i<48; i++) {
			deck[i] = i/2;
		}
		genPerm(deck);
		return deck;
	}
	
	public void generateInventory() {
		int[] deck = generateDeck();
		for(int i=0; i<4; i++) {
			for(int j=0; j<12; j++) {
				inv[i].add(deck[i*12+j]);
			}
		}
	}
	
	public boolean isOver() {
		return turn>=48;
	}

	public ArrayList<Integer> getPossibleMoves() {
		ArrayList<Integer> possibleMoves = new ArrayList<Integer>();
		int player = getPlayer();
		for(int i=0; i<inv[player].count; i++) {
			if(check(inv[player].inv[i])) {
				possibleMoves.add(inv[player].inv[i]);
			}
		}
		return possibleMoves;
		
	}
	private boolean check(int card) {
		/*
		 * insert rules here
		 */
		if(!inv[getPlayer()].hasCard(card))
			return false;
		return true;
	}
}
