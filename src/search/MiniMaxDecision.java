package search;
import agent.*;
import be.ac.ulg.montefiore.run.jadti.AttributeSet;
import be.ac.ulg.montefiore.run.jadti.AttributeValue;
import be.ac.ulg.montefiore.run.jadti.DecisionTree;
import be.ac.ulg.montefiore.run.jadti.Item;
import be.ac.ulg.montefiore.run.jadti.ItemSet;
import be.ac.ulg.montefiore.run.jadti.KnownSymbolicValue;
import be.ac.ulg.montefiore.run.jadti.SymbolicAttribute;

import game.Attributes;
import game.DataPieceAttributes;

import java.util.*;

import mixmeta4.Board;
import mixmeta4.Move;
import mixmeta4.Piece;
import mixmeta4.PieceSet;

/**
 * This file contains a template for getting started writing a minimax 
 * search algorithm. Before editing the class it should first be
 * written to a file called Minimax.java.
 * <p>
 * It requires an application specific NodeInfo object which tailors the
 * search to the particular application - see the NodeInfo documentation
 * for more details.
 * <p>
 * Note that the algorithm is recursive, with maxValue calling minValue,
 * and vice versa.
 *
 * @author Cara MacNish
 */

public class MiniMaxDecision {

  private NodeInfo nodeInfo;
  private ArrayList<Node> visited;
  private DecisionTree tree;
  private PieceSet black;
  private PieceSet white;
  private boolean debug = true;
  private Attributes atts;
   
  public MiniMaxDecision (NodeInfo nodeInfo, DecisionTree tree, PieceSet black, PieceSet white) {
	    this.nodeInfo = nodeInfo;
	    visited = new ArrayList<Node>();
	    this.tree = tree;
	    this.black = black;
	    this.white = white;
	    this.atts = new Attributes();
  }
  
  /**
   * Reset because I do a minimax search for each move and need to clear the visited array
   * otherwise I get a stack out of memory.
   */
  
  public void reset() {
	  visited.clear();
  }
  
  /**
   * The occurs check for the visited nodes.
   * @param node The node to be checked. 
   * @return true if the node has already been seen and false otherwise.
   */
  private boolean nodeExists(Node node) {
	  
	  int x = Collections.binarySearch(visited, node);
	  if(x >= 0) {
		  return true;
	  }
	  return false;
  }

  /**
   * function alphabeta(node, depth, α, β)         
   * (* β represents previous player best choice - doesn't want it if α would worsen it *)
   * if node is a terminal node or depth = 0
   *     return the heuristic value of node
   * foreach child of node
   *     α := max(α, -alphabeta(child, depth-1, -β, -α))     
   *     (* use symmetry, -β becomes subsequently pruned α *)
   *     if β≤α
   *         break                             (* Beta cut-off *)
   * return α
   *
   *	(* Initial call *)
   * alphabeta(origin, depth, -infinity, +infinity)
   * 
   */
  
  /**
   * @return the highest value Max can achieve at this node with optimal play
   */
  
  public double maxValue (Node visit, double alpha, double beta) {
    double maxSoFar = Double.NEGATIVE_INFINITY;
    ListIterator li;
    Action arc;
    Node child;
    double childValue;
    if (nodeInfo.isTerminal(visit)) {
    	return nodeInfo.utility(visit);
    }
    else {
      li = visit.getState().getActions().listIterator();
      while (li.hasNext()) {
    	  child = (Node)visit.clone();
    	  arc = (Action)li.next();
    	  child.update(arc);
    	 
   // 	  if(!nodeExists(child)) {
    //		  child.setUtility(nodeInfo.utility(child));
    //		  this.visited.add(child);
    //		  Collections.sort(this.visited);
    		  alpha = Math.max(alpha, minValue(child, alpha, beta));
    		  if(alpha >= beta) {
    			  return beta;
    		  }
	//		  childValue = alpha;
	//		  if(childValue > maxSoFar) {
	//			  maxSoFar = childValue;
	//		  }
    //	  }
      }
      return alpha;
    }
  }

  /**
   * @return the lowest value Min can achieve at this node with optimal play.
   */
  public double minValue (Node visit, double alpha, double beta) {
    double minSoFar = Double.POSITIVE_INFINITY;
    ListIterator li;
    Action arc;
    Node child;
    double childValue;
    if (nodeInfo.isTerminal(visit)) {
    	return nodeInfo.utility(visit);
    }
    else {
      li = visit.getState().getActions().listIterator();
      while (li.hasNext()) {
    	  child = (Node)visit.clone();
    	  arc = (Action)li.next();
    	  this.decideOnMove((Move)arc, (Board)child.getState());
    	  child.update(arc);
    	  
    	//  if(!nodeExists(child)) {
    	//	  child.setUtility(nodeInfo.utility(child));
    	//	  this.visited.add(child);
    	//	  Collections.sort(this.visited);
    		  beta = Math.min(beta, maxValue(child, alpha, beta));
    		  if(beta <= alpha) {
    			  return alpha;
    		  }
	//		  childValue = beta;
	  //  	  if(childValue < minSoFar) {
	  //  		  minSoFar = childValue;
	    //	  }
    	//  }
      }
      return beta;
    }

    	
  }
  
  private boolean decideOnMove(Move move, Board board) {
	  Board newBoard = (Board)board.clone();
	  ListIterator<Piece> defaultset;
	  ListIterator<Piece> pieces;
	  newBoard.update(move);
	  
	  ArrayList<KnownSymbolicValue> askData = new ArrayList<KnownSymbolicValue>(50); 
	  ItemSet testSet = new ItemSet(this.tree.getAttributeSet());
	  if(!newBoard.redToMove) {
		  defaultset = this.black.listIterator();
		  pieces = newBoard.getBlackPieces().listIterator();
	  } else {
		  defaultset = this.white.listIterator();
		  pieces = newBoard.getRedPieces().listIterator();
	  }
	  boolean doOther = true;
	  if(pieces.hasNext()) {
		  Piece piece = pieces.next();
		  if(debug) System.out.println("Fill the data for each of the pieces loop");
		  while(defaultset.hasNext()) {
//			  DataPieceAttributes dpa = new DataPieceAttributes();

			  Piece defaultPiece = defaultset.next();

			  if(piece.toString().compareTo(defaultPiece.toString()) != 0) {
//				  dpa.setPiece(defaultPiece);

//				  dpa.addAttribute("canBeTaken");
				  
				  askData.add(new KnownSymbolicValue(booleanToInteger(false)));

//				  dpa.addAttribute("canMove");
				  askData.add(new KnownSymbolicValue(booleanToInteger(false)));

//				  dpa.addAttribute("canTake");
				  askData.add(new KnownSymbolicValue(booleanToInteger(false)));

//				  dpa.addAttribute("takeKing");
				  askData.add(new KnownSymbolicValue(booleanToInteger(false)));
//				  datum.addPieceAttributes(dpa);
				  if(pieces.hasNext()) {
					  piece = pieces.next();
				  }
				  //defaultPiece = defaultset.next();
				  doOther = false;

			  }
			  if(doOther) {
//				  dpa.setPiece(piece);

//				  dpa.addAttribute("canBeTaken");
				  askData.add(new KnownSymbolicValue(booleanToInteger(atts.canBeTaken(newBoard, piece))));

//				  dpa.addAttribute("canMove");
				  askData.add(new KnownSymbolicValue(booleanToInteger(atts.canMove(newBoard, piece))));

//				  dpa.addAttribute("canTake");
				  askData.add(new KnownSymbolicValue(booleanToInteger(atts.canTake(newBoard, piece))));

//				  dpa.addAttribute("takeKing");
				  askData.add(new KnownSymbolicValue(booleanToInteger(atts.takeKing(newBoard))));
//				  datum.addPieceAttributes(dpa);
				  if(pieces.hasNext()) {
					  piece = pieces.next();
				  }
				  //defaultPiece = defaultset.next();

			  }
			  doOther = true;
		  }
	  }
	  
	  ListIterator<KnownSymbolicValue> ita = askData.listIterator();
	  KnownSymbolicValue[] ksvArray = new KnownSymbolicValue[askData.size()+2];
	  int o = 1;
	  while(ita.hasNext()) {
		  ksvArray[o] = ita.next();
		  o++;
	  }
	  Item item = new Item(ksvArray);
	  
	  AttributeSet itemAttributes = tree.getAttributeSet();
	  SymbolicAttribute goalAttribute = tree.getGoalAttribute();
		
	  KnownSymbolicValue goalAttributeValue = 
		    (KnownSymbolicValue) item.valueOf(itemAttributes, goalAttribute);
	  KnownSymbolicValue guessedGoalAttributeValue = 
		    tree.guessGoalAttribute(item);
	  
//	  data.add(datum);
	  
	  System.out.println(tree.getGoalAttribute().valueToString(guessedGoalAttributeValue));
	  return true;

  }

//  testSet.add(arg0)

  private int booleanToInteger(boolean duh) {
	  if(duh) {
		  return 1;
	  }
	  return 0;
  }




}


