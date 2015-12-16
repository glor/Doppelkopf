package me.glor.Doppelkopf;

import me.glor.Doppelkopf.Core.TableViewer;

public class HumanPlayer extends AbstractPlayer {
	Input in;
	public HumanPlayer(TableViewer tv, Input in) {
		super(tv);
		this.in = in;
	}
	
	public int request() {
		return in.receiveCard();
	}

	public void confirm() {
	}

	public void update(int card) {
	}
	public void reset(TableViewer tv, int[] inv) {
		this.tv = tv;
		this.inv = new Inventory(inv);
	}
}
