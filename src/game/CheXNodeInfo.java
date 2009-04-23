package game;

import java.awt.Point;
import java.util.Iterator;

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
import mixmeta4.Winthrop;
import search.Node;
import search.NodeInfo;


public class CheXNodeInfo implements NodeInfo {

	boolean isRed;
	double depthlimit;
	
	int king = 11;
	String aKing = (new King(false, false)).toString().toLowerCase();
	int queen = 9;
	String aQueen = (new Queen(false, false)).toString().toLowerCase();
	int egg = 1;
	String anEgg = (new Egg(false, false)).toString().toLowerCase();
	int frogger = 1;
	String aFrogger = (new Frogger(false, false)).toString().toLowerCase();
	int galaxian = 2;
	String aGalaxian = (new Galaxian(false, false)).toString().toLowerCase();
	int winthrop = 5;
	String aWinthrop = (new Winthrop(false, false)).toString().toLowerCase();
	int pod = 3;
	String aPod = (new Pod(false, false)).toString().toLowerCase();
	int knight = 3;
	String aKnight = (new Knight(false, false)).toString().toLowerCase();
	
	int takeable = 10;
	
	
	public CheXNodeInfo(boolean isRed) {
		
		this.isRed = isRed;
	}
	
	public CheXNodeInfo(boolean isRed, double depth) {
		this.depthlimit = depth;
		this.isRed = isRed;
	}

	@Override
	public double getDepthLimit() {
		return this.depthlimit;
	}

	@Override
	public boolean isGoal(Node node) {
		return false;
	}

	@Override
	public boolean isTerminal(Node node) {
		if(node.getPath().size() >= this.getDepthLimit()) {
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
		Board board = (Board)node.getState();
		int red = 0;
		int black = 0;
		
		PieceSet blackPieces = board.getBlackPieces();
		blackPieces.count();
		// Black on board value
		black += blackPieces.numberOfEggs * this.egg;
		black += blackPieces.numberOfFroggers * this.frogger;
		black += blackPieces.numberOfGalaxians * this.galaxian;
	//	black += blackPieces.numberOfKings * this.king;
		black += blackPieces.numberOfKnights * this.knight;
		black += blackPieces.numberOfPods * this.pod;
		black += blackPieces.numberOfQueens * this.queen;
		black += blackPieces.numberOfWinthrops * this.winthrop;
		
		PieceSet redPieces = board.getRedPieces();
		redPieces.count();
		// Red on board value
		red += redPieces.numberOfEggs * this.egg;
		red += redPieces.numberOfFroggers * this.frogger;
		red += redPieces.numberOfGalaxians * this.galaxian;
	//	red += redPieces.numberOfKings * this.king;
		red += redPieces.numberOfKnights * this.knight;
		red += redPieces.numberOfPods * this.pod;
		red += redPieces.numberOfQueens * this.queen;
		red += redPieces.numberOfWinthrops * this.winthrop;
		
		// Black mobility
		for(Iterator<Piece> bpiece = board.getBlackPieces().iterator(); bpiece.hasNext(); ) {
			Piece currentPiece = bpiece.next();
			Moves currentPieceMoves = (Moves) board.getActions(currentPiece);
			for(Iterator<Move> moves = currentPieceMoves.iterator(); moves.hasNext(); ) {
				Move currentMove = moves.next();
				Point currentPosition = currentMove.getDestination();
				if(board.getSquare(currentPosition).isOccupiedByRed()) {
					black += takeable * this.getPieceValue(board.getSquare(currentPosition).look());
					red -= this.getPieceValue(board.getSquare(currentPosition).look());
				}
			}
			black += currentPieceMoves.size();
			
		}
//		Point currentPosition = currentMove.getDestination();
  // 	 if(board.getSquare(currentPosition).isOccupiedByOpponent(this.isRed)) {
   //		 return currentMove; 
   	// }
		// Red mobility
		for(Iterator<Piece> rpiece = board.getRedPieces().iterator(); rpiece.hasNext(); ) {
			Piece currentPiece = rpiece.next();
			Moves currentPieceMoves = (Moves) board.getActions(currentPiece);
			for(Iterator<Move> moves = currentPieceMoves.iterator(); moves.hasNext(); ) {
				Move currentMove = moves.next();
				Point currentPosition = currentMove.getDestination();
				if(board.getSquare(currentPosition).isOccupiedByBlack()) {
					red += takeable * this.getPieceValue(board.getSquare(currentPosition).look());
					black -= this.getPieceValue(board.getSquare(currentPosition).look());
				}
			}
			red += currentPieceMoves.size();
		}
		
		if(this.isRed) {
			return red - black;
		}
		
		
		return black - red;
	}

}
