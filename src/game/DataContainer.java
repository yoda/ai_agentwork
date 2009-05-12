package game;

import java.io.Serializable;

import mixmeta4.Board;
import mixmeta4.Moves;
import mixmeta4.PieceSet;
import mixmeta4.Square;

public class DataContainer implements Serializable, Comparable<DataContainer> {
	
	private static final long serialVersionUID = 1L;
	private Moves themoves;
	private Square thesquares[][];
	private PieceSet blackPieces;
	private PieceSet redPieces;
	private boolean redToMove;
	private String theStringBoard;
	private Board theBoard;
	
	public DataContainer(Board theBoard) {
		this.theBoard = (Board)theBoard.clone();
		this.themoves = (Moves)theBoard.getMovesSoFar().clone();
		this.thesquares = theBoard.squares.clone();
		this.theStringBoard = theBoard.toString();
		//this.blackPieces = (PieceSet)theBoard.getBlackPieces().clone();
		//this.redPieces = (PieceSet)theBoard.getRedPieces().clone();
		this.redToMove = theBoard.redToMove;
	}
	
	public Board getTheBoard() {
		return this.theBoard;
	}
	
	public Moves getTheMoves() {
		return this.themoves;
	}
	
	public String getTheStringBoard() {
		return this.theStringBoard;
	}
	
	public Square[][] getTheSquares() {
		return this.thesquares;
	}
	
	public PieceSet getBlackPieces() {
		return this.blackPieces;
	}
	
	public PieceSet getRedPieces() {
		return this.redPieces;
	}
	
	public boolean getRedToMove() {
		return this.redToMove;
	}

	@Override
	public int compareTo(DataContainer o) {
		if(this.getTheMoves().size() < o.getTheMoves().size()) {
			return -1;
		}
		if(this.getTheMoves().size() > o.getTheMoves().size()) {
			return 1;
		}
		
		return 0;
	}

}
