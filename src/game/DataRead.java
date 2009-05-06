package game;

import java.util.ArrayList;
import java.util.ListIterator;

public class DataRead {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String filename = "testfiledir.0";
		DataReader reader = new DataReader(filename);
		
		ArrayList<DataContainer> tempList = reader.readData();
		System.out.println("Listing " + reader.getDirectoryname() + " contents:");
		ListIterator<DataContainer> it = tempList.listIterator();
		System.out.println(tempList.size());
		while(it.hasNext()) {
			DataContainer tempData = it.next();
			System.out.println(tempData.getTheBoard());
			if(tempData.getTheMoves().size() > 0) {
				System.out.println(tempData.getTheMoves().get(tempData.getTheMoves().size() -1));
			}
			
		}
		

	}

}
