package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

public class DataRead {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String filename = "testfiledir.2";
		DataReader reader = new DataReader(filename);
		
		ArrayList<DataContainer> tempList = reader.readData();
		
		System.out.println("Listing " + reader.getDirectoryname() + " contents:");
		ListIterator<DataContainer> it = tempList.listIterator();
		System.out.println(tempList.size());
		while(it.hasNext()) {
			DataContainer tempData = it.next();
			System.out.println("--------------------------------");
			if(tempData.getTheMoves().size() > 0) {
				System.out.println("Move: " + tempData.getTheMoves().get(tempData.getTheMoves().size() -1));
			}
			System.out.println("String representation of the board:");
			System.out.println(tempData.getTheBoard());
			System.out.println(tempData.getTheBoard().getBlackPieces());
			System.out.println("String representation of the squares:");
			String sqstring = "";
			for(int x = 0; x < tempData.getTheSquares().length; x++) {
				for(int y = 0; y < tempData.getTheSquares()[1].length; y++) {
					if(tempData.getTheSquares()[x][y] == null) {
						sqstring += "   ";
					} else
						sqstring += tempData.getTheSquares()[x][y];
				}
				sqstring += "\n";
			}
			System.out.println(sqstring);
			System.out.println();
			if(tempData.getTheMoves().size() > 0) {
				System.out.println(tempData.getTheMoves().get(tempData.getTheMoves().size() -1));
			}
			
		}
		

	}

}
