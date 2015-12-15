package me.glor.Doppelkopf;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
/**
 * provides core functionality of a playing table.
 * @author glor
 */
public class Table {
	int n;
	// needs to be filled with constants after gen deck
	int[] team;
	int[] score;
	Inventory[] inv;
	int[] used;
	Stack<ArrayList<Integer>> history = new Stack<ArrayList<Integer>>();
	public Table() {
		reset();
	}

	private static int[] generateDeck() {
		int[] deck = new int[48];
		for(int i=0; i<48; i++) {
			deck[i] = i/2;
		}
		//shuffle
		int tmp;
		int random;
		Random rn = new Random();
		for(int i=0; i<deck.length; i++) {
			random = Math.abs(rn.nextInt())%deck.length;
			tmp = deck[i];
			deck[i] = deck[random];
			deck[random] = tmp;
		}
		return deck;
	}
	
	public void dealOut() {
		int[] deck = generateDeck();
		inv = new Inventory[4];
		for(int i=0; i<4; i++) {
			inv[i] = new Inventory();
			for(int j=0; j<12; j++) {
				inv[i].add(deck[i*12+j]);
			}
		}
	}
	
	/**
	 * gets Score of Re party after the game
	 * @return
	 */
	private int getScore(int party) {
		return score[party];
	}
	
	/**
	 * 
	 * @param request
	 * @return if player is to be asked again
	 */
	private boolean eval(int currentPlayer, int card) {
		//int currentPlayer = (begin+i)%4;
		if(!check(currentPlayer, card))
			return false;
		
		//check fertig
		if(history.peek().add(card));
		used[card]++;
		inv[currentPlayer].remove(card);
		return true;
	}
	
	public boolean check(int currentPlayer, int card) {
		if(!inv[currentPlayer].hasCard(card))
			return false;
		return true;
	}

	public int game(Player[] players, int begin) {
		//reset everything
		reset();
		//init Inventorys
		dealOut();
		//reset players
		for(int i=0; i<4; i++) {
			players[i].reset(inv[i].getArray());
		}
		
		//normal game: teams:
		for(int i=0; i<4; i++) {
			if(inv[i].hasCard(20)) {
				team[i] = Constants.TEAM_RE;
			} else {
				team[i] = Constants.TEAM_KONTRA;
			}
		}
		
		//game ends if all cards were layed out
		while(inv[0].getSize()>0) {
			//let the game begin
			for(int i=0; i<4; i++) {
				int currentPlayer = (begin+i)%players.length;
				//pseudocards -> re-request
				while(!eval(currentPlayer, players[currentPlayer].request()));
				Run.debug(1, "current Player " + currentPlayer + " layed out " + Cards.toString(history.peek().get(history.peek().size()-1)));
			}
			//winner of current round is to begin the next one
			begin = evalRound(begin);
			Run.debug(1, "Team RE: " + score[Constants.TEAM_RE] + "\t" + "Team KONTRA: " + score[Constants.TEAM_KONTRA]);
		}
		return getScore(0);
	}
	
	private int evalRound(int begin) {
		if(history.peek().size()!=4)
			throw new RuntimeException();
		//gucken, welcher spiele die punkte bekommt
		int max = Cards.max(begin, history.peek());
		//punkte dem team geben
		for (int i = 0; i < 4; i++) {
			score[team[max]] += history.peek().get(i);
		}
		//neue runde
		history.push(new ArrayList<Integer>());
		return max;
	}

	public void reset() {
		score = new int[]{0,0};
		// needs to be filled with constants after gen deck
		team = new int[]{0, 0, 0, 0};
		history = new Stack<ArrayList<Integer>>();
		history.add(new ArrayList<Integer>());
		used = new int[24];
	}

	public ArrayList<Integer> getAllMoves(int player) {
		ArrayList<Integer> allMoves = new ArrayList<Integer>();
		for(int card:inv[player]) {
			if(check(player, card))
				allMoves.add(card);
		}
		return allMoves;
	}
}
