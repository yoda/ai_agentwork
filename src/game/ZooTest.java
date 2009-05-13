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

    final static String dbFileName = "testfiledir.0.results.34";
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
	
	testAttributesVector.add(attributeSet.findByName("ecanbetakena"));
	testAttributesVector.add(attributeSet.findByName("ecanmovea"));
	testAttributesVector.add(attributeSet.findByName("ecantakea")); 
	testAttributesVector.add(attributeSet.findByName("etakekinga")); 
	testAttributesVector.add(attributeSet.findByName("pcanbetakenb")); 
	testAttributesVector.add(attributeSet.findByName("pcanmoveb")); 
	testAttributesVector.add(attributeSet.findByName("pcantakeb")); 
	testAttributesVector.add(attributeSet.findByName("ptakekingb")); 
	testAttributesVector.add(attributeSet.findByName("ecanbetakenc")); 
	testAttributesVector.add(attributeSet.findByName("ecanmovec")); 
	testAttributesVector.add(attributeSet.findByName("ecantakec")); 
	testAttributesVector.add(attributeSet.findByName("etakekingc")); 
	testAttributesVector.add(attributeSet.findByName("pcanbetakend")); 
	testAttributesVector.add(attributeSet.findByName("pcanmoved")); 
	testAttributesVector.add(attributeSet.findByName("pcantaked")); 
	testAttributesVector.add(attributeSet.findByName("ptakekingd")); 
	testAttributesVector.add(attributeSet.findByName("ecanbetakene")); 
	testAttributesVector.add(attributeSet.findByName("ecanmovee")); 
	testAttributesVector.add(attributeSet.findByName("ecantakee")); 
	testAttributesVector.add(attributeSet.findByName("etakekinge")); 
	testAttributesVector.add(attributeSet.findByName("pcanbetakenf")); 
	testAttributesVector.add(attributeSet.findByName("pcanmovef")); 
	testAttributesVector.add(attributeSet.findByName("pcantakef")); 
	testAttributesVector.add(attributeSet.findByName("ptakekingf"));
	 
	testAttributesVector.add(attributeSet.findByName("wcanbetakeng")); 
	testAttributesVector.add(attributeSet.findByName("wcanmoveg")); 
	testAttributesVector.add(attributeSet.findByName("wcantakeg")); 
	testAttributesVector.add(attributeSet.findByName("wtakekingg")); 
	testAttributesVector.add(attributeSet.findByName("ncanbetakenh")); 
	testAttributesVector.add(attributeSet.findByName("ncanmoveh")); 
	testAttributesVector.add(attributeSet.findByName("ncantakeh")); 
	testAttributesVector.add(attributeSet.findByName("ntakekingh")); 
	testAttributesVector.add(attributeSet.findByName("qcanbetakeni")); 
	testAttributesVector.add(attributeSet.findByName("qcanmovei")); 
	testAttributesVector.add(attributeSet.findByName("qcantakei")); 
	testAttributesVector.add(attributeSet.findByName("qtakekingi"));
	 
	testAttributesVector.add(attributeSet.findByName("kcanbetakenj")); 
	testAttributesVector.add(attributeSet.findByName("kcanmovej")); 
	testAttributesVector.add(attributeSet.findByName("kcantakej")); 
	testAttributesVector.add(attributeSet.findByName("ktakekingj")); 
	testAttributesVector.add(attributeSet.findByName("ncanbetakenba")); 
	testAttributesVector.add(attributeSet.findByName("ncanmoveba")); 
	testAttributesVector.add(attributeSet.findByName("ncantakeba")); 
	testAttributesVector.add(attributeSet.findByName("ntakekingba"));
	 
	testAttributesVector.add(attributeSet.findByName("wcanbetakenbb")); 
	testAttributesVector.add(attributeSet.findByName("wcanmovebb")); 
	testAttributesVector.add(attributeSet.findByName("wcantakebb"));
	
	testAttributesVector.add(attributeSet.findByName("wtakekingbb"));
	
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
