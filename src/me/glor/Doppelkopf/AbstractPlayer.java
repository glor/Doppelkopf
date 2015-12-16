package me.glor.Doppelkopf;

import me.glor.Doppelkopf.Core.TableViewer;

public abstract class AbstractPlayer implements Player {
	Inventory inv;
	TableViewer tv;
	@SuppressWarnings("unused")
	private AbstractPlayer() {
	}
	public AbstractPlayer(TableViewer tv) {
		this.tv = tv;
	}
	public int request() {
		return 0;
	}

	public void confirm() {
	}

	public void update(int card) {
	}
	public void reset(int[] inv) {
		this.inv = new Inventory(inv);
	}
}
