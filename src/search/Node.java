//
//  Node.java
//  javaAgents
//
//  Created by Cara MacNish on 28/07/05.
//  Copyright (c) 2005 CSSE, UWA. All rights reserved.
//

package search;
import agent.*;

/**
 * Note: direct access to instance variables is deprecated. Use get/set methods.
 */
public class Node implements Comparable<Node> {

  public State state;
  public Actions path;
  public double cost;
  public double utility;  // provided for efficiency only

  public Node (State state) {
    this.state = state;
    this.path = new Actions();
    this.cost = 0;
    this.utility = 0;
  }

  public Node (State state, Actions path) {
    this.state = state;
    this.path = path;
    this.cost = 0;
    this.utility = 0;
  }

  public Node (State state, Actions path, double cost, double utility) {
    this.state = state;
    this.path = path;
    this.cost = cost;
    this.utility = utility;
  }

  public Object clone () {
    return new Node((State) state.clone(), (Actions) path.clone(), cost, utility);
  }

  public void update (Action action) {
    state.update(action);
    path.add(action);
    cost = cost + action.getCost();
  }
  
  public State getState () {return state;}  
  public void setState (State state) {this.state = state;}

  public Actions getPath () {return path;}  
  public void setPath (Actions path) {this.path = path;}

  public double getCost () {return cost;}  
  public void setCost (double cost) {this.cost = cost;}

  public double getUtility () {return utility;}  
  public void setUtility (double utility) {this.utility = utility;}

	@Override
	public int compareTo(Node second) {
		if(this.getUtility() == second.getUtility() && this.getState().equals(second.getState())) {
			return 0;
		}
		if(this.getUtility() > second.getUtility()) {
			return -1;
		}
		
		return 1;
	}

}
