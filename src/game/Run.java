package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import com.sun.xml.internal.bind.v2.runtime.reflect.ListIterator;

import mixmeta4.Board;
import mixmeta4.Move;
import mixmeta4.Queen;
import player.*;
import sun.misc.Sort;

public class Run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
<<<<<<< HEAD:src/game/Run.java
		
		Run run = new Run();
		
		Queen tempQueen = new Queen(false, false);
		
		System.out.println(tempQueen.toString());
		
		Board temp1 = new Board();
		Board temp2 = new Board();
		
		System.out.printf("Both the same: %b\n", temp1.equals(temp2));
		
		
		System.out.println("Sort Experimenting...");
		
		List<MoveValuePair> list1 = new LinkedList<MoveValuePair>();
		List<MoveValuePair> list2 = new ArrayList<MoveValuePair>();
		
		Random random = new Random();
		double dbl = 0;
		int nItems = 10000; // Put Ten thousand items in.
		for(int z = 0; z < nItems; z++) {
			dbl = random.nextDouble();
			list1.add(run.new MoveValuePair(null, dbl));
			list2.add(run.new MoveValuePair(null, dbl));
		}
		list1.add(run.new MoveValuePair(null, 2.40));
		
		int x = 0;
		long l1finish;
		long l1start = System.currentTimeMillis();
		
		Collections.sort(list1);
		
		l1finish = System.currentTimeMillis();
		
		for(java.util.ListIterator<MoveValuePair> it = list1.listIterator(); it.hasNext(); ) {
			x++;
			System.out.println(x + " " + it.next().toString());
		}
		
		x = 0;
		long l2finish;
		long l2start = System.currentTimeMillis();
		
		Collections.sort(list2);
		
		l2finish = System.currentTimeMillis();
		
		for(java.util.ListIterator<MoveValuePair> it = list1.listIterator(); it.hasNext(); ) {
			x++;
			System.out.println(x + " " + it.next().toString());
		}
		
		System.out.println(list1.getClass().toString() + " sorted in: " + (l1finish - l1start) + "ms");
		System.out.println(list2.getClass().toString() + " sorted in: " + (l2finish - l2start) + "ms");
		int q = Collections.binarySearch(list1, run.new MoveValuePair(null, 2.40));
		int r = Collections.binarySearch(list1, run.new MoveValuePair(null, 2.41));
		System.out.println(q);
		System.out.println(r);
		System.out.println(((int)(100 * 0.5)));
	}
	
	public class MoveValuePair implements Comparable<MoveValuePair>, Comparator<MoveValuePair> {
		
		Move move;
		double value;
		
		public MoveValuePair(Move theMove, double theValue) {
			this.move = theMove;
			this.value = theValue;
		}
		
		public double getValue() {
			return this.value;
		}
		
		public Move getMove() {
			return this.move;
		}
		
		public String toString() {
			String result = "";
			if(this.move != null) {
				result += getMove().toString();
				result += " : ";
				result += getValue();
				return result;
			}
			result += "null : ";
			result += getValue();
			return result;
		}

		@Override
		public int compare(MoveValuePair first, MoveValuePair second) {
						
			if(first.getValue() == second.getValue()) {
				return 0;
			}
			if(first.getValue() > second.getValue()) {
				return 1;
			}
			
			return -1;
		}

		@Override
		public int compareTo(MoveValuePair second) {
			if(this.getValue() == second.getValue()) {
				return 0;
			}
			if(this.getValue() > second.getValue()) {
				return 1;
			}
			
			return -1;
		}
		
=======
		Play.main(null);
		// Added comment
>>>>>>> Task1_William:src/game/Run.java
	}

}
