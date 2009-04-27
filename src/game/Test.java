package game;

import game.Run.MoveValuePair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import bb.util.*;

import mixmeta4.Game;
import mixmeta4.Player;
import samplePlayers.Marvin;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
	    Callable<Integer> task = new Callable<Integer>() {
	    	public Integer call() {
	    		
	    	}
	    };
	    
	    System.out.println("CheX Game: " + new Benchmark(task));

	}

	public int testFill() {
		List<MoveValuePair> list1 = new LinkedList<MoveValuePair>();
		List<MoveValuePair> list2 = new ArrayList<MoveValuePair>();
		
		Random random = new Random();
		double dbl = 0;
		int nItems = 10000; // Put Ten thousand items in.
		for(int z = 0; z < nItems; z++) {
			dbl = random.nextDouble();
			list1.add(new MoveValuePair(null, dbl));
			list2.add(new MoveValuePair(null, dbl));
		}
	}
}
