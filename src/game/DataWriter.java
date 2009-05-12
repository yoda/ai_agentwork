package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import search.Node;

public class DataWriter {

	private File file;
	private File directory;
	private String filename;
	private String directoryname;
	private ObjectOutputStream oos;
	private FileOutputStream fos;
	private boolean debug = true;
	
	public DataWriter(String filename, String directoryname) {
		this.filename = filename;
		this.directoryname = directoryname;
		
		int x = 0;
		
		do {	
			this.directory = new File(this.directoryname + "." + x);
			x++;
		} while(this.directory.exists());
		if(!this.directory.exists()) {
			this.directory.mkdir();
		}
	}
			
	public String getFilename() {
		return file.getAbsolutePath();
	}
	
	public String getDirectory() {
		return directory.getAbsolutePath();
	}
	
	public boolean writeData(DataContainer data) {
		
		
		int x = 0;
		do {
			file = new File(this.directory.getAbsolutePath() + "/" + filename + "." + x);
			x++;
		} while(file.exists());
		try {
			file.createNewFile();
			file.setWritable(true, true);
		} catch (IOException e) {
			e.printStackTrace();
			if(debug) System.out.println("Unable to create file!");
		}
		
		if(file.canWrite()) {
				try {
					this.fos = new FileOutputStream(this.file, true);
					try {
						this.oos = new ObjectOutputStream(this.fos);
						try {
							this.oos.writeObject(data);
							this.oos.flush();
						} catch (IOException e) {
							if(debug) e.printStackTrace();
							try {
								fos.close();
							} catch (IOException e1) {
								if(debug) System.out.println("Trying to cleanup...");
								if(debug) e1.printStackTrace();
							}
							if(debug) System.out.println("Failed writing object to file!");
							return false;
						}
					} catch (IOException e) {
						if(debug) System.out.println("Failed creating ObjectOutputStream!");
						try {
							fos.close();
						} catch (IOException e1) {
							if(debug) System.out.println("Trying to cleanup...");
							if(debug) e1.printStackTrace();
						}
						if(debug) e.printStackTrace();
						return false;
					}
				} catch (FileNotFoundException e) {
					if(debug) System.out.println("Failed creating FileOutputStream!");
					if(debug) e.printStackTrace();
					return false;
				}
				return true;
			} 
			return false;
	}
	
	public void closeFile() {
		if(this.fos != null) {
			try {
				this.fos.close();
				this.fos = null;
			} catch (IOException e) {
				if(debug) System.out.println("FileOutputStream already closed!");
			}
		}
		if(this.oos != null) {
			try {
				this.oos.close();
				this.oos = null;
			} catch (IOException e) {
				if(debug) System.out.println("ObjectOutputStream already closed!");
			}
		}
	}

}
