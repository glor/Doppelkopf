package me.glor.Doppelkopf;

import java.util.ArrayList;

public class Run {
	private static int debugLevel = 0;
	public static void debug(int level, String s) {
		if(level>debugLevel) {
			System.out.println(s);
		}
	}
	public static void main(String[] args) {
		//INIT USER INTERFACE
		Player[] players = new Player[4];
		Tui tui = new Tui();
		Table table = new Table();
		for (int i = 0; i < 4; i++) {
			players[i] = new RandomPlayer(new TableViewer(table, i));
		}
		table.game(players, 0);
	}
}
