package search;
import agent.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.googlecode.charts4j.AxisLabels;
import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.AxisStyle;
import com.googlecode.charts4j.AxisTextAlignment;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.DataUtil;
import com.googlecode.charts4j.Fills;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.Line;
import com.googlecode.charts4j.LineChart;
import com.googlecode.charts4j.LineStyle;
import com.googlecode.charts4j.Plots;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.Shape;

//
//  GeneralSearch.skeleton
//  javaAgents
//
//  Created by Cara MacNish on 28/07/05.
//  Copyright (c) 2005 CSSE, UWA. All rights reserved.
//


public abstract class GeneralSearch {

	NodeInfo nodeInfo;
	ArrayList<Node> unvisited;
	ArrayList<Node> visited;
	
	double highestUtility;
	double highestGoalUtility;
	Node bestNodeSoFar;
	Node bestGoalSoFar;
	
	
	public GeneralSearch (State startState, NodeInfo nodeInfo) {
		this.nodeInfo = nodeInfo;
		unvisited = new ArrayList<Node>();
		unvisited.add(new Node(startState, new Actions()));
		visited = new ArrayList<Node>();
		this.highestUtility = Double.NEGATIVE_INFINITY;
		this.bestNodeSoFar = new Node(startState, new Actions());
		
	}

	
	
	/*
	 * The body of this class has been provided for you with,
	 * the exception of the search() method, which you are required,
	 * to complete so that it implements the General Search Algorithm,
	 * discussed in the Lecture Notes. 
	 * You need not include an occurs check at this stage, 
	 * but should check the terminal condition in this method.
	 * 
	 * If the search completes successfully the method should return,
	 * the goal node found (which includes the path, or "plan",
	 * to reach that goal) otherwise it should return null.
	 * 
	 * The remaining methods select() and insert(Node node) should,
	 * be defined in any implementing subclass. It is these methods that,
	 * tailor the General Search to a particular search strategy. 
	 * isTerminal(Node node) should be defined in the NodeInfo instance. 
	 * 
	 * -1. If U is empty halt and report no goal found.
	 * -2. Select, according to some (as yet undefined) strategy , a
	 * 	node s from U .
	 * -3. (Optional) If s ∈ V discard s and repeat from 1.
	 * -4. If g(s) = true halt and report goal found.
	 * -5. (Optional) If t(s) = true discard s and repeat from 1.
	 * 6. Otherwise move s to the set V , and add to U all the
	 *    nodes reachable from s. Repeat from 1.
	 * 
	 */
	
	private boolean isVisited(Node cur) {
		if(!this.visited.isEmpty() && cur != null) {
			for(int x = 0; x < this.visited.size(); x++) {
				if(this.visited.get(x).getState().toString().compareTo(cur.getState().toString()) == 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	public Node search() {
		
		Node current = null;
		
		while(!this.unvisited.isEmpty()) { 
			System.out.println("Selecting node from unvisited...");
			current = select();
			System.out.println("Node selected!");
			if(current == null) break;
			
			System.out.println("Is this nodes utility higher than previously seen?");
			if(current.getUtility() > this.highestGoalUtility) {
				System.out.println("Yes, its the new best option!");
				this.highestGoalUtility = current.getUtility();
				this.bestGoalSoFar = (Node)current.clone();
			}
			
			System.out.println("Is this the goal?");
			if(nodeInfo.isGoal(current)) {
				System.out.println("Yes!");
				return current;
			} else
				System.out.println("No!");
				System.out.println("Is this Node terminal?");
				if(!nodeInfo.isTerminal(current)) {
				System.out.println("No!, adding its children");
				insert(current);
			}
		}
		return this.bestGoalSoFar;
	}

	public abstract Node select ();

	public abstract void insert (Node node);

}
