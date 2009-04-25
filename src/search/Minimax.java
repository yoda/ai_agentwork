package search;
import agent.*;
import java.util.*;

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

  public Minimax (NodeInfo nodeInfo) {
    this.nodeInfo = nodeInfo;
    visited = new ArrayList<Node>();
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
	  
	  for(Iterator<Node> nodes = visited.iterator(); nodes.hasNext(); ) {
		  if(node.getState().equals(nodes.next())) {
			  return true;
		  }
		  
	  }
	  return false;
  }

  /**
   * @return the highest value Max can achieve at this node with optimal play
   */
  
  public double maxValue (Node visit) {
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
    	  if(!nodeExists(child)) {
    		  this.visited.add(child);
			  childValue = minValue(child);
			  if(childValue > maxSoFar) {
				  maxSoFar = childValue;
			  }
    	  }
      }
      return maxSoFar;
    }
  }

  /**
   * @return the lowest value Min can achieve at this node with optimal play
   */
  public double minValue (Node visit) {
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
    	  if(!nodeExists(child)) {
    		  this.visited.add(child);
	    	  childValue = maxValue(child);
	    	  if(childValue < minSoFar) {
	    		  
	    		  minSoFar = childValue;
	    	  }
    	  }
      }
      return minSoFar;
    }


  }

}
