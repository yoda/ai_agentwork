package game;

import game.Run.MoveValuePair;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

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
import mixmeta4.Square;

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

public class EricMiniMax extends Player implements agent.Agent, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CheXNodeInfo nodeInfo;
	Random random;
	double ratio;
	String filename; 
	DataWriter writer; 
	
	public EricMiniMax(boolean isRed) {
		super(isRed, "Eric - MiniMax Agent");
		filename = "testfile";
		writer = new DataWriter(filename, filename + "dir");
		this.nodeInfo = new CheXNodeInfo(isRed, 2);
		this.random = new Random();
		ratio = 0.5; // There is a 100 % 30 chance that the agent might not (50%) pick the optimum move, instead second (if it exists).
		System.out.println();
		if(this.isRed) System.out.println(this.name + " is red");
		if(!this.isRed) System.out.println(this.name + "is blue");
	}
	

	@Override
	public Action getAction(Percept cP) {
		 Board board = (Board) cP;
		 Board board2 = (Board)board.clone();
		 System.out.println("Black time: " + board.game.getBlackTime());
		 System.out.println("Red time: " + board.game.getRedTime());
		 if(!nodeInfo.isRed) {
			 if(board.game.getBlackTime() > 66000) {
				 //this.nodeInfo.setDepthLimit(4);
			 } else {
				 this.nodeInfo.setDepthLimit(2);
			 }
				 
		 } else {
			 if(board.game.getRedTime() > 66000) {
				 //this.nodeInfo.setDepthLimit(4);
			 } else {
				 this.nodeInfo.setDepthLimit(2);
			 }
		 }
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
		 ListIterator<Piece> piece  = currentPieceSet.listIterator();
		 while(piece.hasNext()) {
			 
			 Piece currentPiece = piece.next();
			 // For each piece get its possible moves
			 ListIterator<Move> moves = ((Moves)(board.getActions(currentPiece))).listIterator();
			 while(moves.hasNext()) {
				 Move move = moves.next();
				 Board clone = (Board)board.clone();
				 clone.update(move);
				 // Update the board and send each updated board off to minimax for evaluation.
				 bestMoveValuePairs.add(new MoveValuePair(move, search.minValue(new Node(clone), Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY)));
				 // Reset the search list.
				// search.reset();
			 }
		 }
		 
		 
		 double best = Double.NEGATIVE_INFINITY;
		 // If for some reason the list of moves doesnt exist or something, a random move will be returned.
		 Move bestMove = (Move)fullMoveList.get(random.nextInt(fullMoveList.size()));
		 
		 // Find the move with the highest Utility.
		 Collections.sort(bestMoveValuePairs);
		 int index = 0;
		 if(random.nextInt((int)(100 * ratio)) == 0 && bestMoveValuePairs.size() > 1) {
			 System.out.println("Might be random here...");
			 index = random.nextInt(2);
		 }
		 
		 bestMove = bestMoveValuePairs.get(index).getMove();
	//	 System.out.println("Move utility: " + bestMoveValuePairs.get(index).getValue());
	//	 System.out.println("Doing experiment: ");
	//	 experiment(board);
		 board2.update(bestMove);
		 DataContainer temp = new DataContainer((Board)cP);
		 DataContainer temp2 = new DataContainer(board2);
		 
		 if(writer.writeData(temp)) {
			 System.out.println("Successfully written!");
			 writer.closeFile();
		 }
		 if(writer.writeData(temp2)) {
			 System.out.println("Successfully written!");
			 writer.closeFile();
		 }
		 
		 
	     return bestMove;
	}
	
	private void experiment(Board theboard) {
		Square[][] squareBoard = theboard.squares;
		String row = "";
		String col = "\n";
		for(int y = 0; y < squareBoard[0].length; y++) {
			for(int x = 0; x < squareBoard.length; x++) {
				if(squareBoard[x][y] != null) {
					row += squareBoard[x][y];
				} else
					row += "   ";
				
			}
			row += col;
		}
		System.out.println(row);
		for(Iterator<Piece> it = theboard.getRedPieces().iterator(); it.hasNext(); ) {
			Piece thepiece = it.next();
			arroundPiece(thepiece);
			System.out.println();
//			try {
//				System.out.println("Piece: " + thepiece.toString() + " ThreatValue: " + checkPiece(theboard, thepiece));
//			} catch (SAXException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (JAXBException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
	
	private void arroundPiece(Piece thePiece) {
		String block = "";
		
		block += thePiece.square.neighbour(new Point(-1,1));
		block += thePiece.square.neighbour(new Point(0,1));
		block += thePiece.square.neighbour(new Point(1,1));
		
		block += "\n";
		
		block += thePiece.square.neighbour(new Point(-1,0));
		block += thePiece.square;
		block += thePiece.square.neighbour(new Point(1,0));
		
		block += "\n";
		
		block += thePiece.square.neighbour(new Point(-1,-1));
		block += thePiece.square.neighbour(new Point(0,-1));
		block += thePiece.square.neighbour(new Point(1,-1));
		
		System.out.println(block);
	}
	
	private int checkPiece(Board theBoard, Piece thePiece) throws SAXException, JAXBException {
		Moves pieceMoves = (Moves)theBoard.getActions(thePiece);
		int value = 0;
		for(Iterator<Move> it = pieceMoves.iterator(); it.hasNext(); ) {
			Move theMove = it.next();
			if(theBoard.getSquare(theMove.getDestination()).isOccupiedByOpponent(theBoard.redToMove)) {
				if(threatCheck(theBoard.getSquare(theMove.getDestination()), theBoard) != 0) {
					value += nodeInfo.getPieceValue(theBoard.getSquare(theMove.getDestination()).look());
				}		
			}
		}
		return value;
	}
	
	private int threatCheck(Square destination, Board theBoard) throws SAXException, JAXBException {
		Square squareBoard[][] = theBoard.squares;
		
		int threat = 10;
		int value = 0;
		int col = destination.column;
		int startcol = 0;
		int endcol = theBoard.numColumns;
		int row = destination.row;
		int startrow = 0;
		int endrow = theBoard.numRows;
		if(col > 2) {
			startcol = col - 2;
		}
		if(col < theBoard.numColumns - 2) {
			endcol = col + 2;
		}
		if(row > 2) {
			startrow = row - 2;
		}
		if(row < theBoard.numRows - 2) {
			endrow = row + 2;
		}
		
		for(int y = startcol; y < endcol; y++) {
			for(int x = startrow; x < endrow; x++) {
				if(squareBoard[y][x].isOccupiedByOpponent(theBoard.redToMove)) {
					Piece possibleThreat = squareBoard[y][x].look();
					Moves possibleThreatMoves = (Moves)theBoard.getActions(possibleThreat);
					for(Iterator<Move> it = possibleThreatMoves.iterator(); it.hasNext(); ) {
						Move theMove = it.next();
						if(theBoard.getSquare(theMove.getDestination()).isOccupiedByOpponent(!theBoard.redToMove)) {
							value += threat;
						}
					}
				}
			}
		}
		return value;
		
	}
	
	// Group subclass
	public class MoveValuePair implements Comparable<MoveValuePair>, Comparator<MoveValuePair> {
		
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
		
		public String toString() {
			String result = "";
			if(this.move != null) {
				result += getMove().toString();
				result += " : ";
				result += getValue();
				return result;
			}
			result += "null : ";
			result += getValue();
			return result;
		}

		@Override
		public int compare(MoveValuePair first, MoveValuePair second) {
						
			if(first.getValue() == second.getValue()) {
				return 0;
			}
			if(first.getValue() > second.getValue()) {
				return -1;
			}
			
			return 1;
		}

		@Override
		public int compareTo(MoveValuePair second) {
			if(this.getValue() == second.getValue()) {
				return 0;
			}
			if(this.getValue() > second.getValue()) {
				return -1;
			}
			
			return 1;
		}
		
	}

}
