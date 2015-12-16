package me.glor.Doppelkopf;

import java.util.HashMap;
import java.util.Scanner;

import me.glor.Doppelkopf.Core.Cards;
import me.glor.Doppelkopf.Core.Constants;

public class Tui implements Input, Output {
	public static boolean colored = false;
	String currentRound;
	public Tui() {
	}
	Scanner sc = new Scanner(System.in);
	public void sendCard(int player, int card) {
		if(player == 0)
			currentRound = "";
		//System.out.println("Player " + player + " layed out card " + Cards.getPoints(card) + " in color " + Cards.getColors(card));
		currentRound += Cards.toString(card) + " ";
		System.out.println(currentRound);
	}
	public void message(String msg) {
		System.out.println(msg);
	}
	public int receiveCard() {
		int card;
		do {
			if(!sc.hasNextLine()) {
				throw new RuntimeException("Unexpected end to input stream.");
			}
			card = Cards.identify(sc.nextLine());
		} while (card == Constants.CARD_INVALID);
		return card;
	}

}
