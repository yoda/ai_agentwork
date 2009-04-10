package game;

import java.awt.Point;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;

import agent.Action;
import agent.Actions;
import agent.Percept;
import mixmeta4.Board;

import mixmeta4.Moves;
import mixmeta4.Player;
import mixmeta4.Move;

/*
 * This simple reflex agent clearly has no skills that will help it win the game. 
 * It can be improved by refining the conditions under which actions are applied.
 *  
 * An example strategy for Finish First (which may or may not help) would be to move,
 * the piece that has the longest distance to go.
 *  
 * An example strategy for TakeAll would be to move the more mobile pieces,
 * (in the hope of taking before being taken).
 * 
 * Try refining the conditions that select the action that is chosen. 
 * See if you can develop an agent that beats the random reflex agent. 
 */

public class EricNSRD extends Player implements agent.Agent {

	public EricNSRD(boolean isRed) {
		super(isRed, "Eric - Not So Random (Distance)");
		// TODO Auto-generated constructor stub
	}

	@Override
	public Action getAction(Percept cP) {
		 Board board = (Board) cP;
		 Action bestAction = null;
		 double biggest = -1;
	     Actions moves = board.getActions();
	     Random random = new Random();
	     for (ListIterator<Move> it = moves.listIterator(moves.size());
	     	it.hasPrevious(); ) {
	    	 Move t = it.previous();
	    	 Point a = t.getOrigin();
	    	 Point b = t.getDestination();
	    	 if(a.distance(b) > biggest) {
	    		 bestAction = t;
	    	 }
	     }

	     if(bestAction != null) {
	    	 return bestAction;
	     } else {
	    	int index = random.nextInt(moves.size());
	     	return (Action) moves.get(index);
	     }
	     
	}

}
