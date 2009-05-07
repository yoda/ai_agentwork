package game;

import java.util.ArrayList;
import java.util.ListIterator;

import mixmeta4.Board;
import mixmeta4.Move;
import mixmeta4.Piece;

public class DataProcessor {

	private String directoryname;
	private ArrayList<Move> moves;
	
	public DataProcessor(String directoryname) {
		this.directoryname = directoryname;
	}
	
	private void scanData() {
		DataReader reader = new DataReader(directoryname);
		ArrayList<DataContainer> boards = reader.readData();
		
		
		ListIterator<DataContainer> it = boards.listIterator();
		
		while(it.hasNext()) {
			DataContainer before = it.next();
			
			ArrayList<Move> this.boardMoves = new ArrayList<Move>();
			Board currentBoard = temp.getTheBoard();
			ListIterator<Piece> pieces = currentBoard.getRedPieces().listIterator();
			while(pieces.hasNext()) {
				Piece p = pieces.next();
				DTree tree = new DTree(p, currentBoard);
				currentBoard.setcost = tree.getcost()
				
			}
		}
	}
	
	public double calcUtility(Board board) {
		
	}
	
}
