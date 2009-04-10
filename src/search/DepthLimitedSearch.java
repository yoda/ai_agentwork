package search;

import java.util.ArrayList;

import agent.Action;
import agent.Actions;

public class DepthLimitedSearch extends GeneralSearch {

	
	public DepthLimitedSearch(State startState, NodeInfo nodeInfo, double depth) {
		super(startState, nodeInfo);
		this.nodeInfo.setDepthLimit(depth);
		
	}
	
	private boolean isVisited(Node cur) {
		boolean isinvisited = false;
		
		if(!this.visited.isEmpty() && cur != null) {
			for(int x = 0; x < this.visited.size(); x++) {
				if(this.visited.get(x).getState().toString().compareTo(cur.getState().toString()) == 0) {
					isinvisited = true;
				}
			}
		}
				
		return isinvisited;
	}
	
	@Override
	public void insert(Node node) {
		
		Actions children = node.getState().getActions();
			
		
		String result = "";

		if(!children.isEmpty()) {
			
			for(int z = 0; z < children.size(); z++) {
				Node clone = ((Node)node.clone());
				clone.update((Action)children.get(z));
				if(!isVisited(clone)) {
					this.unvisited.add(0,clone);
					result += " : " + clone.getState().toString();
				}
			}
			System.out.println("Adding" + result);
		}
		if(children.isEmpty()) System.out.println("Loop of death!");
		

	}

	@Override
	public Node select() {
		
		Node current = null;
		Node next = null;
		if(this.unvisited.size() > 2) {
			current = this.unvisited.get(0);
			next = this.unvisited.get(1);
			next.setUtility(next.getUtility() + nodeInfo.utility(next));
			current.setUtility(current.getUtility() + nodeInfo.utility(current));
			if(next.getUtility() > current.getUtility()) {
				this.visited.add(this.unvisited.remove(0));
				return next;
			}
			this.visited.add(this.unvisited.remove(0));
			return current;
		}
		if(!this.unvisited.isEmpty()) {
			current = this.unvisited.get(0);
			this.visited.add(this.unvisited.remove(0));
			return current;
		}
		
		return current;
	}
	
	public String toString() {
		return "DepthLimitedSearch";
	}
}
