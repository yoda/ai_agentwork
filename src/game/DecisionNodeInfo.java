package game;

import java.awt.Point;
import java.io.Serializable;
import java.util.Iterator;
import java.util.ListIterator;

import agent.Actions;

import mixmeta4.Board;
import mixmeta4.Egg;
import mixmeta4.Frogger;
import mixmeta4.Galaxian;
import mixmeta4.King;
import mixmeta4.Knight;
import mixmeta4.Move;
import mixmeta4.Moves;
import mixmeta4.Piece;
import mixmeta4.PieceSet;
import mixmeta4.Pod;
import mixmeta4.Queen;
import mixmeta4.Square;
import mixmeta4.Winthrop;
import search.Node;
import search.NodeInfo;


public class DecisionNodeInfo implements NodeInfo, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean isRed;
	double depthlimit;
	
	int king = 1;
	String aKing = (new King(false, false)).toString().toLowerCase();
	int queen = 9;
	String aQueen = (new Queen(false, false)).toString().toLowerCase();
	int egg = 2;
	String anEgg = (new Egg(false, false)).toString().toLowerCase();
	int frogger = 9;
	String aFrogger = (new Frogger(false, false)).toString().toLowerCase();
	int galaxian = 9;
	String aGalaxian = (new Galaxian(false, false)).toString().toLowerCase();
	int winthrop = 8;
	String aWinthrop = (new Winthrop(false, false)).toString().toLowerCase();
	int pod = 2;
	String aPod = (new Pod(false, false)).toString().toLowerCase();
	int knight = 7;
	String aKnight = (new Knight(false, false)).toString().toLowerCase();
	
	int takeable = 10;
	int threat = 20;
	
	
	public DecisionNodeInfo(boolean isRed) {
		
		this.isRed = isRed;
	}
	
	public DecisionNodeInfo(boolean isRed, double depth) {
		this.depthlimit = depth;
		this.isRed = isRed;
	}

	@Override
	public double getDepthLimit() {
		return this.depthlimit;
	}

	@Override
	public boolean isGoal(Node node) {
		if(this.isRed && ((Board)node.getState()).blackKingTaken()) {
			return true;
		}
		if(!this.isRed && ((Board)node.getState()).redKingTaken()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isTerminal(Node node) {
		if(node.getPath().size() >= this.getDepthLimit()) {
			return true;
		}
		if(((Board)node.getState()).redKingTaken() || ((Board)node.getState()).blackKingTaken()) {
			return true;
		}
		return false;
	}

	@Override
	public void setDepthLimit(double limit) {
		this.depthlimit = limit;
	}

	public int getPieceValue(Piece thepiece) {
		String currentPiece = thepiece.toString().toLowerCase();
		if(currentPiece.compareTo(this.aQueen) == 0) {
			return this.queen;
		}
		
		if(currentPiece.compareTo(this.anEgg) == 0) {
			return this.egg;
		}
		
		if(currentPiece.compareTo(this.aFrogger) == 0) {
			return this.frogger;
		}
		
		if(currentPiece.compareTo(this.aGalaxian) == 0) {
			return this.galaxian;
		}
		
		if(currentPiece.compareTo(this.aKing) == 0) {
			return this.king;
		}
		
		if(currentPiece.compareTo(this.aKnight) == 0) {
			return this.knight;
		}
		
		if(currentPiece.compareTo(this.aPod) == 0) {
			return this.pod;
		}
		
		if(currentPiece.compareTo(this.aWinthrop) == 0) {
			return this.winthrop;
		}
		
		return 0;
	}
	
	@Override
	public double utility(Node node) {
		
	}
	

}
