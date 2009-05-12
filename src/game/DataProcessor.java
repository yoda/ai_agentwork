package game;

import java.util.ArrayList;
import java.util.ListIterator;

import mixmeta4.Board;
import mixmeta4.Move;
import mixmeta4.Piece;

public class DataProcessor {

	private String directoryname;
	private ArrayList<Move> moves;
	private Attributes atts;
	private ArrayList<DataAtom> data;
	
	public DataProcessor(String directoryname) {
		this.directoryname = directoryname;
		this.atts = new Attributes();
		this.data = new ArrayList<DataAtom>();
	}
	
	private void scanData() {
		DataReader reader = new DataReader(directoryname);
		ArrayList<DataContainer> boards = reader.readData();
		
		
		ListIterator<DataContainer> it = boards.listIterator();
		int x = 0;
		while(it.hasNext()) {
			DataContainer board = it.next();
			
			if(board.getTheBoard().getSquare(board.getTheBoard().lastMove.getDestination().getLocation()).isOccupiedByBlack()) {
				DataAtom datum = new DataAtom();
				datum.setKey(x);
				datum.setMove(board.getTheBoard().lastMove);
				ListIterator<Piece> pieces = board.getTheBoard().getBlackPieces().listIterator();

				while(pieces.hasNext()) {
					DataPieceAttributes dpa = new DataPieceAttributes();
					Piece piece = pieces.next();
					
					dpa.setPiece(piece);
					
					dpa.addAttribute("canBeTaken");
					dpa.addResult(atts.canBeTaken(board.getTheBoard(), piece));
					
					dpa.addAttribute("canMove");
					dpa.addResult(atts.canMove(board.getTheBoard(), piece));
					
					dpa.addAttribute("canTake");
					dpa.addResult(atts.canTake(board.getTheBoard(), piece));
					
					dpa.addAttribute("takeKing");
					dpa.addResult(atts.takeKing(board.getTheBoard()));
					datum.addPieceAttributes(dpa);
				}
			}
			
		}
	}
	
	public double calcUtility(Board board) {
		return 0.00;
	}
	
	public static void main(String[] args)
	{
		if(args.length != 1)
		{
			System.out.println("Usage: java game.DataProcessor DirectoryName");
			return;
		}
		
		System.out.println("Running the Data Processor over '" + args[0] + "'.");
		DataProcessor d = new DataProcessor(args[0]);
	}
}