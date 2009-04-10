package game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

import agent.Action;
import agent.Actions;
import agent.Percept;
import mixmeta4.Board;
import mixmeta4.Move;
import mixmeta4.Piece;
import mixmeta4.Player;

/*
 * A simple reflex agent simply chooses a valid action for a given percept. 
 * The maximal set of valid "condition-action" pairs is provided implicitly,
 * by the board.getActions() method, since only actions which are valid,
 * (that is, the "condition" is satisfied) are returned.
 * 
 * Implement a simple reflex player by implementing the getAction method,
 * to randomly choose a valid action. You can add your player to the player package.
 * Try your agent out against one of the other agents to check that it works. 
 */

public class EricNSRM extends Player implements agent.Agent {

	public EricNSRM(boolean isRed) {
		super(isRed, "Eric - Not So Random (Mobility)");
		// TODO Auto-generated constructor stub
	}

	@Override
	public Action getAction(Percept cP) {
		Board board = (Board) cP;
		Actions moves = board.getActions();
		Actions pieceMoves = new Actions();
		Actions tempPieceMoves = new Actions();
		Random random = new Random();
		if(this.isRed) {
			for (ListIterator<Piece> pieces = board.getRedPieces().listIterator(); pieces.hasNext();) {
				
				tempPieceMoves = board.getActions(pieces.next());
				if(tempPieceMoves.size() > pieceMoves.size()) {
					pieceMoves = (Actions) tempPieceMoves.clone();
				}
			}
			if(pieceMoves.size() > 1) {
				return (Action) pieceMoves.get(random.nextInt(pieceMoves.size()));
			} else {
				int index = random.nextInt(moves.size());
				return (Action) moves.get(index);
			}
			
			
		} else {
			for (ListIterator<Piece> pieces = board.getBlackPieces().listIterator(); pieces.hasNext();) {
				
				tempPieceMoves = board.getActions(pieces.next());
				if(tempPieceMoves.size() > pieceMoves.size()) {
					pieceMoves = (Actions) tempPieceMoves.clone();
				}
			}
			if(pieceMoves.size() > 1) {
				return (Action) pieceMoves.get(random.nextInt(pieceMoves.size()));
			} else {
				int index = random.nextInt(moves.size());
				return (Action) moves.get(index);
			}
		}
	}

}
