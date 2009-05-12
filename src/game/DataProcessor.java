package game;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.ListIterator;

import mixmeta4.Board;
import mixmeta4.Move;
import mixmeta4.Moves;
import mixmeta4.Piece;

public class DataProcessor {

	private ArrayList<String> directories;
	private ArrayList<Move> moves;
	private Attributes atts;
	private ArrayList<DataAtom> data;
	private String directory;
	private boolean debug = true;
	private boolean writeable;
	
	public DataProcessor(ArrayList<String> directories) {
		this.directories = directories;
		this.atts = new Attributes();
		this.data = new ArrayList<DataAtom>();
		this.writeable = false;
	}
	
	public ArrayList<DataAtom> scanData() {
		for(int x = 0; x < this.directories.size(); x++) {
			this.scanOneDirectory(this.directories.get(x));
		}
		this.writeable = true;
		return this.data;
		
	}
	
	public boolean writeData(String filename) {
		if(!this.writeable) {
			return false;
		}

		File file;
		int x = 0;
		do {
			file = new File(filename + "." + x);
			x++;
		} while(file.exists());
		try {
			file.createNewFile();
			file.setWritable(true, true);
		} catch (IOException e) {
			e.printStackTrace();
			if(debug) System.out.println("Unable to create file!");
		}
		FileOutputStream fos;
		PrintStream pos;
		if(file.canWrite()) {
			try {
				fos = new FileOutputStream(file, true);
				pos = new PrintStream(fos);
				for(int z = 0; z < this.data.size(); z++) {
					pos.println(this.data.get(z).toString());
				}
			} catch (FileNotFoundException e) {
				if(debug) System.out.println("Failed creating FileOutputStream!");
				if(debug) e.printStackTrace();
				return false;
			}
			pos.close();
			try {
				fos.close();
			} catch (IOException e) {
				if(debug) System.out.println("Unable to close FileOutputStream!");
				if(debug) e.printStackTrace();
			}
			return true;
		} 
		return false;
	}
	
	private void scanOneDirectory(String directoryname) {
		DataReader reader = new DataReader(directoryname);
		ArrayList<DataContainer> boards = reader.readData();
		
		
		ListIterator<DataContainer> it = boards.listIterator();
		int x = 0;
		while(it.hasNext()) {
			DataContainer board = it.next();
			Moves theMoves = board.getTheMoves();
			Move moi = null;
			if(theMoves.size() > 1) {
				moi = (Move)theMoves.get(theMoves.size() - 1);
			}
			if(moi != null && board.getTheBoard().getSquare(moi.getDestination().getLocation()).isOccupiedByBlack()) {
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
				x++;
				data.add(datum);
			}
			
		}
	}
	
	public double calcUtility(Board board) {
		return 0.00;
	}
	
	public static void main(String[] args)
	{
		/*
		if(args.length != 1)
		{
			System.out.println("Usage: java game.DataProcessor DirectoryName");
			return;
		}
		*/
		String dirname = "testfiledir.0";
		ArrayList<String> directories = new ArrayList<String>();
		directories.add(dirname);
		System.out.println("Running the Data Processor over '" + dirname + "'.");
		DataProcessor d = new DataProcessor(directories);
		ArrayList<DataAtom> temp = d.scanData();
		d.writeData(dirname + ".results");
		System.out.println("Size of temp: " + temp.size());
		for(int x = 0; x < temp.size(); x++) {
			System.out.println("Board: " + x);
			DataAtom atts = temp.get(x);
			System.out.println("\tMove: " + atts.getMove().toString());
			for(int y = 0; y < atts.getPieceAttributesSet().size(); y++) {
				DataPieceAttributes dpa = atts.getPieceAttributesSet().get(y);
				System.out.println("\t\tPiece: " + dpa.getPiece().toString());
				for(int z = 0; z < dpa.getAttributes().size(); z++) {
					System.out.println("\t\t\t" + dpa.getAttributes().get(z) + " : " + dpa.getResults().get(z));
				}
			}
		}
	}
}