package me.glor.Doppelkopf;

import java.util.ArrayList;

public class TableViewer {
	private Table table;
	private int player;
	private TableViewer() {
	}
	public TableViewer(Table table, int player) {
		this.table = table;
		this.player = player;
	}
	public boolean check(int card) {
		return table.check(player, card);
	}
	public ArrayList<Integer> getAllMoves() {
		return table.getAllMoves(player);
	}
}
