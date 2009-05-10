package player;
import java.awt.Point;
import java.util.ListIterator;
import java.util.ArrayList;
import mixmeta4.*;

public class Attributes {
	
	static final int canmove = 1;
	static final int cantake =2;
	static final int canbetaken = 3;
	
	public boolean canMove (Board board, Piece which) {
		if (board.getActions(which).size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean canTake (Board board, Piece which) {
		if (canMove(board, which)) {//check a piece whether can move
			Point dest;
			boolean colour = which.isRed;//get the piece colour
			Moves moves = (Moves) board.getActions(which);
			Move current;
			ListIterator ls = moves.listIterator();
			while (ls.hasNext()) {
				current = (Move) ls.next();
				dest = current.getDestination();
				if (board.getSquare(dest).isOccupiedByOpponent(colour)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean canBeTaken (Board board, Piece which) {
		Point location = which.square.getLocation();
		boolean colour = which.isRed;
		PieceSet opp;//opponent's pieceset
		if (colour) {
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
		while (ls.hasNext()) {
			currentPiece = (Piece) ls.next();
			moves = (Moves) board.getActions(currentPiece);
			li = moves.listIterator();
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
	//@attribute -  means use which function will be used from above
	public boolean[] getAll (Board board, PieceSet ps, int attribute) {
		boolean result[] = {};
		Piece currentPiece;
		ListIterator ls = ps.listIterator();
		int i = 0;
		while (ls.hasNext()) {
			currentPiece = (Piece) ls.next();
			if (attribute == 1) {
				result[i] = canMove(board, currentPiece);
			} else if (attribute ==2) {
				result[i] = canTake(board, currentPiece);
			} else if (attribute ==3) {
				result[i] = canBeTaken(board, currentPiece);
			}
			
			i++;
		}
		return result;
	}
}
