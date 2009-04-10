package game;

import java.awt.Point;
import java.util.Iterator;

import agent.Actions;

import mixmeta4.Board;
import mixmeta4.Move;
import mixmeta4.Moves;
import mixmeta4.Piece;
import mixmeta4.PieceSet;
import search.Node;
import search.NodeInfo;


public class CheXNodeInfo implements NodeInfo {

	boolean isRed;
	double depthlimit;
	Board originboard;
	
	int king = 100;
	int queen = 9;
	int egg = 1;
	int frogger = 1;
	int galaxian = 2;
	int winthrop = 5;
	int pod = 3;
	int knight = 3;
	
	int takeable = 10;
	
	
	public CheXNodeInfo(boolean isRed) {
		
		this.isRed = isRed;
	}
	
	public CheXNodeInfo(boolean isRed, double depth, Board originboard) {
		this.depthlimit = depth;
		this.isRed = isRed;
		this.originboard = originboard;
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
				if(board.getSquare(currentPosition).isOccupiedByOpponent(!this.isRed)) {
					black += takeable;
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
				if(board.getSquare(currentPosition).isOccupiedByOpponent(this.isRed)) {
					red += takeable;
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
