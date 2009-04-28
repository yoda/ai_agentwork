package game;

import java.util.Comparator;

import mixmeta4.Move;

public class MoveValuePair implements Comparable<MoveValuePair>, Comparator<MoveValuePair> {
	
	Move move;
	double value;
	
	public MoveValuePair(Move theMove, double theValue) {
		this.move = theMove;
		this.value = theValue;
	}
	
	public double getValue() {
		return this.value;
	}
	
	public Move getMove() {
		return this.move;
	}
	
	public String toString() {
		String result = "";
		if(this.move != null) {
			result += getMove().toString();
			result += " : ";
			result += getValue();
			return result;
		}
		result += "null : ";
		result += getValue();
		return result;
	}

	@Override
	public int compare(MoveValuePair first, MoveValuePair second) {
					
		if(first.getValue() == second.getValue()) {
			return 0;
		}
		if(first.getValue() > second.getValue()) {
			return 1;
		}
		
		return -1;
	}

	@Override
	public int compareTo(MoveValuePair second) {
		if(this.getValue() == second.getValue()) {
			return 0;
		}
		if(this.getValue() > second.getValue()) {
			return 1;
		}
		
		return -1;
	}
	
}