package game;

import be.ac.ulg.montefiore.run.jadti.*;
import be.ac.ulg.montefiore.run.jadti.io.*;
import java.io.*;
import java.util.*;



public class DataDTreeGenerator {

	
	private String dataFileName;
	private String filename;
	private boolean debug = false;
	
    public DataDTreeGenerator(String datainput, String filename) {
    	this.dataFileName = datainput;
    	this.filename = filename;
    }
	
	
    public DecisionTree getDTree() throws FileNotFoundException {
    	ItemSet learningSet = null;
    	try {
    	    learningSet = ItemSetReader.read(new FileReader(this.dataFileName));
    	}
    	catch(FileNotFoundException e) {
    		System.err.println("File not found!");
    	    System.exit(1);
    	} catch (FileFormatException e) {
    		System.err.println("File format error!");
    		System.exit(1);
		} catch (IOException e) {
			System.err.println("IO error!");
			System.exit(1);
		}
    	
    	AttributeSet attributeSet = learningSet.attributeSet();
    	
    	Vector testAttributesVector = new Vector();
    	
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
    	//
    	testAttributesVector.add(attributeSet.findByName("wtakekingbb"));
    	
    	AttributeSet testAttributes = new AttributeSet(testAttributesVector);
    	SymbolicAttribute goalAttribute =
    	    (SymbolicAttribute) learningSet.attributeSet().findByName("type");

    	DecisionTree tree = buildTree(learningSet, testAttributes,
    				      goalAttribute);
    	return tree;
    }
    
    public boolean generateTree() throws IOException {
	
	ItemSet learningSet = null;
	try {
	    learningSet = ItemSetReader.read(new FileReader(this.dataFileName));
	}
	catch(FileNotFoundException e) {
	    return false;
	}
	
	AttributeSet attributeSet = learningSet.attributeSet();
	
	Vector testAttributesVector = new Vector();
	
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
	//
	testAttributesVector.add(attributeSet.findByName("wtakekingbb"));
	
	AttributeSet testAttributes = new AttributeSet(testAttributesVector);
	SymbolicAttribute goalAttribute =
	    (SymbolicAttribute) learningSet.attributeSet().findByName("type");

	DecisionTree tree = buildTree(learningSet, testAttributes,
				      goalAttribute);
	
	// About to turn tree into dot for printing beauty.
	Vector newtestAttributesVector = new Vector();
	Iterator<Attribute> it = tree.getAttributeSet().attributes().iterator();
	while(it.hasNext()) {
		Attribute old = it.next();
		char[] temp = old.name().toCharArray();
		temp[0] = Character.toUpperCase(temp[0]);
		newtestAttributesVector.add(new String(temp));
	}
	
	Iterator<Attribute> at = newtestAttributesVector.iterator();
	while(at.hasNext()) {
		System.out.println(at.next());
	}
	
	DecisionTreeToDot dot = new DecisionTreeToDot(tree);
	
	String dotString = dot.produce();
	
	File file;
	int x = 0;
	do {
		file = new File(filename + "." + x);
		x++;
	} while(file.exists());
	try {
		file.createNewFile();
		file.setWritable(true, true);
	} catch (IOException e) {
		e.printStackTrace();
		if(debug) System.out.println("Unable to create file!");
	}
	FileOutputStream fos;
	PrintStream pos;
	if(file.canWrite()) {
		try {
			fos = new FileOutputStream(file, true);
			pos = new PrintStream(fos);
			pos.println(dotString);
		} catch (FileNotFoundException e) {
			if(debug) System.out.println("Failed creating FileOutputStream!");
			if(debug) e.printStackTrace();
			return false;
		}
		pos.close();
		try {
			fos.close();
		} catch (IOException e) {
			if(debug) System.out.println("Unable to close FileOutputStream!");
			if(debug) e.printStackTrace();
		}
		return true;
	} 
	return false;
	
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
    public String printGuess(Item item, DecisionTree tree) {
	AttributeSet itemAttributes = tree.getAttributeSet();
	SymbolicAttribute goalAttribute = tree.getGoalAttribute();
	
	KnownSymbolicValue goalAttributeValue = 
	    (KnownSymbolicValue) item.valueOf(itemAttributes, goalAttribute);
	KnownSymbolicValue guessedGoalAttributeValue = 
	    tree.guessGoalAttribute(item);

	String s = tree.getGoalAttribute().valueToString(guessedGoalAttributeValue);
	
	return s;
    }
}
