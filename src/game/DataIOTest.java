package game;

import java.util.ArrayList;
import java.util.ListIterator;

import mixmeta4.Board;
import mixmeta4.Game;
import mixmeta4.Moves;
import mixmeta4.Player;
import samplePlayers.Marvin;
import search.Node;

public class DataIOTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Player red = new EricMiniMax(true);
	    Player black = new EricMiniMax(false);
	    
	    String setup =""+
	      "..wnqknw..\n"+
	      "._epepep_.\n"+
	      "__________\n"+
	      "__..__..__\n"+
	      "__________\n"+
	      "._epepep_.\n"+
	      "..wnqknw..\n";
	    
		Game game = new Game("CheX",setup,red,black, 60, 6, "terminal");
		
		Board temp = new Board(game, setup, false);
		DataContainer data = new DataContainer(temp);
		
		String filename = "testfile";
		DataWriter writer = new DataWriter(filename, filename + "dir");
		DataReader reader = new DataReader(filename + "dir");
		
		if(writer.writeData(data)) {
			System.out.println("Node written!");
			writer.closeFile();
		} else {
			System.out.println("Node write failed!");
			System.exit(1);
		}
		
		ArrayList<DataContainer> tempList = reader.readData();
		
		ListIterator<DataContainer> it = tempList.listIterator();
		while(it.hasNext()) {
			System.out.println(it.next().getTheMoves());
		}
		
		

	}

}
