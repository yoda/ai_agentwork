package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

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
		return this.move;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String result = "";
		result += this.getKey();
		for(int y = 0; y < this.getPieceAttributesSet().size(); y++) {
			DataPieceAttributes dpa = this.getPieceAttributesSet().get(y);
			//result += " " + dpa.getPiece().toString() + "";
			for(int z = 0; z < dpa.getAttributes().size(); z++) {
				result += " " + dpa.getResults().get(z) + "";
			}
		}
		result += " " + this.removeSpaces(this.getMove().toString()) + "";
		return result;
	}
	
	private String removeSpaces(String s) {
		  StringTokenizer st = new StringTokenizer(s," ",false);
		  String t="";
		  while (st.hasMoreElements()) t += st.nextElement();
		  return t;
  }

}
