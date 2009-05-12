package game;

import mixmeta4.Board;
import mixmeta4.Move;
import mixmeta4.Moves;
import mixmeta4.Piece;



public class DTree {

	private Piece currentPiece;
	private Move move;
	private Board board;
	
	public DTree(Piece piece, Board board) {
		this.currentPiece = piece;
		this.move = null;
		this.board = board;
	}
	
	public Move moveDecision()
	{
		if(this.move == null)
			return getDecision();
		else
			return move;
	}
	
	private Move getDecision() {
		Moves moves = this.canMove(this.board);
		if((moves) != null) {
			return null;
		} else
			return (Move)moves.get(0);
	}
	
	public Moves canMove(Board board) {
		Moves moves = (Moves)board.getActions(this.currentPiece);
		if(moves.size() > 0) {
			return moves;
		}
		return null;
	}
}
