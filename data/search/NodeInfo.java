package search;

/**
 * Searches will use NodeInfo objects to gain application-specific
 * information about a node needed to carry out the search. The search
 * may need to know if it is a goal node, whether it satisfies conditions
 * that allow that path to be terminated, and what the utility or
 * estimated utility of the node is.
 * <p>
 * Since it is application specific the implementation of NodeInfo should 
 * reside with the agent or application domain classes and not in the 
 * search package.
 * <p>
 * Not all searches will use all methods of NodeInfo, in which case dummy
 * values can be returned.
 *
 * @author Cara MacNish
 */

public interface NodeInfo {

  /**
   * @return true if this node is a goal node.
   */
  public boolean isGoal (Node node);


  /**
   * @return true in cases where the search should terminate
   */
  public boolean isTerminal (Node node);


  /**
   * @return the utility or estimated utility (evalutation function) of the node
   */ 
  public double utility (Node node);
  
  /**
   * Set the depth/cost limit for depth limited and iterative deepending searches.
   * @param limit the depth limit
   */
  public void setDepthLimit (double limit);

  /**
   * Get the depth/cost limit for depth limited and iterative deepening searches.
   * @return the depth limit
   */
  public double getDepthLimit ();
}
 
