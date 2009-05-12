package game;

import java.io.Serializable;
import java.util.ArrayList;

import mixmeta4.Move;
import mixmeta4.Piece;

public class DataPieceAttributes implements Serializable {
	
	private ArrayList<Boolean> results;
	private ArrayList<String> attributes;
	private Piece piece;
	
	public DataPieceAttributes() {
		this.results = new ArrayList<Boolean>();
		this.attributes = new ArrayList<String>();
	}
	
	public void addResult(boolean res) {
		this.results.add(res);
	}
	
	public ArrayList<Boolean> getResults() {
		return this.results;
	}
	
	public void addAttribute(String attribute) {
		this.attributes.add(attribute);
	}
	
	public ArrayList<String> getAttributes() {
		return this.attributes;
	}
	
	public void setPiece(Piece thePiece) {
		this.piece = thePiece;
	}
	
	public Piece getPiece() {
		return this.piece;
	}

}
