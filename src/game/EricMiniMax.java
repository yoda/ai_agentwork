package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import search.DepthLimitedSearch;
import search.Minimax;
import search.Node;

import agent.Action;
import agent.Actions;
import agent.Percept;
import mixmeta4.Board;
import mixmeta4.Move;
import mixmeta4.Moves;
import mixmeta4.Piece;
import mixmeta4.PieceSet;
import mixmeta4.Player;

/*
 * A one-step look-ahead agent will try each of the possible moves using,
 * a model of the environment - in this case the Board that has been provided,
 * to you as the percept - and evaluate the resulting positions to select the,
 * most promising one. 
 * This evaluation function is an example of a utility function,
 * (hence this is a simple utility-based agent).
 * 
 * For example, one simple strategy would be to move to a position that,
 * minimises the number of options available to your opponent. 
 * In this case the utility function might be the negation of the number,
 * of moves available to the opponent (so that fewer moves gives a higher value).
 * 
 * Implement an agent with this evaluation function. You can do this by,
 * using the Board as your implementation of State - cloning a new copy,
 * of the Board for each available move (so you don't change the actual game), 
 * and trying the move out by passing it the cloned board's update function. 
 * The resulting position can be examined in an implementation of NodeInfo.
 * 
 * Try the new agent out against your reflex agent to see if it is more successful. 
 * The extra competency may be more apparent with some board sizes, configurations, 
 * and number of pieces than others, so you may wish to try a few.
 * 
 * What other one-step strategies can you think of? 
 * Note that you have access to the current pieces through board.getRedPieces(),
 * and board.getBlackPieces() methods. 
 * Try to develop the optimal one-step strategy/evalutation function. 
 * Try your proposals out by building a player and playing off against, 
 * the players provided and other student's players and see if they appear,
 * successful.
 *   
 */

/*
 * 
 */

public class EricMiniMax extends Player implements agent.Agent {

	CheXNodeInfo nodeInfo;
	Random random;
	
	public EricMiniMax(boolean isRed) {
		super(isRed, "Eric - MiniMax Agent");
		this.nodeInfo = new CheXNodeInfo(isRed, 2);
		this.random = new Random();
		
		System.out.println();
		if(this.isRed) System.out.println(this.name + " is red");
		if(!this.isRed) System.out.println(this.name + "is blue");
	}

	@Override
	public Action getAction(Percept cP) {
		 Board board = (Board) cP;
		 Moves fullMoveList = (Moves)board.getActions();
		 Minimax search = new Minimax(this.nodeInfo);
		 
		 
		 // Depending on which colour the agent is select all the relative pieces.
		 PieceSet currentPieceSet = board.getRedPieces();
		 if(this.isRed) {
		 } else {
			 currentPieceSet = board.getBlackPieces();
		 }
			 
		 // Get list of agents current pieces.
	     ArrayList<MoveValuePair> bestMoveValuePairs = new ArrayList<MoveValuePair>();
		 for(Iterator<Piece> piece  = currentPieceSet.iterator(); piece.hasNext(); ) {
			 
			 Piece currentPiece = piece.next();
			 // For each piece get its possible moves
			 for(Iterator<Move> moves = ((Moves)(board.getActions(currentPiece))).iterator(); moves.hasNext(); ) {
				 Move move = moves.next();
				 Board clone = (Board)board.clone();
				 clone.update(move);
				 // Update the board and send each updated board off to minimax for evaluation.
				 bestMoveValuePairs.add(new MoveValuePair(move, search.minValue(new Node(clone), Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY)));
				 // Reset the search list.
				 search.reset();
			 }
		 }
		 
		 
		 double best = Double.NEGATIVE_INFINITY;
		 // If for some reason the list of moves doesnt exist or something, a random move will be returned.
		 Move bestMove = (Move)fullMoveList.get(random.nextInt(fullMoveList.size()));
		 
		 // Find the move with the highest Utility.
		 for(Iterator<MoveValuePair> findBest = bestMoveValuePairs.iterator(); findBest.hasNext(); ) {
			 
			 MoveValuePair currentPair = findBest.next();
			 if(currentPair.getValue() > best) {
				 best = currentPair.getValue();
				 bestMove = currentPair.getMove();
			 }
		 }
	     return bestMove;
	}
	
	// Group subclass
	public class MoveValuePair {
		
		Move move;
		double value;
		
		public MoveValuePair(Move theMove, double theValue) {
			this.move = theMove;
			this.value = theValue;
		}
		
		public double getValue() {
			return this.value;
		}
		
		public Move getMove() {
			return this.move;
		}
	}

}
