package player;
import java.awt.Point;
import java.util.ListIterator;
import java.util.ArrayList;
import mixmeta4.*;

public class Attributes {
	
	// a helper method to find the location of the king
	// todo: change to binary search
	// @return a Square, because a square can get both location and piece
	private Square findKing (Board board, Boolean isRed) {
		PieceSet ps = null;
		Piece currentPiece = null;
		
		// Get the right side
		if (board.isRed) {
			ps = board.getRedPieces();
		} else {
			ps = board.getBlackPieces();
		}
		
		// Iterate through the piece set to get all pieces
		ListIterator ls = ps.listIterator();
		while (ls.hasNext()) {
			currentPiece = (Piece) ls.next();
			// If found king, break from search
			if (currentPiece.toString().toLowerCase().equals("k")) {
				break;
			}
		}
		
		// Return the Piece or null
		return currentPiece;
	}
	
	public boolean takeKing (Board board) {
		Moves move = (Moves) board.getActions();
		Move currentMove = null;
		
		// Iterate through all moves
		ListIterator ls = move.listIterator();
		//!board.redToMove because want to get opponent's king and it's my turn to move
		Square kingLocation = findKing (board, !board.redToMove);
		//get all my moves' destinations
		while (ls.hasNext()) {
			currentMove = (Move) ls.next();
			if (currentMove.getDestination().distance(kingLocation.getLocation()) == 0) {
				return true;
			}
		}
		return false;
	}
	
	// can just call canBeTake function and the target piece will be my king
	// @return true if our King is under threat.
	public boolean kingOnThread (Board board) {
		// Find our King in the board
		Square sq = findKing(board, board.redToMove);
		// Return if it can be taken
		return canBeTaken(board, sq.look());
	}
	
	// @return true if piece can Move.
	public boolean canMove (Board board, Piece which) {
		return (board.getActions(which).size() > 0);
	}
	
	// @return true if piece can take any of the opponents pieces.
	public boolean canTake (Board board, Piece which) {
		// check a piece whether can move
		if (canMove(board, which)) {
			Point dest;
			Moves moves = (Moves) board.getActions(which);
			Move current;
			
			// Iterate through pieces available moves.
			ListIterator ls = moves.listIterator();
			while (ls.hasNext()) {
				current = (Move) ls.next();
				dest = current.getDestination();
				if (board.getSquare(dest).isOccupiedByOpponent(which.isRed)) {
					return true;
				}
			}
		}
		return false;
	}
	
	// @return true if the piece can be taken by an opponent.
	public boolean canBeTaken (Board board, Piece which) {
		Point location = which.square.getLocation();
		PieceSet opp;//opponent's pieceSet
		if (which.isRed) {
			opp = board.getBlackPieces();
		} else {
			opp = board.getRedPieces();
		}
		Point dest;
		Piece currentPiece;
		Moves moves;
		Move currentMove;
		ListIterator ls = opp.listIterator();
		ListIterator li;
		//the double while loop here is checking each opponent's move's destination 
		//whether is same as the target piece
		// Iterate through all of opponents pieces.
		while (ls.hasNext()) {
			currentPiece = (Piece) ls.next();
			moves = (Moves) board.getActions(currentPiece);
			li = moves.listIterator();
			// Check all moves for that piece.
			while (li.hasNext()) {
				currentMove = (Move) li.next();
				dest = currentMove.getDestination();
				if (dest.distance(location) == 0) {
					return true;
				}
			}
		}
		return false;
	}
}