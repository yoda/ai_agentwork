package game;

import java.io.Serializable;
import java.util.ArrayList;

import mixmeta4.Move;

public class DataAtom implements Serializable {
	
	private int key;
	private ArrayList<DataPieceAttributes> pieceList;
	private Move move;
	
	public DataAtom() {
		this.pieceList = new ArrayList<DataPieceAttributes>();
	}
	
	public void setKey(int x) {
		this.key = x;
	}
	
	public int getKey() {
		return this.key;
	}
	
	public void addPieceAttributes(DataPieceAttributes dpa) {
		this.pieceList.add(dpa);
	}
	
	public ArrayList<DataPieceAttributes> getPieceAttributesSet() {
		return this.pieceList;
	}
	
	public void setMove(Move theMove) {
		this.move = theMove;
	}
	
	public Move getMove() {
		return this.getMove();
	}

}
