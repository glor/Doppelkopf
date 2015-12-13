package me.glor.Doppelkopf;
/**
 * Cards are represented as integers.
 * 
 * @author glor
 *
 */
public class Cards {
	public static final int CARD_NONE = -1;
	public static final int CARD_OK = -2;
	public static final int CARD_RE = -3;
	public static final int CARD_POOR = -4;
	public static final int CARD_SOLO = -5;
	
	private static int[] colors = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3};
	private static int[] points = {0, 10, 2, 3, 4, 11, 0, 10, 2, 3, 4, 11, 0, 10, 2, 3, 4, 11, 0, 10, 2, 3, 4, 11};
	private static int[] priority = {1,	3,	5,	9,	2,	4,	0,	13,	6,	10,	0,	0,	0,	0,	7,	11,	0,	0,	0,	0,	8,	12,	0,	0};
	
	public void printAllInfo() {
		for(int i=0; i<24; i++)
			System.out.print(Cards.getColors(i) + "\t");
		System.out.println();
		for(int i=0; i<24; i++)
			System.out.print(Cards.getPoints(i) + "\t");
		System.out.println();
		for(int i=0; i<24; i++)
			System.out.print(Cards.getPriority(i) + "\t");
		System.out.println();
	}
	/**
	 * priority ist 0 für Fehl und für Trupf aufsteigend
	 * @return the priority
	 */
	public static int getPriority(int id) {
		return priority[id];
	}
	/**
	 * gibt die Farbe zu einer bestimmten Kartennummer. Karo = 0, Herz = 1, Pik = 2, Kreuz = 3
	 * @param id Kartennummer
	 * @return
	 */
	static int getColors(int id) {
		return colors[id];
	}
	static int getPoints(int id) {
		return points[id];
	}
	private static final String ANSI_RESET = "\u001B[0m";
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_BLACK = "\u001B[30m";
	private final static boolean colored = true;
	public static String toString(int card) {
		String s = "";
		if(colored) {
			switch(colors[card]) {
			case 0:
				s+=ANSI_RED+"♦";
				break;
			case 1:
				s+=ANSI_RED+"♥";
				break;
			case 2:
				s+="♠";
				break;
			default:
				s+="♣";
			}
			
		} else {
			switch(colors[card]) {
			
			case 0:
				s+="Caro ";
				break;
			case 1:
				s+="Herz ";
				break;
			case 2:
				s+="Pik ";
				break;
			default:
				s+="Kreuz ";
			}
		}
		switch(points[card]) {
		case 0:
			s+="9";
			break;
		case 10:
			s+="10";
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
		case 11:
			s+="A";
			break;
		}
		s+=ANSI_RESET;
		return s;
	}
}
