package game;

import mixmeta4.Board;
import mixmeta4.Queen;
import player.*;

public class Run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Queen tempQueen = new Queen(false, false);
		
		System.out.println(tempQueen.toString());
		
		Board temp1 = new Board();
		Board temp2 = new Board();
		
		System.out.printf("Both the same: %b\n", temp1.equals(temp2));
		
		
		// Added comment
	}

}
