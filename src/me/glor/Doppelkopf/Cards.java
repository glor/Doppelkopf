package me.glor.Doppelkopf;

import java.util.ArrayList;

/**
 * Cards are represented as integers.
 * 
 * @author glor
 *
 */
public abstract class Cards {
	
	private static int[] colors = 	{0,	0,	0,	0,	0,	0,	1,	1,	1,	1,	1,	1,	2,	2,	2,	2,	2,	2,	3,	3,	3,	3,	3,	3	};
	private static int[] points = 	{0,	2,	3,	4,	10,	11,	0,	2,	3,	4,	10,	11,	0,	2,	3,	4,	10,	11,	0,	2,	3,	4,	10,	11	};
	private static int[] priority =	{1,	5,	9,	2,	3,	4,	0,	6,	10,	0,	13,	0,	0,	7,	11,	0,	0,	0,	0,	8,	12,	0,	0,	0	};
	
	public static void printAllInfo() {
		System.out.println("Card value priority");
		for(int i=0; i<24; i++) {
			System.out.printf("%4s %4d %4d\n", toString(i), Cards.getPoints(i), Cards.getPriority(i));
		}
	}
	/**
	 * priority ist 0 für Fehl und für Trupf aufsteigend
	 * @return the priority
	 */
	public static int getPriority(int card) {
		return priority[card];
	}
	/**
	 * gibt die Farbe zu einer bestimmten Kartennummer. Karo = 0, Herz = 1, Pik = 2, Kreuz = 3
	 * @param card Kartennummer
	 * @return
	 */
	static int getColors(int card) {
		return colors[card];
	}
	static int getPoints(int card) {
		return points[card];
	}
	private static final String ANSI_RESET = "\u001B[0m";
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_BLACK = "\u001B[30m";
	public static String toString(int card) {
		String s = "";
		if(Tui.colored) {
			if(colors[card]<2) {
				s+=ANSI_RED;
			} else {
				s+=ANSI_BLACK;
			}
			
			
		}
		switch(colors[card]) {
		case 0:
			s+="♦";
			break;
		case 1:
			s+="♥";
			break;
		case 2:
			s+="♠";
			break;
		default:
			s+="♣";
		}
		switch(points[card]) {
		case 0:
			s+="9";
			break;
		case 2:
			s+="B";
			break;
		case 3:
			s+="D";
			break;
		case 4:
			s+="K";
			break;
		case 10:
			s+="10";
			break;
		case 11:
			s+="A";
			break;
		}
		if(Tui.colored)
			s+=ANSI_RESET;
		return s;
	}
	/**
	 * evaluates the cards of this round
	 * @param begin beginning player in that round
	 * @param a cards in that round
	 * @return winning player
	 */
	public static int max(int begin, ArrayList<Integer> a) {
		int max = begin;
		for(int i=begin+1; i<begin+a.size(); i++)
			if(smaller(a.get(max), a.get(i%a.size())))
				max = i%a.size();
		return max;
	}
	/**
	 * 
	 * @param a first card
	 * @param b second card
	 * @return false if a<=b, false if a>b
	 */
	public static boolean smaller(int a, int b) {
		if(getPriority(a)<getPriority(b))
			return false;
		if(getPriority(a)>getPriority(b))
			return true;
		if(getColors(a) < getColors(b))
			return false;
		if(getColors(a) > getColors(b))
			return true;
		if(getPoints(a) < getPoints(b))
			return false;
		return false;
	}
}
