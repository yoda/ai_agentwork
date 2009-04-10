package game;

import java.util.Random;

import agent.Action;
import agent.Actions;
import agent.Percept;
import mixmeta4.Board;
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

public class EricSRA extends Player implements agent.Agent {

	public EricSRA(boolean isRed) {
		super(isRed, "Eric - Simple Reflex(Random) Agent");
		// TODO Auto-generated constructor stub
	}

	@Override
	public Action getAction(Percept cP) {
		 Board board = (Board) cP;
	     Actions moves = board.getActions();
	     Random random = new Random();
	     int index = random.nextInt(moves.size());
	     return (Action) moves.get(index);
	}

}
