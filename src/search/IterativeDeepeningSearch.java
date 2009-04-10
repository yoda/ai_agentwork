package search;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.googlecode.charts4j.AxisLabels;
import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.AxisStyle;
import com.googlecode.charts4j.AxisTextAlignment;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.DataUtil;
import com.googlecode.charts4j.Fills;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.Line;
import com.googlecode.charts4j.LineChart;
import com.googlecode.charts4j.LineStyle;
import com.googlecode.charts4j.Plots;

public class IterativeDeepeningSearch {
	
	private NodeInfo nodeInfo;
	private State state;
	private DepthLimitedSearch subSearch; 
	private double depth;
	
	public IterativeDeepeningSearch(State startState, NodeInfo nodeInfo) {
		 this.nodeInfo = nodeInfo;
		 this.state = startState;
		 this.depth = 0;
		 subSearch = new DepthLimitedSearch(this.state, this.nodeInfo, this.depth);
	}
	
	
	public Node search() {
		Node result = null;
		while(result == null) {
			result = this.subSearch.search();
			this.depth++;
			if(result == null) { 
				this.subSearch = new DepthLimitedSearch(this.state, this.nodeInfo, this.depth);
			}
		}
		
		
		return result;
	}
}
