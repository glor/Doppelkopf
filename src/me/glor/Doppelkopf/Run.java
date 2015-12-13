package me.glor.Doppelkopf;

import java.util.ArrayList;

public class Run {
	private static int debugLevel = 0;
	public static void debug(int level, String s) {
		if(level<debugLevel) {
			System.out.println(s);
		}
	}
	public static void main(String[] args) {
		//DEFAULT VALUES
		Player[] players = new Player[4];
		Tui tui = new Tui();
		Table table = new Table();
		table.generateInventory();
		for (int i = 0; i < 4; i++) {
			players[i] = new RandomPlayer(i, table.inv[i].copy());
		}
		while(!table.isOver()) {
			int currentPlayer = table.getPlayer();
			int card;
			debug(3, "Player " + currentPlayer+1 + "s Inventory is " + table.inv[currentPlayer].toString());
			//debug(2, "Player " + currentPlayer+1 + " has these options: ")
			ArrayList<Integer> field = table.getPossibleMoves();
			field = table.getPossibleMoves();
			
			do {
				card = players[currentPlayer].request();
			} while(!table.putCard(currentPlayer, card));
			debug(3, "Player " + currentPlayer+1 + "played " + Cards.toString(card));
			tui.sendCard(currentPlayer, card);
			for(int i=0; i<4; i++) {
				if(i==currentPlayer) {
					players[i].confirm();
				} else {
					players[i].update(card);
				}
			}
		}
	}

}
