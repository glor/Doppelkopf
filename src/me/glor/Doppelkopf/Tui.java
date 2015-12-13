package me.glor.Doppelkopf;

import java.util.Scanner;

public class Tui implements Input, Output {
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
		return sc.nextInt();
	}

}
