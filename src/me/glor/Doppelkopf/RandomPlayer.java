package me.glor.Doppelkopf;

import java.util.ArrayList;
import java.util.Random;

public class RandomPlayer extends AbstractPlayer {
	Input in;
	int lastCard;
	Random rn = new Random();
	public RandomPlayer(int nr, Inventory inv) {
		super(nr, inv);
	}
	
	public int request() {
		ArrayList<Integer> cards = table.getPossibleMoves();
		lastCard = cards.get(Math.abs(rn.nextInt())%cards.size());
		return lastCard;
	}

	public void confirm() {
		table.putCard(nr, lastCard);
		
	}

	public void update(int card) {
		table.forceCard(card);
	}
	public void reset(int nr, Inventory inv) {
		super.reset(nr, inv);
	}
}
