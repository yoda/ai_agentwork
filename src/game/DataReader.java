package game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import search.Node;

public class DataReader {
	
private File[] files;
private File directory;
private String filename;
private String directoryname;
private ObjectInputStream ois;
private FileInputStream fis;	
private boolean debug = true;

	public DataReader(String directoryname) {
		this.directoryname = directoryname;
		this.directory = new File(directoryname);
		if(!directory.exists()) {
			if(debug) System.out.println("Directory does not exist!");
		}
		if(!directory.canRead()) {
			if(debug) System.out.println("Can't read specified directory!");
		}
		this.files = this.directory.listFiles();
	}
	
	public String getDirectoryname() {
		return this.directory.getAbsolutePath();
	}
	
	public ArrayList<DataContainer> readData() {
		ArrayList<DataContainer> data = new ArrayList<DataContainer>();
		
		for(int x = 0; x < this.files.length; x++) {
			if(this.files[x].canRead()) {
					try {
						fis = new FileInputStream(this.files[x]);
						ois = new ObjectInputStream(fis);
						data.add((DataContainer)ois.readObject());
						ois.close();
						fis.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
			}
		}
		return data;
	}
	
}
