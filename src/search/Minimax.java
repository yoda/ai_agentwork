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
  ArrayList visited;

  public Minimax (NodeInfo nodeInfo) {
    this.nodeInfo = nodeInfo;
    visited = new ArrayList();
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
    	  childValue = minValue(child);
    	  if(child.getUtility() > maxSoFar) {
    		  maxSoFar = child.getUtility();
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
    	  childValue = maxValue(child);
    	  if(child.getUtility() < minSoFar) {
    		  minSoFar = child.getUtility();
    	  }
      }
      return minSoFar;
    }


  }

}
