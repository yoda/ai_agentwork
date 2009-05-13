package search;
import agent.*;
import be.ac.ulg.montefiore.run.jadti.DecisionTree;
import be.ac.ulg.montefiore.run.jadti.ItemSet;

import game.DataPieceAttributes;

import java.util.*;

import mixmeta4.Board;
import mixmeta4.Move;
import mixmeta4.Piece;

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

public class Minimax {

  NodeInfo nodeInfo;
  ArrayList<Node> visited;
  private DecisionTree tree;

  public Minimax (NodeInfo nodeInfo) {
    this.nodeInfo = nodeInfo;
    visited = new ArrayList<Node>();
  }
  
  public Minimax (NodeInfo nodeInfo, DecisionTree tree) {
	    this.nodeInfo = nodeInfo;
	    visited = new ArrayList<Node>();
	    this.tree = tree;
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
	  
	  newBoard.update(move);
	  ItemSet testSet = new ItemSet(this.tree.getAttributeSet());
	  ListIterator<Piece> defaultset = black.listIterator();
		boolean doOther = true;
		if(pieces.hasNext()) {
			Piece piece = pieces.next();
			if(debug) System.out.println("Fill the data for each of the pieces loop");
			while(defaultset.hasNext()) {
				DataPieceAttributes dpa = new DataPieceAttributes();

				Piece defaultPiece = defaultset.next();

				if(piece.toString().compareTo(defaultPiece.toString()) != 0) {
					dpa.setPiece(defaultPiece);

					dpa.addAttribute("canBeTaken");
					dpa.addResult(false);

					dpa.addAttribute("canMove");
					dpa.addResult(false);

					dpa.addAttribute("canTake");
					dpa.addResult(false);

					dpa.addAttribute("takeKing");
					dpa.addResult(false);
					datum.addPieceAttributes(dpa);
					if(pieces.hasNext()) {
						piece = pieces.next();
					}
					//defaultPiece = defaultset.next();
					doOther = false;

				}
				if(doOther) {
					dpa.setPiece(piece);

					dpa.addAttribute("canBeTaken");
					dpa.addResult(atts.canBeTaken(board.getTheBoard(), piece));

					dpa.addAttribute("canMove");
					dpa.addResult(atts.canMove(board.getTheBoard(), piece));

					dpa.addAttribute("canTake");
					dpa.addResult(atts.canTake(board.getTheBoard(), piece));

					dpa.addAttribute("takeKing");
					dpa.addResult(atts.takeKing(board.getTheBoard()));
					datum.addPieceAttributes(dpa);
					if(pieces.hasNext()) {
						piece = pieces.next();
					}
					//defaultPiece = defaultset.next();
					
				}
				doOther = true;
			}
			if(debug) System.out.println("Number of pieces to search: " + black.size());
			if(debug) System.out.println("Number of piece data sets in DataAtom: " + datum.getPieceAttributesSet().size());
		}
		
		data.add(datum);
		

	}

	  testSet.add(arg0)
	  
	  
	  
	  
	  
	  
  }

}
