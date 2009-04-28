package game;

import game.MoveValuePair;

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
		
	    Callable<Integer> task1 = new Callable<Integer>() {
	    	public Integer call() {
	    		return testFillLL();
	    	}
	    };
	    
	    Callable<Integer> task2 = new Callable<Integer>() {
	    	public Integer call() {
	    		return testFillAL();
	    	}
	    };
	    
	    System.out.println("Benchmark LinkedList Fill: " + new Benchmark(task1));
	    System.out.println("Benchmark ArrayList Fill: " + new Benchmark(task2));

	}

	public static int testFillLL() {
		List<MoveValuePair> list1 = new LinkedList<MoveValuePair>();
		
		
		Random random = new Random();
		double dbl = 0;
		int nItems = 10000; // Put Ten thousand items in.
		for(int z = 0; z < nItems; z++) {
			dbl = random.nextDouble();
			list1.add(new MoveValuePair(null, dbl));
		
		}
		return 1;
	}
	
	public static int testFillAL() {
		
		List<MoveValuePair> list2 = new ArrayList<MoveValuePair>();
		
		Random random = new Random();
		double dbl = 0;
		int nItems = 10000; // Put Ten thousand items in.
		for(int z = 0; z < nItems; z++) {
			dbl = random.nextDouble();
		
			list2.add(new MoveValuePair(null, dbl));
		}
		return 1;
	}
}
