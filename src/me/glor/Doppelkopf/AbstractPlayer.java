package me.glor.Doppelkopf;

public abstract class AbstractPlayer implements Player {
	protected int nr;
	@SuppressWarnings("unused")
	private AbstractPlayer() {
	}
	public AbstractPlayer(int nr, Inventory inv) {
		table = new Table();
		this.nr = nr;
		table.inv[nr] = inv.copy();
	}
	Table table;
	public int request() {
		return 0;
	}

	public void confirm() {
		
	}

	public void update(int card) {
		
	}
	public void reset(int nr, Inventory inv) {
		table = new Table();
		this.nr = nr;
		table.inv[nr] = inv.copy();
	}
}
