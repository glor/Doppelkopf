package me.glor.Doppelkopf;

public class HumanPlayer extends AbstractPlayer {
	Input in;
	public HumanPlayer(int nr, Input in, Inventory inv) {
		super(nr, inv);
		this.in = in;
	}
	
	public int request() {
		return in.receiveCard();
	}

	public void confirm() {
	}

	public void update(int card) {
	}
	public void reset(int nr, Inventory inv) {
		super.reset(nr, inv);
	}
}
