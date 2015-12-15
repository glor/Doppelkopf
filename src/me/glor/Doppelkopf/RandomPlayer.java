package me.glor.Doppelkopf;

import java.util.ArrayList;
import java.util.Random;

public class RandomPlayer extends AbstractPlayer {
	Input in;
	int lastCard;
	Random rn = new Random();
	public RandomPlayer(TableViewer tv) {
		super(tv);
	}
	
	public int request() {
		ArrayList<Integer> cards = tv.getAllMoves();
		lastCard = cards.get(Math.abs(rn.nextInt())%cards.size());
		return lastCard;
	}

	public void confirm() {
		
	}

	public void update(int card) {
	}
	public void reset(int[] inv) {
		super.reset(inv);
	}
}
