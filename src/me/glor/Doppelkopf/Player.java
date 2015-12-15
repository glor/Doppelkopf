package me.glor.Doppelkopf;

public interface Player {
	public int request();
	public void confirm();
	public void update(int card);
	public void reset(int[] inv);
}
