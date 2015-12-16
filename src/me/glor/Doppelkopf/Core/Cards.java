package me.glor.Doppelkopf.Core;

import java.util.ArrayList;

import me.glor.Doppelkopf.Tui;

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
	
	private static int[] priority_default =		{1,	5,	9,	2,	3,	4,	0,	6,	10,	0,	13,	0,	0,	7,	11,	0,	0,	0,	0,	8,	12,	0,	0,	0	};
	private static int[] priority_herz =		{1,	5,	9,	2,	3,	4,	0,	6,	10,	0,	13,	0,	0,	7,	11,	0,	0,	0,	0,	8,	12,	0,	0,	0	};//not correct
	private static int[] priority_pik =			{1,	5,	9,	2,	3,	4,	0,	6,	10,	0,	13,	0,	0,	7,	11,	0,	0,	0,	0,	8,	12,	0,	0,	0	};//not correct
	private static int[] priority_kreuz =		{1,	5,	9,	2,	3,	4,	0,	6,	10,	0,	13,	0,	0,	7,	11,	0,	0,	0,	0,	8,	12,	0,	0,	0	};//not correct
	private static int[] priority_damen =		{0,	0,	1,	0,	0,	0,	0,	0,	2,	0,	0,	0,	0,	0,	3,	0,	0,	0,	0,	0,	4,	0,	0,	0	};
	private static int[] priority_buben =		{0,	1,	0,	0,	0,	0,	0,	2,	0,	0,	0,	0,	0,	3,	0,	0,	0,	0,	0,	4,	0,	0,	0,	0	};
	private static int[] priority_ass =			{0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0	};
	
	protected static void setMode(int mode) {
		int[] newarray;
		switch(mode) {
			case Constants.CARD_MODUS_SOLO_KARO:
				newarray = priority_default;
				break;
			case Constants.CARD_MODUS_SOLO_HERZ:
				newarray = priority_herz;
				break;
			case Constants.CARD_MODUS_SOLO_PIK:
				newarray = priority_pik;
				break;
			case Constants.CARD_MODUS_SOLO_KREUZ:
				newarray = priority_kreuz;
				break;
			case Constants.CARD_MODUS_SOLO_BUBEN:
				newarray = priority_buben;
				break;
			case Constants.CARD_MODUS_SOLO_DAMEN:
				newarray = priority_damen;
				break;
			case Constants.CARD_MODUS_SOLO_ASS:
				newarray = priority_ass;
				break;
		default:
			throw new RuntimeException("Wrong Game Modus");
		}
		System.arraycopy(newarray, 0, priority, 0, newarray.length);
	}
	
	public static int identify(String s) {
		if(!s.matches("[CHPK](9|B|D|K|10)"))
			return Constants.CARD_INVALID;
		int card = 0;
		switch(s.charAt(0)) {
			case 'C':
				card = 0;
				break;
			case 'H':
				card = 1;
				break;
			case 'P':
				card = 2;
				break;
			case 'K':
				card = 3;
				break;
		}
		switch(s.charAt(0)) {
			case '9':
				card += 0;
				break;
			case 'B':
				card += 1;
				break;
			case 'D':
				card += 2;
				break;
			case 'K':
				card += 3;
				break;
			case '1':
				card += 4;
				break;
		}
		return card;
	}
	
}
