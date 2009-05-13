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
	
	private String convertDigitToAlpha(int x) {
		String initial = "" + x;
		String result = "";
		for(int y = 0; y < initial.length(); y++) {
			switch(Character.getNumericValue(initial.charAt(y))) {
				case 0 :
					result += "a";
					break;
				case 1 :
					result += "b";
					break;
				case 2 :
					result += "c";
					break;
				case 3 :
					result += "d";
					break;
				case 4 :
					result += "e";
					break;
				case 5 :
					result += "f";
					break;
				case 6 :
					result += "g";
					break;
				case 7 :
					result += "h";
					break;
				case 8 :
					result += "i";
					break;
				case 9 :
					result += "j";
					break;
			}	
		}
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String result = "";
		result += this.convertDigitToAlpha(this.getKey());
		for(int y = 0; y < this.getPieceAttributesSet().size(); y++) {
			DataPieceAttributes dpa = this.getPieceAttributesSet().get(y);
			//result += " " + dpa.getPiece().toString() + "";
			for(int z = 0; z < dpa.getAttributes().size(); z++) {
				result += " " + dpa.getResults().get(z) + "";
			}
		}
		result += " " + this.fixMove(this.removeSpaces(this.getMove().toString())) + "";
		return result;
	}
	
	private String fixMove(String s) {
		String result = "";
		for(int x = 0; x < s.length(); x++) {
			if(Character.isDigit(s.charAt(x))) {
				result += this.convertDigitToAlpha(s.charAt(x));
			} else if(s.charAt(x) == '>' || s.charAt(x) == '-') {
				result += 'z';
			} else {
				result += s.charAt(x);
			}
		}
		return result;
	}
	
	private String removeSpaces(String s) {
		  StringTokenizer st = new StringTokenizer(s," ",false);
		  String t="";
		  while (st.hasMoreElements()) t += st.nextElement();
		  return t;
  }

}
