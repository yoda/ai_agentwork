/* jaDTi package - v0.6.0 */

package game;

import be.ac.ulg.montefiore.run.jadti.*;
import be.ac.ulg.montefiore.run.jadti.io.*;
import java.io.*;
import java.util.*;


/*
 * A short example program of the jaDTi library.
 */
public class ZooTest {

    final static String dbFileName = "testfiledir.0.results.25";
    final static String jadtiURL = "http://www.run.montefiore.ulg.ac.be/" +
	"~francois/software/jaDTi/";
    
    
    static public void main(String[] args)
	throws IOException {
	
	ItemSet learningSet = null;
	try {
	    learningSet = ItemSetReader.read(new FileReader(dbFileName));
	}
	catch(FileNotFoundException e) {
	    System.err.println("File not found : " + dbFileName + ".");
	    System.err.println("This file is included in the source " +
			       "distribution of jaDti.  You can find it at " +
			       jadtiURL);
	    System.exit(-1);
	}
	
	AttributeSet attributeSet = learningSet.attributeSet();
	
	Vector testAttributesVector = new Vector();
//	testAttributesVector.add(attributeSet.findByName("legs"));
//	testAttributesVector.add(attributeSet.findByName("tail"));
//	testAttributesVector.add(attributeSet.findByName("domestic"));
//	testAttributesVector.add(attributeSet.findByName("hair"));
//	testAttributesVector.add(attributeSet.findByName("feathers"));
	
	testAttributesVector.add(attributeSet.findByName("e0canBeTaken"));
	testAttributesVector.add(attributeSet.findByName("e0canMove"));
	testAttributesVector.add(attributeSet.findByName("e0canTake")); 
	testAttributesVector.add(attributeSet.findByName("e0takeKing")); 
	testAttributesVector.add(attributeSet.findByName("p1canBeTaken")); 
	testAttributesVector.add(attributeSet.findByName("p1canMove")); 
	testAttributesVector.add(attributeSet.findByName("p1canTake")); 
	testAttributesVector.add(attributeSet.findByName("p1takeKing")); 
	testAttributesVector.add(attributeSet.findByName("e2canBeTaken")); 
	testAttributesVector.add(attributeSet.findByName("e2canMove")); 
	testAttributesVector.add(attributeSet.findByName("e2canTake")); 
	testAttributesVector.add(attributeSet.findByName("e2takeKing")); 
	testAttributesVector.add(attributeSet.findByName("p3canBeTaken")); 
	testAttributesVector.add(attributeSet.findByName("p3canMove")); 
	testAttributesVector.add(attributeSet.findByName("p3canTake")); 
	testAttributesVector.add(attributeSet.findByName("p3takeKing")); 
	testAttributesVector.add(attributeSet.findByName("e4canBeTaken")); 
	testAttributesVector.add(attributeSet.findByName("e4canMove")); 
	testAttributesVector.add(attributeSet.findByName("e4canTake")); 
	testAttributesVector.add(attributeSet.findByName("e4takeKing")); 
	testAttributesVector.add(attributeSet.findByName("p5canBeTaken")); 
	testAttributesVector.add(attributeSet.findByName("p5canMove")); 
	testAttributesVector.add(attributeSet.findByName("p5canTake")); 
	testAttributesVector.add(attributeSet.findByName("p5takeKing")); 
	testAttributesVector.add(attributeSet.findByName("w6canBeTaken")); 
	testAttributesVector.add(attributeSet.findByName("w6canMove")); 
	testAttributesVector.add(attributeSet.findByName("w6canTake")); 
	testAttributesVector.add(attributeSet.findByName("w6takeKing")); 
	testAttributesVector.add(attributeSet.findByName("n7canBeTaken")); 
	testAttributesVector.add(attributeSet.findByName("n7canMove")); 
	testAttributesVector.add(attributeSet.findByName("n7canTake")); 
	testAttributesVector.add(attributeSet.findByName("n7takeKing")); 
	testAttributesVector.add(attributeSet.findByName("q8canBeTaken")); 
	testAttributesVector.add(attributeSet.findByName("q8canMove")); 
	testAttributesVector.add(attributeSet.findByName("q8canTake")); 
	testAttributesVector.add(attributeSet.findByName("q8takeKing")); 
	testAttributesVector.add(attributeSet.findByName("k9canBeTaken")); 
	testAttributesVector.add(attributeSet.findByName("k9canMove")); 
	testAttributesVector.add(attributeSet.findByName("k9canTake")); 
	testAttributesVector.add(attributeSet.findByName("k9takeKing")); 
	testAttributesVector.add(attributeSet.findByName("n10canBeTaken")); 
	testAttributesVector.add(attributeSet.findByName("n10canMove")); 
	testAttributesVector.add(attributeSet.findByName("n10canTake")); 
	testAttributesVector.add(attributeSet.findByName("n10takeKing")); 
	testAttributesVector.add(attributeSet.findByName("w11canBeTaken")); 
	testAttributesVector.add(attributeSet.findByName("w11canMove")); 
	testAttributesVector.add(attributeSet.findByName("w11canTake")); 
	testAttributesVector.add(attributeSet.findByName("w11takeKing"));
	
	AttributeSet testAttributes = new AttributeSet(testAttributesVector);
	SymbolicAttribute goalAttribute =
	    (SymbolicAttribute) learningSet.attributeSet().findByName("type");

	DecisionTree tree = buildTree(learningSet, testAttributes,
				      goalAttribute);
	
	printDot(tree);
	
	printGuess(learningSet.item(0), tree);
    }

    
    /*
     * Build the decision tree.
     */
    static private DecisionTree buildTree(ItemSet learningSet, 
					  AttributeSet testAttributes, 
					  SymbolicAttribute goalAttribute) {
	DecisionTreeBuilder builder = 
	    new DecisionTreeBuilder(learningSet, testAttributes,
				    goalAttribute);
	
	return builder.build().decisionTree();
    }
    

    /*
     * Prints a dot file content depicting a tree.
     */
    static private void printDot(DecisionTree tree) {
	System.out.println((new DecisionTreeToDot(tree)).produce());
    }
    

    /*
     * Prints an item's guessed goal attribute value.
     */
    static private void printGuess(Item item, DecisionTree tree) {
	AttributeSet itemAttributes = tree.getAttributeSet();
	SymbolicAttribute goalAttribute = tree.getGoalAttribute();
	
	KnownSymbolicValue goalAttributeValue = 
	    (KnownSymbolicValue) item.valueOf(itemAttributes, goalAttribute);
	KnownSymbolicValue guessedGoalAttributeValue = 
	    tree.guessGoalAttribute(item);

	String s = "Item goal attribute value is " +
	    goalAttribute.valueToString(goalAttributeValue) + "\n";
	
	s += "The value guessed by the tree is " + 
	    tree.getGoalAttribute().valueToString(guessedGoalAttributeValue);
	
	System.out.println(s);
    }
}
