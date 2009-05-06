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


public class CheXNodeInfo implements NodeInfo, Serializable {

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
		Board board = (Board)node.getState();
		int red = 0;
		int black = 0;
		/*
		if(!board.redToMove) {
			int a = checkForAThreat(board); 
			red -= a;
			black += a;
		} else {
			int b = checkForAThreat(board); 
			red += b;
			black -= b;
		}
		*/
		
		if(board.redKingTaken()) {
			black += 1000;
			red -= 1000;
		}
		PieceSet blackPieces = board.getBlackPieces();
		blackPieces.count();
		// Black on board value
		black += blackPieces.numberOfEggs * this.egg;
		black += blackPieces.numberOfFroggers * this.frogger;
		black += blackPieces.numberOfGalaxians * this.galaxian;
		black += blackPieces.numberOfKings * this.king; //* 1000;
		black += blackPieces.numberOfKnights * this.knight;
		black += blackPieces.numberOfPods * this.pod;
		black += blackPieces.numberOfQueens * this.queen;
		black += blackPieces.numberOfWinthrops * this.winthrop;
		
		
		if(board.blackKingTaken()) {
			black -= 1000;
			red += 1000;
		}
		PieceSet redPieces = board.getRedPieces();
		redPieces.count();
		// Red on board value
		red += redPieces.numberOfEggs * this.egg;
		red += redPieces.numberOfFroggers * this.frogger;
		red += redPieces.numberOfGalaxians * this.galaxian;
		red += redPieces.numberOfKings * this.king; //* 1000;
		red += redPieces.numberOfKnights * this.knight;
		red += redPieces.numberOfPods * this.pod;
		red += redPieces.numberOfQueens * this.queen;
		red += redPieces.numberOfWinthrops * this.winthrop;

		/*
		// Black mobility
		ListIterator<Piece> bpiece = board.getBlackPieces().listIterator();
		while(bpiece.hasNext()) {
			Piece currentPiece = bpiece.next();
			Moves currentPieceMoves = (Moves) board.getActions(currentPiece);
			ListIterator<Move> moves = currentPieceMoves.listIterator();
			while(moves.hasNext()) {
				Move currentMove = moves.next();
				Point currentPosition = currentMove.getDestination();
	//			int threatened = checkForNextThreat(board, currentMove, board.getSquare(currentPosition));
	//			black -= threatened;
				if(board.getSquare(currentPosition).isOccupiedByRed()) {
					black += takeable * this.getPieceValue(board.getSquare(currentPosition).look());
					
		//			if(threatened == 0) {
						red -= threat * this.getPieceValue(board.getSquare(currentPosition).look());
			//		}
					// If this piece can take, or if a piece can be taken.
				}
			}
		//	black += currentPieceMoves.size(); // Number of moves this piece can move aka its "mobility".
			
		}

		// Red mobility
		ListIterator<Piece> rpiece = board.getRedPieces().listIterator();
		while(rpiece.hasNext()) {
			Piece currentPiece = rpiece.next();
			Moves currentPieceMoves = (Moves) board.getActions(currentPiece);
			ListIterator<Move> moves = currentPieceMoves.listIterator();
			while(moves.hasNext()) {
				Move currentMove = moves.next();
				
				Point currentPosition = currentMove.getDestination();
		//		int threatened = checkForNextThreat(board, currentMove, board.getSquare(currentPosition));
		//		red -= threatened;
				if(board.getSquare(currentPosition).isOccupiedByBlack()) {
					red += takeable * this.getPieceValue(board.getSquare(currentPosition).look());
		//			if(threatened == 0) {
						black -= threat * this.getPieceValue(board.getSquare(currentPosition).look());
		//			}	
					// If this piece can take, or if a piece can be taken.
				}
			}
			//red += currentPieceMoves.size(); // Number of moves this piece can move aka its "mobility".
		}
		*/
		// If agent is playing as red, make it red less the utility of black.
		if(this.isRed) {
			return red - black;
		}
		
		// If agent is playing as black make it black less the utility of red.
		return black - red;
	}
	
	private int checkForAThreat(Board aboard) {
		int posthreat = 0;
		Moves attacks = (Moves)aboard.getActions(aboard.getSquare(aboard.lastMove.getDestination()).look());
		ListIterator<Move> it = attacks.listIterator();
		while(it.hasNext()) {
			Move attack = it.next();
			if(aboard.getSquare(attack.getDestination()).isOccupiedByOpponent(aboard.redToMove)) {
				posthreat += threat * this.getPieceValue(aboard.getSquare(attack.getDestination()).look());
			}
		}
		return posthreat;
	}
	
	private int checkForNextThreat(Board aboard, Move currentMove, Square thesquare) {
		Board temp = (Board)aboard.clone();
		temp.update(currentMove);
		if(temp.redToMove) {
			for(Iterator<Piece> rpiece = temp.getRedPieces().iterator(); rpiece.hasNext(); ) {
				Piece currentPiece = rpiece.next();
				Moves currentPieceMoves = (Moves) temp.getActions(currentPiece);
				for(Iterator<Move> moves = currentPieceMoves.iterator(); moves.hasNext(); ) {
					Move nextMove = moves.next();
					Point nextPosition = nextMove.getDestination();
					if(temp.getSquare(nextPosition).isOccupiedByBlack()) {
						if(temp.getSquare(nextPosition).equals(thesquare)) {
							return threat * this.getPieceValue(thesquare.look());
						}
					}
				}
			}
		} else if(!temp.redToMove) {
			for(Iterator<Piece> bpiece = temp.getBlackPieces().iterator(); bpiece.hasNext(); ) {
				Piece currentPiece = bpiece.next();
				Moves currentPieceMoves = (Moves) temp.getActions(currentPiece);
				for(Iterator<Move> moves = currentPieceMoves.iterator(); moves.hasNext(); ) {
					Move nextMove = moves.next();
					Point nextPosition = nextMove.getDestination();
					if(temp.getSquare(nextPosition).isOccupiedByRed()) {
						if(temp.getSquare(nextPosition).equals(thesquare)) {
							return threat * this.getPieceValue(thesquare.look());
						}
					}
				}
			}
		} 
			return 0;
	}

}
