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
	ArrayList nodeTimes;
	int maxstored;
	int solutionlength;
	int numtimes;
	int totalnodes;
	Long totaltime;
	
	public IterativeDeepeningSearch(State startState, NodeInfo nodeInfo) {
		 this.nodeInfo = nodeInfo;
		 this.state = startState;
		 this.depth = 0;
		 subSearch = new DepthLimitedSearch(this.state, this.nodeInfo, this.depth);
		 this.nodeTimes = new ArrayList();
		 this.totaltime = new Long(0);
	}
	
	private void updateStats() {
		this.maxstored = this.subSearch.maxstored;
		for(int x = 0; x < this.subSearch.nodeTimes.size(); x++) {
			this.nodeTimes.add(this.subSearch.nodeTimes.get(x));
		}
		this.solutionlength = this.subSearch.solutionlength;
		this.totalnodes += this.subSearch.totalnodes;
		this.totaltime = this.subSearch.totaltime + this.totaltime;
	}
	
	public Node search() {
		Node result = null;
		while(result == null) {
			result = this.subSearch.search();
			this.depth++;
			updateStats();
			if(result == null) { 
				this.subSearch = new DepthLimitedSearch(this.state, this.nodeInfo, this.depth);
			}
		}
		
		
		return result;
	}
	
	public void writeData() {
		Data theData;
		Long maxvalue = new Long(0);
		Long minvalue = new Long(999999);
	    
	    
	     
	    
	    for(int x = 0; x < this.nodeTimes.size(); x++) {
	    	if((Long)this.nodeTimes.get(x) > maxvalue) {
	    		maxvalue = (Long)this.nodeTimes.get(x);
	    	}
	    	if((Long)this.nodeTimes.get(x) < minvalue) {
	    		minvalue = (Long)this.nodeTimes.get(x);
	    	}
	    }
	    
	    theData = DataUtil.scale(this.nodeTimes);
	    
	    System.out.println("Sample size: " + this.numtimes);
	    System.out.println("Scaled sample size: " + theData.getSize());
	    
	    
	    Line line = Plots.newLine(DataUtil.scale(this.nodeTimes), Color.BLACK, "Insert()");

	    line.setLineStyle(LineStyle.newLineStyle(1, 1, 0));
        line.setFillAreaColor(Color.LIGHTGREEN);

        AxisStyle axisStyle = AxisStyle.newAxisStyle(Color.WHITE, 12, AxisTextAlignment.CENTER);
        AxisLabels yAxis1 = AxisLabelsFactory.newNumericRangeAxisLabels(minvalue.intValue(), maxvalue.intValue());
        yAxis1.setAxisStyle(axisStyle);
        
        AxisLabels yAxis2 = AxisLabelsFactory.newAxisLabels("Time(ms)", 50.0);
        yAxis2.setAxisStyle(AxisStyle.newAxisStyle(Color.WHITE, 14, AxisTextAlignment.CENTER));
        
        AxisLabels xAxis1 = AxisLabelsFactory.newNumericRangeAxisLabels(0, theData.getSize());
        xAxis1.setAxisStyle(axisStyle);

        AxisLabels xAxis2 = AxisLabelsFactory.newAxisLabels("Node Expansions", 50.0);
        xAxis2.setAxisStyle(AxisStyle.newAxisStyle(Color.WHITE, 14, AxisTextAlignment.CENTER));

	    
	    LineChart chart = GCharts.newLineChart(line);

	    chart.addYAxisLabels(yAxis1);
	    chart.addYAxisLabels(yAxis2);
        chart.addXAxisLabels(xAxis1);
        chart.addXAxisLabels(xAxis2);
        chart.setGrid(100, 6.78, 5, 0);

	    chart.setSize(600, 500);
        chart.setTitle(this.toString() + " search for: " + this.nodeInfo.toString(), Color.WHITE, 14);
        chart.setBackgroundFill(Fills.newSolidFill(Color.BLACK));
        chart.setAreaFill(Fills.newSolidFill(Color.newColor("708090")));

		
		String snodetimes = "";
		String maxstore = "";
		String sollen = "";
		for(int x = 0; x < this.nodeTimes.size(); x++) {
			snodetimes += ((Long)this.nodeTimes.get(x)).toString() + ",";
		}
		maxstore = String.valueOf(this.maxstored);
		sollen = String.valueOf(this.solutionlength);
		String html = "";
		
		html += "<html>\n";
		html += "<body style=\"background-color:#000\">\n";
		html += "<img src=\"" + chart.toURLForHTML() + "\" />\n";
		html += "<p style=\"color:#fff\">" + "Total time elapsed: " + this.totaltime.toString() + "ms" + "</p>\n";
		html += "<p style=\"color:#fff\">" + "Max stored nodes: " + this.maxstored + "" + "</p>\n";
		html += "<p style=\"color:#fff\">" + "Total nodes searched: " + this.totalnodes + "" + "</p>\n";
		html += "<p style=\"color:#fff\">" + "Solution operations: " + this.solutionlength + "" + "</p>\n";
		html += "</body>\n";
		html += "</html>\n";
		
		try {
	        BufferedWriter out = new BufferedWriter(new FileWriter(this.toString() + "." + this.nodeInfo.toString() + ".txt"));
	        out.write(snodetimes + '\n' + maxstore + '\n' + sollen + '\n' + chart.toURLString() + '\n');
	        out.close();
	        BufferedWriter out2 = new BufferedWriter(new FileWriter(this.toString() + "." + this.nodeInfo.toString() + ".html"));
	        out2.write(html);
	        out2.close();
	    } catch (IOException e) {
	    	System.out.println("Fail!");
	    	System.exit(0);
	    }
	}
}
